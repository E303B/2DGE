package Scripts.Functions.Class;

import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;
import Scripts.Types.ScriptClass;
import Shared.Start;

public class New extends BaseFunction {
    /*
     * 0. Class name
     * *1. Class parent
     */
    @Override
    public void run(String functionParams, Object scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        try {
            if (params.length > 1) {
                runner.newType(
                        new ScriptClass(params[0].toString(), (ScriptClass) runner.getType(params[1].toString())));
            } else if (params.length == 1) {
                runner.newType(new ScriptClass(params[0].toString()));
            } else
                Start.mainRunner.mainLogger.error("Class.New takes at least 1 argument, but zero given");

        } catch (Exception e) {
            Start.mainRunner.mainLogger
                    .error("Class.New throw " + e.getClass().getSimpleName() + ": " + e.getLocalizedMessage());
        }
    }
}