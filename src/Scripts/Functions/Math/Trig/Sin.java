package Scripts.Functions.Math.Trig;

import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;

public class Sin extends BaseFunction {
    private Object trySin(Object a) {
        return Math.sin(Double.parseDouble(a.toString()));
    }
    /*
     * 0. Var name to store result
     * *1. Value to sin(if not given, returns sin of first var)
     */
    @Override
    public void run(String functionParams, Object[] scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        if (params.length > 1) {
            runner.setVar(params[0].toString(), trySin(params[1]));
        } else if (params.length > 0) {
            runner.setVar(params[0].toString(), trySin(runner.getVar(params[0].toString())));
        }
    }

}
