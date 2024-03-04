package Scripts.Functions.Class;

import java.lang.reflect.InvocationTargetException;

import Scripts.ScriptRunner;
import Scripts.Functions.BaseFunction;
import Scripts.Types.ScriptClass;
import Shared.Start;

public class Set extends BaseFunction {
    /*
     * 0. Class name
     * 1. Attribute name
     * 2. Value
     */
    @Override
    public void run(String functionParams, Object scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        if (params.length > 2) {
            try {
                ((ScriptClass) runner.getType(params[0].toString())).trySetAttribute(params[1].toString(), params[2]);
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                Start.mainRunner.mainLogger
                        .error("Class.Set throw " + e.getClass().getSimpleName() + ": " + e.getLocalizedMessage());
            }
        } else
            Start.mainRunner.mainLogger.error("Class.Set takes 3 argument, but " + params.length + " given");
    }

}
