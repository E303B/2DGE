package Shared;

import java.awt.Image;
import java.io.IOException;
import Systems.MainSystem;

public class Start implements Runnable {
    public static final boolean logOnStart = true;

    public Window mainWindow;
    public static final String logs = "log.txt", title = "2DGE";
    public static final Image icon = null;
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
        if (logOnStart)
            mainLogger.log("Finished engine initialization with " + mainLogger.errors + " errors, "
                    + mainLogger.warnings + " warnings");
        long previous = System.currentTimeMillis();
        long deltaTime = 0;
        while (true) {
            deltaTime = System.currentTimeMillis() - previous;
            tick();
            previous = System.currentTimeMillis();
        }
    }
}