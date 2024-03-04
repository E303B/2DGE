package Scripts.Functions.Object.Attribute;

import Scripts.ScriptObject;
import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;
import Shared.Start;

public class Const extends BaseFunction {
    /*
     * 0. Class name
     * 1. Attribute name
     * 2. Value
     */
    @Override
    public void run(String functionParams, Object scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        try {
            if (params.length > 2) {
                ((ScriptObject) params[0]).tryAddAttribute(params[1].toString(), params[2],
                        true);
            } else
                Start.mainRunner.mainLogger
                        .error("Object.Attribute.Const takes at least 3 argument, but " + params.length + " given");
        } catch (Exception e) {
            Start.mainRunner.mainLogger
                    .error("Object.Attribute.Const throw " + e.getClass().getSimpleName() + ": "
                            + e.getLocalizedMessage());
        }
    }

}
