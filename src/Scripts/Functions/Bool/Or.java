package Scripts.Functions.Bool;

import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;
import Shared.Start;
import Shared.Tools;

public class Or extends BaseFunction {

    @Override
    public void run(String functionParams, Object scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        if (params.length > 2) {
            if(Tools.isBool(params[1].toString())&&Tools.isBool(params[2].toString())){
                runner.setVar(params[0].toString(), Boolean.parseBoolean(params[1].toString())||Boolean.parseBoolean(params[2].toString()));
            }
            else{
                Start.mainRunner.mainLogger
                    .error("Bool.Or get invalid value type");
            }
        } else {
            Start.mainRunner.mainLogger
                    .error("Bool.Or should be used with at least 3 argument, but " + params.length + " given");
        }
    }

}
