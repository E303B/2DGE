package Scripts.Functions.Move;

import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;
import Shared.Start;
import Shared.Tools;

public class By extends BaseFunction {
    /*
     * 0.How much line to go(negative value for backward, positive value for
     * forward)
     */
    @Override
    public void run(String functionParams, Object[] scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        if (params.length > 0) {
            if (Tools.isInteger(params[0].toString()) && (Integer.parseInt(params[0].toString()) + runner.line) >= 0) {
                runner.line += Integer.parseInt(params[0].toString());
            }
        }
        else Start.mainRunner.mainLogger.error("Move.By takes one argument, but zero given");
    }

}
