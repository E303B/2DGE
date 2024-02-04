package Scripts.Packages;

import java.util.ArrayList;

import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;

public abstract class BasePackage {
    protected ArrayList<BasePackage> childPackages;
    protected ArrayList<BaseFunction> childFunctions;
    public String name;

    public BasePackage() {
        childPackages = new ArrayList<BasePackage>();
        childFunctions = new ArrayList<BaseFunction>();
        name = this.getClass().getName().substring(this.getClass().getName().lastIndexOf(".") + 1);
    }

    public final boolean tryRun(String command, Object scriptsParams, ScriptRunner runner) {
        if (childFunctions != null) {
            for (BaseFunction function : childFunctions) {
                if (command.startsWith(function.name)) {
                    function.run(command.substring(function.name.length() + 1).trim(), scriptsParams,
                            runner);
                    return true;
                }
            }
        }
        if (childPackages != null)
            for (BasePackage childPackage : childPackages)
                if (command.startsWith(childPackage.name))
                    return childPackage.tryRun(command.substring(childPackage.name.length() + 2).trim(),
                            scriptsParams, runner);
        return false;
    }
}
