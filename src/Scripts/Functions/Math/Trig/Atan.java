package Scripts.Functions.Math.Trig;

import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;

public class Atan extends BaseFunction {
    private Object tryAtan(Object a) {
        return Math.atan(Double.parseDouble(a.toString()));
    }
    /*
     * 0. Var name to store result
     * *1. Value to atan(if not given, returns atan of first var)
     */
    @Override
    public void run(String functionParams, Object scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        if (params.length > 1) {
            runner.setVar(params[0].toString(), tryAtan(params[1]));
        } else if (params.length > 0) {
            runner.setVar(params[0].toString(), tryAtan(runner.getVar(params[0].toString())));
        }
    }

}
