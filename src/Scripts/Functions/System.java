package Scripts.Functions;

import Scripts.ScriptRunner;
import Shared.Start;

public class System extends BaseFunction{

    @Override
    public void run(String functionParams, Object[] scriptParams, ScriptRunner runner) {
        Object[] params=parseAttributes(functionParams, runner);
        try {
            Start.mainRunner.mainSystem.call(params, runner);
        } catch (Exception e) {
            Start.mainRunner.mainLogger.error("Call "+params[0].toString()+" throw "+e.getClass().getSimpleName()+": "+e.getLocalizedMessage());
        }
    }
    
}
