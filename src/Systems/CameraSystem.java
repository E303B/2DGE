package Systems;

public class CameraSystem extends BaseSystem {
    public float cameraX, cameraY = 0f;
    public float cameraSize = 50f;
    static {
        BaseSystem.systems.add(CameraSystem.class);
    }

    @Override
    public void run() {
    }

}
