package Scripts.Functions.Math;

import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;
import Shared.Start;
import Shared.Tools;

public class Mod extends BaseFunction {

    public Object tryMod(Object a, Object b) {
        String astr = a.toString();
        String bstr = b.toString();
        if (Tools.isLong(astr) && Tools.isLong(bstr)) {
            if (Tools.isShort(astr) && Tools.isShort(bstr)) {
                return (short) (Short.parseShort(astr) % Short.parseShort(bstr));
            } else if (Tools.isInteger(astr) && Tools.isInteger(bstr)) {
                return (int) (Integer.parseInt(astr) % Integer.parseInt(bstr));
            } else if (Tools.isLong(astr) && Tools.isLong(bstr)) {
                return (long) (Long.parseLong(astr) % Long.parseLong(bstr));
            }
        } else if (Tools.isDouble(astr) || Tools.isDouble(bstr)) {
            if (Tools.isFloat(astr) && Tools.isFloat(bstr)) {
                return (float) (Float.parseFloat(astr) % Float.parseFloat(bstr));
            } else if (Tools.isDouble(astr) && Tools.isDouble(bstr)) {
                return (double) (Double.parseDouble(astr) % Double.parseDouble(bstr));
            }
        }
        return null;
    }
    /*
     * 0. Var name to store result
     * 1. First arg
     * *2. Optimal arg(if it given, returns first arg % this var, else returns var name % first arg)
     */
    @Override
    public void run(String functionParams, Object scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        if (params.length > 2) {
            Object i = tryMod(params[1], params[2]);
            if (i == null)
                Start.mainRunner.mainLogger.error("Math.Mod get invalid argument type");
            else
                runner.setVar(params[0].toString(), i);
        } else if (params.length == 2) {
            Object i = tryMod(params[0], params[1]);
            if (i == null)
                Start.mainRunner.mainLogger.error("Math.Mod get invalid argument type");
            else
                runner.setVar(params[0].toString(), i);
        } else
            Start.mainRunner.mainLogger
                    .error("Math.Mod should be used with at least 2 argument, but " + params.length + " given");
    }

}
