package Scripts.Functions.String;

import Scripts.ScriptObject;
import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;
import Shared.Start;

public class ToObject extends BaseFunction {

    @Override
    public void run(String functionParams, Object[] scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        if (params.length > 1) {
            ScriptObject object = new ScriptObject();
            int i = 0;
            for (char c : params[1].toString().toCharArray()) {
                object.tryAddAttribute(java.lang.String.valueOf(i), Character.toString(c));
                i++;
            }
            runner.setVar(params[0].toString(), object);
        } else
            Start.mainRunner.mainLogger.error("String.ToObject takes 2 arguments, but " + params.length + " given");
    }

}
