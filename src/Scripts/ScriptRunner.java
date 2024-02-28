package Scripts;

import java.util.ArrayList;
import java.util.HashMap;

import Scripts.Functions.BaseFunction;
import Scripts.Packages.BasePackage;
import Shared.Tools;

public final class ScriptRunner implements Runnable {
    private ArrayList<BasePackage> defaultPackages;
    private ArrayList<BaseFunction> defaultFunctions;
    public int line;
    public ArrayList<String> lines;
    public ArrayList<Var> variables;
    private Object params;

    public HashMap<String, Object> convertVariablesToHashMap() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        for (Var var : variables)
            result.put(var.name, var.data);
        return result;
    }

    public boolean hasVar(String name) {
        for (Var variable : variables) {
            if (variable.name.equals(name))
                return true;
        }
        return false;
    }

    public void setVar(String name, Object value) {
        for (Var variable : variables) {
            if (variable.name.equals(name))
                variable.trySetData(value);
        }
    }

    public Object getVar(String name) {
        for (Var variable : variables) {
            if (variable.name.equals(name))
                return variable.getData();
        }
        return null;
    }

    private void initPackages() {
        defaultPackages = new ArrayList<BasePackage>();
        defaultFunctions = new ArrayList<BaseFunction>();
        defaultPackages.add(new Scripts.Packages.Console());
        defaultPackages.add(new Scripts.Packages.Variable());
        defaultPackages.add(new Scripts.Packages.Math());
        defaultPackages.add(new Scripts.Packages.Move());
        defaultPackages.add(new Scripts.Packages.Bool());

        defaultFunctions.add(new Scripts.Functions.If());
    }

    public ScriptRunner(String srcCode, Object params) {
        initPackages();
        lines = Tools.splitBy(srcCode, "\n");
        this.params = params;

    }

    public void runScript() {

        line = 0;
        variables = new ArrayList<Var>();
        while (line < lines.size()) {
            for(BaseFunction baseFunction: defaultFunctions){
                if (lines.get(line).trim().startsWith("//"))
                    continue;
                if (lines.get(line).trim().startsWith(baseFunction.getClass().getSimpleName())) {
                    baseFunction.run(
                            lines.get(line).trim().substring(baseFunction.getClass().getSimpleName().length() + 1),
                            params, this);
                    break;
                }
            }
            for (BasePackage basePackage : defaultPackages) {
                if (lines.get(line).trim().startsWith("//"))
                    continue;
                if (lines.get(line).trim().startsWith(basePackage.getClass().getSimpleName())) {
                    basePackage.tryRun(
                            lines.get(line).trim().substring(basePackage.getClass().getSimpleName().length() + 1),
                            params, this);
                    break;
                }
            }
            line++;
        }
    }

    @Override
    public void run() {
        runScript();
    }
}
