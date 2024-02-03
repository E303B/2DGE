package Systems;

import Types.GameObject;
import Shared.Start;
import java.io.IOException;

import Components.TextureComponent;
import Components.VectorComponent;

public class TextureSystem extends BaseSystem {

    @Override
    public void run() {
        CameraSystem camera = (CameraSystem) Start.mainRunner.mainSystem.getSystem("Systems.CameraSystem");
        DrawingSystem drawer = (DrawingSystem) Start.mainRunner.mainSystem.getSystem("Systems.DrawingSystem");
        for (GameObject i : ((GameObjectSystem) Start.mainRunner.mainSystem
                .getSystem(GameObjectSystem.class)).gameObjects) {
            TextureComponent texture = (TextureComponent) i.getComponent("Components.TextureComponent");
            VectorComponent vector = (VectorComponent) i.getComponent("Components.VectorComponent");
            int drawX = (int) ((vector.x - camera.cameraX) * camera.cameraSize)
                    + Start.mainRunner.mainWindow.getWidth() / 2;
            int drawY = (int) ((vector.y - camera.cameraY) * camera.cameraSize)
                    + Start.mainRunner.mainWindow.getHeight() / 2;
            try {
                drawer.renderImage("./Resources/Textures/" + texture.path, drawX, drawY,
                        (int) (texture.renderWidth * camera.cameraSize),
                        (int) (texture.renderHeight * camera.cameraSize));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
