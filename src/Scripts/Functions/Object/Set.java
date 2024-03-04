package Scripts.Functions.Object;

import Scripts.ScriptObject;
import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;
import Shared.Start;

public class Set extends BaseFunction {
    /*
     * 0. Class name
     * 1. Attribute name
     * 2. Value
     */
    @Override
    public void run(String functionParams, Object[] scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        if (params.length > 2) {
            try {
                ((ScriptObject)params[0]).setAttribute(params[1].toString(), params[2]);
            } catch (Exception e) {
                Start.mainRunner.mainLogger
                        .error("Object.Set throw " + e.getClass().getSimpleName() + ": " + e.getLocalizedMessage());
            }
        } else
            Start.mainRunner.mainLogger.error("Object.Set takes 3 argument, but " + params.length + " given");
    }

}
