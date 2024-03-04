package Scripts;

import java.util.ArrayList;

public class ScriptFunc {
    private ArrayList<String> srcCode;
    public ScriptFunc(ArrayList<String> srcCode){
        this.srcCode=srcCode;
    }
    public void call(Object[] params, ScriptRunner runner){
        new ScriptRunner(srcCode, params, runner).runScript();
    }
}
