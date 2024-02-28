package Scripts.Functions.Bool;

import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;
import Shared.Start;

public class StrongEqual extends BaseFunction {

    @Override
    public void run(String functionParams, Object scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        if(params.length>2){
            runner.setVar(params[0].toString(), params[1].equals(params[2]));
        }
        else{
            Start.mainRunner.mainLogger
                    .error("Bool.StrongEqual should be used with at least 3 argument, but " + params.length + " given");
        }
    }
    
}
