package Scripts.Functions.Object;

import Scripts.ScriptObject;
import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;
import Shared.Start;

public class Length extends BaseFunction {

    @Override
    public void run(String functionParams, Object scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        if (params.length > 1) {
            runner.setVar(params[1].toString(), ((ScriptObject)params[0]).size());
        } else
            Start.mainRunner.mainLogger.error("Object.Length takes 2 argument, but " + params.length + " given");
    }

}
