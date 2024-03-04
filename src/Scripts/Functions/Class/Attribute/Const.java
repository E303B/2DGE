package Scripts.Functions.Class.Attribute;

import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;
import Scripts.Types.ScriptClass;
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
                ((ScriptClass) runner.getType(params[0].toString())).tryAddAttribute(params[1].toString(), params[2],
                        true);
            } else
                Start.mainRunner.mainLogger
                        .error("Class.Attribute.Const takes at least 3 argument, but " + params.length + " given");
        } catch (Exception e) {
            Start.mainRunner.mainLogger
                    .error("Class.Attribute.Const throw " + e.getClass().getSimpleName() + ": " + e.getLocalizedMessage());
        }
    }

}
