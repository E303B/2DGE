package Scripts.Functions.VariableFunctions;

import Scripts.ScriptRunner;
import Scripts.Var;
import Scripts.Functions.BaseFunction;
import Shared.Start;

public class Typed extends BaseFunction {
    /*
     * 0. Var name
     * 1. Var type
     * *2. Default value
     */
    @Override
    public void run(String functionParams, Object[] scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        try {
            if (params.length > 2) {
                runner.variables
                .add(new Var(params[0].toString(), runner.getType(params[1].toString()), false, params[2]));
            } else if (params.length > 1) {
                runner.variables.add(new Var(params[0].toString(), runner.getType(params[1].toString()), false));
            } else
                Start.mainRunner.mainLogger
                        .error("Variable.New.Any takes at least 2 arguments, but " + params.length + " given");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
