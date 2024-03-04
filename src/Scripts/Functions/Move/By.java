package Scripts.Functions.Move;

import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;
import Shared.Tools;

public class By extends BaseFunction {
    /*
     * 0. Line where to go(starts with 0)
     */
    @Override
    public void run(String functionParams, Object scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        if (Tools.isInteger(params[0].toString()) && (Integer.parseInt(params[0].toString())+runner.line)>=0) {
            runner.line+=Integer.parseInt(params[0].toString());
        }
    }

}
