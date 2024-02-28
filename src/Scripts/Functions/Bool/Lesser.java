package Scripts.Functions.Bool;

import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;
import Shared.Start;
import Shared.Tools;

public class Lesser extends BaseFunction {

    @Override
    public void run(String functionParams, Object scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        if (params.length > 2) {
            if(Tools.isLong(params[1].toString())&&Tools.isLong(params[2].toString())){
                runner.setVar(params[0].toString(), Long.parseLong(params[1].toString())<Long.parseLong(params[2].toString()));
            }
            else if(Tools.isDouble(params[1].toString())||Tools.isDouble(params[2].toString())){
                runner.setVar(params[0].toString(), Double.parseDouble(params[1].toString())<Double.parseDouble(params[2].toString()));
            }
            else{
                Start.mainRunner.mainLogger
                    .error("Bool.Lesser get invalid value type");
            }
        } else {
            Start.mainRunner.mainLogger
                    .error("Bool.Lesser should be used with at least 3 argument, but " + params.length + " given");
        }
    }

}
