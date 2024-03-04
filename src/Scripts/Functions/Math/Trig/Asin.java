package Scripts.Functions.Math.Trig;

import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;

public class Asin extends BaseFunction {
    private Object tryAsin(Object a) {
        return Math.asin(Double.parseDouble(a.toString()));
    }
    /*
     * 0. Var name to store result
     * *1. Value to asin(if not given, returns asin of first var)
     */
    @Override
    public void run(String functionParams, Object[] scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        if (params.length > 1) {
            runner.setVar(params[0].toString(), tryAsin(params[1]));
        } else if (params.length > 0) {
            runner.setVar(params[0].toString(), tryAsin(runner.getVar(params[0].toString())));
        }
    }

}
