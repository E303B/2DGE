package Systems;

import java.util.ArrayList;

import Shared.Start;

public abstract class BaseSystem {
    public abstract void run();

    @SuppressWarnings("rawtypes")
    public static ArrayList<Class> systems = new ArrayList<Class>();

    public BaseSystem() {
        if (Start.logOnStart)
            Start.mainRunner.mainLogger.log("Init " + this.getClass().getName());
    }

    public void init() {

    }
}
