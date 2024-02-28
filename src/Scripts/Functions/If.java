package Scripts.Functions;

import Scripts.ScriptRunner;
import Shared.Start;
import Shared.Tools;

public class If extends BaseFunction {

    @Override
    public void run(String functionParams, Object scriptParams, ScriptRunner runner) {
        Object[] params = parseAttributes(functionParams, runner);
        if (params.length > 1) {
            if (!(Tools.isBool(params[0].toString()) && Tools.isInteger(params[1].toString()) && Integer.parseInt(params[1].toString()) >= 0)) {
                Start.mainRunner.mainLogger.error("Error while branching: If took condition invalid argument");
                return;
            }
            if (!((boolean) params[0]))
                runner.line = Integer.parseInt(params[1].toString()) - 1;
        } else
            Start.mainRunner.mainLogger
                    .error("Error while branching: If takes 2 argument, but " + params.length + " given");
    }

}
