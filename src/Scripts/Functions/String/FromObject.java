package Scripts.Functions.String;

import Scripts.ScriptObject;
import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;
import Shared.Start;

public class FromObject extends BaseFunction {

    @Override
    public void run(String functionParams, Object[] scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        if (params.length > 1) {
            runner.setVar(params[0].toString(), ((ScriptObject)params[1]).toString());
        } else
            Start.mainRunner.mainLogger.error("String.ToObject takes 2 arguments, but " + params.length + " given");
    }

}
