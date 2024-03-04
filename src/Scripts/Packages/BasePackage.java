package Scripts.Packages;

import java.util.ArrayList;

import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;

public abstract class BasePackage {
    protected ArrayList<BasePackage> childPackages;
    protected ArrayList<BaseFunction> childFunctions;

    public BasePackage() {
        childPackages = new ArrayList<BasePackage>();
        childFunctions = new ArrayList<BaseFunction>();
    }

    public final boolean tryRun(String command, Object[] scriptsParams, ScriptRunner runner) {
        if (childFunctions != null) {
            for (BaseFunction function : childFunctions) {
                if (command.startsWith(function.getClass().getSimpleName())) {
                    function.run(command.substring(function.getClass().getSimpleName().length() + 1).trim(), scriptsParams,
                            runner);
                    return true;
                }
            }
        }
        if (childPackages != null)
            for (BasePackage childPackage : childPackages) {
                if (command.startsWith(childPackage.getClass().getSimpleName()))
                    return childPackage.tryRun(command.substring(childPackage.getClass().getSimpleName().length() + 1).trim(),
                            scriptsParams, runner);
            }
        return false;
    }
}
