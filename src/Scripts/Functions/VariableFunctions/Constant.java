package Scripts.Functions.VariableFunctions;

import Scripts.ScriptRunner;
import Scripts.Var;
import Scripts.Functions.BaseFunction;
import Shared.Start;

public class Constant  extends BaseFunction {
    /*
     * 0. Var name
     * *1. Default value
     */
    @Override
    public void run(String functionParams, Object[] scriptParams, ScriptRunner runner) {
        Object[] params=parseAttributes(functionParams, runner);
        if(params.length>1){
            runner.variables.add(new Var(params[0].toString(), null, true, params[1]));
        }
        else if(params.length>0)
        {
            runner.variables.add(new Var(params[0].toString(), null, true));
            Start.mainRunner.mainLogger.warning("Variable.New.Constant recommended to use with initial value");
        }
        else{
            Start.mainRunner.mainLogger.error("Variable.New.Constant takes at least 1 argument, but zero given");
        }
    }
    
}
