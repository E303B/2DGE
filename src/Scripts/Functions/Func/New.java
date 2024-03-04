package Scripts.Functions.Func;

import java.util.ArrayList;

import Scripts.ScriptFunc;
import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;
import Shared.Start;
import Shared.Tools;

public class New extends BaseFunction {
    /*
     * 0. Var to store func
     * 1. Last line of func(last line exclusive)
     */
    @Override
    public void run(String functionParams, Object[] scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        if (params.length > 1) {
            if (Tools.isInteger(params[1].toString()) && (Integer.parseInt(params[1].toString()) > runner.line)) {
                ArrayList<String> lines = new ArrayList<String>(
                        runner.lines.subList(runner.line + 1, (Integer.parseInt(params[1].toString()))));
                runner.setVar(params[0].toString(), new ScriptFunc(lines));
                runner.line = Integer.parseInt(params[1].toString()) - 1;
            }
        } else
            Start.mainRunner.mainLogger.error("Func.New takes two arguments, but " + params.length + " given");
    }

}
