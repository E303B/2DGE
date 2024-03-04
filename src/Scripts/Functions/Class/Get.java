package Scripts.Functions.Class;

import java.lang.reflect.InvocationTargetException;

import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;
import Scripts.Types.ScriptClass;
import Shared.Start;

public class Get extends BaseFunction {
    /*
     * 0. Class
     * 1. Var where value should be stored
     * 2. Attribute name
     */
    @Override
    public void run(String functionParams, Object[] scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        if (params.length > 2) {
            try {
                runner.setVar(params[1].toString(),
                        ((ScriptClass) runner.getType(params[0].toString())).getAttribute(params[2].toString()));
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                Start.mainRunner.mainLogger
                        .error("Class.Get throw " + e.getClass().getSimpleName() + ": " + e.getLocalizedMessage());
            }
        } else
            Start.mainRunner.mainLogger.error("Class.Get takes 3 argument, but " + params.length + " given");
    }

}
