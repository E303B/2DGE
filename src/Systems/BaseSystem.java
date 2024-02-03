package Systems;

import Shared.Start;

public abstract class BaseSystem {
    public abstract void run();

    public BaseSystem() {
        if (Start.logOnStart)
            Start.mainRunner.mainLogger.log("Init " + this.getClass().getName());
    }

    public void init(){

    }
}
