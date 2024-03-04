package Scripts.Functions.Move;

import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;
import Shared.Tools;

public class To extends BaseFunction {
    /*
     * How much line to go(negative value for backward, positive value for forward)
     */
    @Override
    public void run(String functionParams, Object scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        if (Tools.isInteger(params[0].toString()) && (Integer.parseInt(params[0].toString()) >= 0)) {
            runner.line=Integer.parseInt(params[0].toString())-1;
        }
    }

}
