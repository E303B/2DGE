package Scripts.Functions.Math.Trig;

import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;

public class Tan extends BaseFunction {
    private Object tryTan(Object a) {
        return Math.tan(Double.parseDouble(a.toString()));
    }
    /*
     * 0. Var name to store result
     * *1. Value to tan(if not given, returns tan of first var)
     */
    @Override
    public void run(String functionParams, Object scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        if (params.length > 1) {
            runner.setVar(params[0].toString(), tryTan(params[1]));
        } else if (params.length > 0) {
            runner.setVar(params[0].toString(), tryTan(runner.getVar(params[0].toString())));
        }
    }

}
