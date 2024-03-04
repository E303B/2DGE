package Shared;

import java.awt.Image;
import java.io.IOException;

import Scripts.ScriptManager;
import Systems.MainSystem;

public final class Start implements Runnable {
    // Config data
    public static boolean logOnStart = false;
    public static String logs = "log.txt";
    public static Image icon = null;
    public static int limitTPS = 50;
    public static String texturesPath;

    // Main global objects
    public ConfigReader config;
    public Window mainWindow;
    public static Start mainRunner;
    public MainSystem mainSystem;
    public LogManager mainLogger;
    public ScriptManager scriptManager;

    public static final int rnd(int min, int max) {
        double gen = (Math.random() * ((max - min) + 1)) + min;
        int result = (int) gen;
        return result;
    }

    public static void main(String[] args) {
        mainRunner = new Start();
        new Thread(mainRunner).start();
    }

    private final void tick() {
        mainSystem.run();
        mainWindow.repaint();
    }

    @SuppressWarnings("unused")
    @Override
    public void run() {
        // File related global object
        try {
            mainLogger = new LogManager(logs);
        } catch (IOException e) {
            return;
        }
        try {
            config = new ConfigReader();
        } catch (IOException e) {
            mainLogger.error("Failed to read config");
            return;
        }

        // Loadings main config
        logOnStart = (boolean) config.getValue("logOnStart", true);
        limitTPS = Integer.parseInt(config.getValue("limitTPS", 1).toString());
        texturesPath = (String) config.getValue("texturesPath", "");

        if (logOnStart)
            mainLogger.log("Init engine");
        // Other main global objects
        mainWindow = new Window((String) config.getValue("title", "2DGE"), icon);
        mainSystem = new MainSystem();
        scriptManager = new ScriptManager((String) config.getValue("scriptsPath", ""));
        mainSystem.init();
        if (logOnStart)
            mainLogger.log("Finished engine initialization with " + mainLogger.errors + " errors, "
                    + mainLogger.warnings + " warnings");
        long previous = System.currentTimeMillis();
        long deltaTime = 0;
        int tps = 0;
        config.logConfigWarnings();
        scriptManager.runScript("list.esf");
        // Main loop
        while (true) {
            deltaTime = System.currentTimeMillis() - previous;
            try {
                tps = (int) Math.floor(1 / deltaTime);
            } catch (ArithmeticException e) {
                tps = limitTPS + 1;
            }
            previous = System.currentTimeMillis();
            // if (limitTPS != 0 && tps >= limitTPS) {
            try {
                Thread.sleep(1000 / limitTPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // }
            tick();
        }
    }
}