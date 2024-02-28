package Scripts.Functions.Bool;

import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;
import Shared.Start;

public class Equal extends BaseFunction {

    @Override
    public void run(String functionParams, Object scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        if (params.length > 2) {
            runner.setVar(params[0].toString(), params[1].toString().equals(params[2].toString()));
        } else {
            Start.mainRunner.mainLogger
                    .error("Bool.Equal should be used with at least 3 argument, but " + params.length + " given");
        }
    }

}
