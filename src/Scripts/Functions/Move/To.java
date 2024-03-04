package Scripts.Functions.Move;

import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;
import Shared.Start;
import Shared.Tools;

public class To extends BaseFunction {
    /*
     * 0. Line where to go(starts with 0)
     */
    @Override
    public void run(String functionParams, Object[] scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        if (params.length > 0) {
            if (Tools.isInteger(params[0].toString()) && (Integer.parseInt(params[0].toString()) >= 0)) {
                runner.line = Integer.parseInt(params[0].toString()) - 1;
            }
        }
        else Start.mainRunner.mainLogger.error("Move.To takes one argument, but zero given");
    }

}
