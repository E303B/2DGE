package Scripts.Functions;

import Scripts.ScriptRunner;
import Shared.Start;

public class Param extends BaseFunction{
    /*
     * 0. Var name to store
     * 1. Param index
     */
    @Override
    public void run(String functionParams, Object[] scriptParams, ScriptRunner runner) {
       Object[] params=parseAttributes(functionParams, runner);
       if(scriptParams==null){
        Start.mainRunner.mainLogger.error("Called param, but script runner doesn`t get any");
        return;
       }
       if(params.length>1){
        try {
            runner.setVar(params[0].toString(), scriptParams[Integer.parseInt(params[1].toString())]);
        } catch (Exception e) {
            Start.mainRunner.mainLogger.error("Param throw "+e.getClass().getSimpleName()+": "+e.getLocalizedMessage());
        }
       }
       else Start.mainRunner.mainLogger.error("Param takes two argument, but "+params.length+" given");
    }
    
}
