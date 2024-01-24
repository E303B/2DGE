package Shared;

import Systems.MainSystem;

public class Start implements Runnable {
    public Window mainWindow;
    int tps = 50;
    public static Start mainRunner;
    public MainSystem mainSystem;

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
        mainWindow = new Window("2DGE", null);
        mainSystem = new MainSystem();
        while (true) {
            tick();
            try {
                Thread.sleep(1000 / tps);
            } catch (InterruptedException e) {
                System.out.println("Catch an Interrupted exception");
            }
        }
    }
}