package Scripts.Functions;

import Scripts.ScriptRunner;
import Shared.Start;

public class Log extends BaseFunction {
    @Override
    public void run(String functionParams, Object scriptParams, ScriptRunner runner) {
        Start.mainRunner.mainLogger.log((String) parseAttributes(functionParams, runner)[0]);
    }
}
