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
            if (temp.startsWith("\"") && temp.length() >= 3 && temp.endsWith("\"")) {
                result.add(temp.substring(1, temp.length() - 2));
                temp = "";
                pointer++;
            } else if (temp.startsWith("'") && temp.length() >= 3 && temp.endsWith("'")) {
                result.add(temp.substring(1, temp.length() - 2));
                temp = "";
                pointer++;
            } else if (temp.trim() != temp) {
                temp = temp.trim();
                if (Tools.isInteger(temp)) {
                    result.add(Integer.parseInt(temp));
                    temp = "";
                }
                else if (Tools.isFloat(temp)) {
                    result.add(Float.parseFloat(temp));
                    temp = "";
                }
                else if (Tools.isBool(temp)) {
                    result.add(Boolean.parseBoolean(temp));
                    temp = "";
                }
                else if (runner.hasVar(temp)){
                    result.add(runner.getVar(temp));
                    temp = "";
                }
            }
        }
        return result.toArray();
    }

    public abstract void run(String functionParams, Object scriptParams, ScriptRunner runner);
}
