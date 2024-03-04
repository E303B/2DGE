package Scripts.Functions.Math.Trig;

import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;

public class Acos extends BaseFunction {
    private Object tryAcos(Object a) {
        return Math.acos(Double.parseDouble(a.toString()));
    }
    /*
     * 0. Var name to store result
     * *1. Value to acos(if not given, returns acos of first var)
     */
    @Override
    public void run(String functionParams, Object[] scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        if (params.length > 1) {
            runner.setVar(params[0].toString(), tryAcos(params[1]));
        } else if (params.length > 0) {
            runner.setVar(params[0].toString(), tryAcos(runner.getVar(params[0].toString())));
        }
    }

}
