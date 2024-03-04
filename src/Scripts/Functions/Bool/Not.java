package Scripts.Functions.Bool;

import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;
import Shared.Start;
import Shared.Tools;

public class Not extends BaseFunction {
    /*
     * 0. Var name
     * 1. Arg
     */
    @Override
    public void run(String functionParams, Object scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        if (params.length > 1) {
            if(Tools.isBool(params[1].toString()))
                runner.setVar(params[0].toString(), !Boolean.parseBoolean(params[1].toString()));
            else
                Start.mainRunner.mainLogger
                    .error("Bool.Not get invalid argument type");
        } else {
            Start.mainRunner.mainLogger
                    .error("Bool.Not should be used with at least 2 argument, but " + params.length + " given");
        }
    }
}
