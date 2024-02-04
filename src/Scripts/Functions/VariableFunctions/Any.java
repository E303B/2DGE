package Scripts.Functions.VariableFunctions;

import Scripts.ScriptRunner;
import Scripts.Var;
import Scripts.Functions.BaseFunction;
import Shared.Start;

public class Any extends BaseFunction {

    @Override
    public void run(String functionParams, Object scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        if (params.length > 1) {
            runner.variables.add(new Var((String) params[0], null, false, params[1]));
        } else if (params.length == 1)
            runner.variables.add(new Var((String) params[0], null, false));
        else
            Start.mainRunner.mainLogger.error("Variable.New.Any takes at least 1 argument, but zero given");

    }

}