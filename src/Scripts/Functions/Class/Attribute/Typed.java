package Scripts.Functions.Class.Attribute;

import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;
import Scripts.Types.ScriptClass;
import Shared.Start;

public class Typed extends BaseFunction {
    /*
     * 0. Class name
     * 1. Attribute name
     * 2. Attribute type
     * *3. Default value
     */
    @Override
    public void run(String functionParams, Object[] scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        try {
            if (params.length > 3) {
                ((ScriptClass) runner.getType(params[0].toString())).tryAddAttribute(params[1].toString(), params[3],
                        runner.getType(params[2].toString()));
            } else if (params.length == 3) {
                ((ScriptClass) runner.getType(params[0].toString())).tryAddAttribute(params[1].toString(),
                        runner.getType(params[2].toString()));
            } else
                Start.mainRunner.mainLogger
                        .error("Class.Attribute.Typed takes at least 3 argument, but " + params.length + " given");
        } catch (Exception e) {
            Start.mainRunner.mainLogger
                    .error("Class.Attribute.Typed throw " + e.getClass().getSimpleName() + ": " + e.getLocalizedMessage());
        }
    }

}
