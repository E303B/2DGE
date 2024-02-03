package Shared;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Window extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    ArrayList<Integer> keysPressed;
    public BufferedImage renderBuf;

    public Window(String title, Image image) {
        if (Start.logOnStart)
            Start.mainRunner.mainLogger.log("Init window");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(title);
        this.setIconImage(image != null ? image : getIconImage());
        this.setExtendedState(MAXIMIZED_BOTH);
        keysPressed = new ArrayList<>();
        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (keysPressed.contains(e.getKeyCode()))
                    return;
                keysPressed.add(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (!keysPressed.contains(e.getKeyCode()))
                    return;
                keysPressed.remove(keysPressed.indexOf(e.getKeyCode()));
            }

        });
        if (Start.logOnStart)
            Start.mainRunner.mainLogger.log("Finished window initialization");
    }

    @Override
    public void paint(Graphics g) {
        ((Graphics2D) g).drawImage(renderBuf, null, 0, 0);
    }
}
