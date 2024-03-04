package Scripts.Functions.Math.Func;

import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;
import Shared.Start;
import Shared.Tools;

public class Floor extends BaseFunction {
    private Object tryFloor(Object a) {
        if (Tools.isShort(a.toString())) {
            return (short) Math.floor(Double.parseDouble(a.toString()));
        } else if (Tools.isInteger(a.toString())) {
            return (int) Math.floor(Double.parseDouble(a.toString()));
        } else if (Tools.isLong(a.toString())) {
            return (long) Math.floor(Double.parseDouble(a.toString()));
        } else if (Tools.isFloat(a.toString())) {
            return (float) Math.floor(Double.parseDouble(a.toString()));
        } else if (Tools.isDouble(a.toString())) {
            return (double) Math.floor(Double.parseDouble(a.toString()));
        }
        return null;
    }
    /*
     * 0. Var name to store result
     * *1. Value to floor(if not given, returns floor of first var)
     */
    @Override
    public void run(String functionParams, Object scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        if (params.length > 1) {
            Object i = tryFloor(params[1]);
            if (i == null)
                Start.mainRunner.mainLogger.error("Math.Func.Floor get invalid argument type");
            else
                runner.setVar(params[0].toString(), i);
        } else if (params.length > 0) {
            Object i = tryFloor(runner.getVar(params[0].toString()));
            if (i == null)
                Start.mainRunner.mainLogger.error("Math.Func.Floor get invalid argument type");
            else
                runner.setVar(params[0].toString(), i);
        }
    }

}
