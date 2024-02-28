package Scripts.Functions;

import java.util.ArrayList;

import Scripts.ScriptRunner;
import Shared.Tools;

public abstract class BaseFunction {

    public static Object[] parseAttributes(String s, ScriptRunner runner) {
        ArrayList<Object> result = new ArrayList<Object>();
        String temp = "";
        for (int pointer = 0; pointer < s.length(); pointer++) {
            temp += s.charAt(pointer);
            Object parsed = Tools.parseValue(temp, runner.convertVariablesToHashMap());
            if (parsed != null) {
                result.add(parsed);
                if (temp.startsWith("\"") || temp.startsWith("'"))
                    pointer++;
                temp = "";
            }
        }
        if (!temp.isEmpty()) {
            Object parsed = Tools.parseValue(temp, runner.convertVariablesToHashMap(), true);
            if (parsed != null)
                result.add(parsed);
        }
        return result.toArray();
    }

    public abstract void run(String functionParams, Object scriptParams, ScriptRunner runner);
}
