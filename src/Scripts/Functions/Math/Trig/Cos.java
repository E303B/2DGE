package Scripts.Functions.Math.Trig;

import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;

public class Cos extends BaseFunction {
    private Object tryCos(Object a) {
        return Math.cos(Double.parseDouble(a.toString()));
    }
    /*
     * 0. Var name to store result
     * *1. Value to cos(if not given, returns cos of first var)
     */
    @Override
    public void run(String functionParams, Object scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        if (params.length > 1) {
            runner.setVar(params[0].toString(), tryCos(params[1]));
        } else if (params.length > 0) {
            runner.setVar(params[0].toString(), tryCos(runner.getVar(params[0].toString())));
        }
    }

}
