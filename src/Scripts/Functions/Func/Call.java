package Scripts.Functions.Func;

import java.util.Arrays;

import Scripts.ScriptFunc;
import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;
import Shared.Start;

public class Call extends BaseFunction {

    @Override
    public void run(String functionParams, Object[] scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        if (params.length > 0) {
            try {
                ((ScriptFunc) params[0]).call(Arrays.copyOfRange(params, 1, params.length), runner);
            } catch (Exception e) {
                Start.mainRunner.mainLogger
                        .error("Func.Call throw " + e.getClass().getSimpleName() + ": " + e.getLocalizedMessage());
            }
        } else
            Start.mainRunner.mainLogger.error("Func.Call takes at least one argument, but zero given");
    }

}
