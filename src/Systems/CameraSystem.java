package Systems;

import Scripts.ScriptRunner;
import Shared.Start;

public class CameraSystem extends BaseSystem {
    public float cameraX, cameraY = 0f;
    public float cameraSize = 50f;
    static {
        BaseSystem.systems.add(CameraSystem.class);
    }

    @Override
    public void run() {
    }

    @Override
    public void call(Object[] params, ScriptRunner runner) {
        if (params.length < 2) {
            Start.mainRunner.mainLogger
                    .error("Call CameraSystem takes at least 2 arguments, but " + params.length + " given");
            return;
        }
        switch (params[0].toString()) {
            case "GetCameraX":
                runner.setVar(params[1].toString(), cameraX);
                break;
            case "GetCameraY":
                runner.setVar(params[1].toString(), cameraY);
                break;
            case "GetCameraSize":
                runner.setVar(params[1].toString(), cameraSize);
                break;
            case "SetCameraX":
                cameraX = (float) params[1];
                break;
            case "SetCameraY":
                cameraY = (float) params[1];
                break;
            case "SetCameraSize":
                cameraSize = (float) params[1];
                break;
            default:
                break;
        }
    }

}
