package Scripts.Functions.Math.Trig;

import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;
import Shared.Start;

public class Cos extends BaseFunction {
    private Object tryCos(Object a) {
        return Math.cos(Double.parseDouble(a.toString()));
    }

    @Override
    public void run(String functionParams, Object scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        if (params.length > 1) {
            Object i = tryCos(params[1]);
            if (i == null)
                Start.mainRunner.mainLogger.error("Math.Trig.Cos get invalid argument type");
            else
                runner.setVar(params[0].toString(), i);
        } else if (params.length > 0) {
            Object i = tryCos(runner.getVar(params[0].toString()));
            if (i == null)
                Start.mainRunner.mainLogger.error("Math.Trig.Cos get invalid argument type");
            else
                runner.setVar(params[0].toString(), i);
        }
    }

}
