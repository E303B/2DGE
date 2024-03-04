package Scripts.Functions.Console;

import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;
import Shared.Start;

public class Log extends BaseFunction {
    /*
     * 0. Value to log
     */
    @Override
    public void run(String functionParams, Object scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        if (params.length > 0)
            Start.mainRunner.mainLogger.log(params[0].toString());
        else
            Start.mainRunner.mainLogger.error("Console.Log should be used with one argument, but zero given");
    }
}
