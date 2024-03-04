package Scripts.Functions.Object;

import Scripts.ScriptObject;
import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;
import Shared.Start;

public class Get extends BaseFunction {
    /*
     * 0. Class
     * 1. Attribute name
     * 2. Var where value should be stored
     */
    @Override
    public void run(String functionParams, Object[] scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        if (params.length > 2) {
            try {
                runner.setVar(params[2].toString(),
                        ((ScriptObject) params[0]).getAttribute(params[1].toString()));
            } catch (Exception e) {
                Start.mainRunner.mainLogger
                        .error("Class.Get throw " + e.getClass().getSimpleName() + ": " + e.getLocalizedMessage());
            }
        } else
            Start.mainRunner.mainLogger.error("Class.Get takes 3 argument, but " + params.length + " given");
    }

}
