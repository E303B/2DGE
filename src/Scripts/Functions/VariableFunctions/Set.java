package Scripts.Functions.VariableFunctions;

import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;
import Shared.Start;

public class Set extends BaseFunction {

    @Override
    public void run(String functionParams, Object scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        if (params.length > 1) {
            runner.setVar(params[0].toString(), params[1]);
        } else
            Start.mainRunner.mainLogger
                    .error("Variable.Set takes 2 argument, but given " + Integer.toString(params.length));
    }

}
