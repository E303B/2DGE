package Scripts.Functions.VariableFunctions;

import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;
import Shared.Start;

public class Del extends BaseFunction{

    @Override
    public void run(String functionParams, Object[] scriptParams, ScriptRunner runner) {
        Object[] params=parseAttributes(functionParams, runner);
        if(params.length>0){
            runner.deleteVar(params[0].toString());
        }
        else Start.mainRunner.mainLogger.error("Variable.Del takes one argument, but zero given");
    }
    
}
