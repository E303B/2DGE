package Scripts.Functions.Console;

import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;
import Shared.Start;

public class Warn extends BaseFunction {
    /*
     * 0. Value to warn
     */
    @Override
    public void run(String functionParams, Object scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        if (params.length > 0)
            Start.mainRunner.mainLogger.warning(params[0].toString());
        else
            Start.mainRunner.mainLogger.error("Console.Warn should be used with one argument, but zero given");
    }

}
