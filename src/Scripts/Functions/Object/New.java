package Scripts.Functions.Object;

import Scripts.ScriptObject;
import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;
import Scripts.Types.ScriptClass;
import Shared.Start;

public class New extends BaseFunction {
    @Override
    public void run(String functionParams, Object scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        try {
            if (params.length > 1) {
                runner.setVar(params[1].toString(),
                        new ScriptObject((ScriptClass) runner.getType(params[0].toString())));
            } else if (params.length == 1) {
                runner.setVar(params[1].toString(),
                        new ScriptObject());
            } else
                Start.mainRunner.mainLogger.error("Object.New takes at least 1 argument, but zero given");

        } catch (Exception e) {
            Start.mainRunner.mainLogger
                    .error("Object.New throw " + e.getClass().getSimpleName() + ": " + e.getLocalizedMessage());
        }
    }
}