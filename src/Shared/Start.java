package Shared;

import java.awt.Image;
import java.io.IOException;
import Systems.MainSystem;

public class Start implements Runnable {

    public static final boolean logOnStart = false;
    public static final String logs = "log.txt", title = "2DGE";
    public static final Image icon = null;
    public static int limitTPS = 50;

    public Window mainWindow;
    public static Start mainRunner;
    public MainSystem mainSystem;
    public LogManager mainLogger;

    public static final int rnd(int min, int max) {
        double gen = (Math.random() * ((max - min) + 1)) + min;
        int result = (int) gen;
        return result;
    }

    public static void main(String[] args) {
        mainRunner = new Start();
        new Thread(mainRunner).start();
    }

    private void tick() {
        mainSystem.run();
        mainWindow.repaint();
    }

    @SuppressWarnings("unused")
    @Override
    public void run() {
        try {
            mainLogger = new LogManager(logs);
        } catch (IOException e) {
            return;
        }
        if (logOnStart)
            mainLogger.log("Init engine");
        mainWindow = new Window(title, icon);
        mainSystem = new MainSystem();
        mainSystem.init();
        if (logOnStart)
            mainLogger.log("Finished engine initialization with " + mainLogger.errors + " errors, "
                    + mainLogger.warnings + " warnings");
        long previous = System.currentTimeMillis();
        long deltaTime = 0;
        int tps = 0;
        while (true) {
            deltaTime = System.currentTimeMillis() - previous;
            try {
                tps = (int) Math.floor(1 / deltaTime);
            } catch (ArithmeticException e) {
                tps = limitTPS + 1;
            }
            if (tps >= limitTPS) {
                try {
                    Thread.sleep(1000 / limitTPS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            tick();
            previous = System.currentTimeMillis();
        }
    }
}