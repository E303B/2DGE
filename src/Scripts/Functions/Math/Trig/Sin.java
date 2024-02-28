package Scripts.Functions.Math.Trig;

import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;
import Shared.Start;

public class Sin extends BaseFunction {
    private Object trySin(Object a) {
        return Math.sin(Double.parseDouble(a.toString()));
    }

    @Override
    public void run(String functionParams, Object scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        if (params.length > 1) {
            Object i = trySin(params[1]);
            if (i == null)
                Start.mainRunner.mainLogger.error("Math.Trig.Sin get invalid argument type");
            else
                runner.setVar(params[0].toString(), i);
        } else if (params.length > 0) {
            Object i = trySin(runner.getVar(params[0].toString()));
            if (i == null)
                Start.mainRunner.mainLogger.error("Math.Trig.Sin get invalid argument type");
            else
                runner.setVar(params[0].toString(), i);
        }
    }

}
