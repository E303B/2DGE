package Scripts.Functions.Class.Attribute;

import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;
import Scripts.Types.ScriptClass;
import Shared.Start;

public class Any extends BaseFunction {

    @Override
    public void run(String functionParams, Object scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        try {
            if (params.length > 2) {
                ((ScriptClass) runner.getType(params[0].toString())).tryAddAttribute(params[1].toString(), params[2]);
            } else if (params.length == 2) {
                ((ScriptClass) runner.getType(params[0].toString())).tryAddAttribute(params[1].toString());
            } else
                Start.mainRunner.mainLogger
                        .error("Class.Attribute.Any takes at least 2 argument, but " + params.length + " given");
        } catch (Exception e) {
            e.printStackTrace();;
            Start.mainRunner.mainLogger
                    .error("Class.Attribute.Any throw " + e.getClass().getSimpleName() + ": " + e.getLocalizedMessage());
        }
    }

}
