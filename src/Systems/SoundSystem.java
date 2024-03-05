package Systems;

import Scripts.ScriptRunner;
import Shared.Sound;
import Shared.Start;

public class SoundSystem extends BaseSystem {
    private static void playSound(String sound) {
        new Thread(new Sound(sound));
    }

    @Override
    public void run() {}

    @Override
    public void call(Object[] params, ScriptRunner runner) {
        if (params.length < 2) {
            Start.mainRunner.mainLogger
                    .error("Call SoundSystem takes at least 2 arguments, but " + params.length + " given");
            return;
        }
        switch (params[0].toString()) {
            case "play":
                playSound(params[1].toString());
                break;
            default:
                break;
        }
    }

}
