package Systems;

import Shared.Start;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class DrawingSystem extends BaseSystem {
    static {
        BaseSystem.systems.add(DrawingSystem.class);
    }

    @Override
    public void run() {
    }

    public final void renderImage(String path, int x, int y, int width, int height) throws IOException {
        Graphics gr = Start.mainRunner.mainWindow.renderBuf.getGraphics();
        final BufferedImage image = ImageIO.read(new File(path));
        gr.drawImage(image, x - width / 2, y - height / 2, x + width / 2, y + height / 2, null);
    }
}
