package Scripts;

import java.util.ArrayList;

import Scripts.Packages.BasePackage;
import Scripts.Packages.Console;
import Shared.Tools;

public final class ScriptRunner {
    private ArrayList<BasePackage> defaultPackages;
    public int line;
    public ArrayList<String> lines;
    public ArrayList<Variable> variables;

    public boolean hasVar(String name) {
        for (Variable variable : variables) {
            if (variable.name == name)
                return true;
        }
        return false;
    }

    public Object getVar(String name) {
        for (Variable variable : variables) {
            if (variable.name == name)
                return variable.getData();
        }
        return null;
    }

    private void initPackages() {
        defaultPackages = new ArrayList<BasePackage>();
        defaultPackages.add(new Console());
    }

    public ScriptRunner(String srcCode, Object params) {
        initPackages();
        line = 0;
        lines = Tools.splitBy(srcCode, "\n");
        variables = new ArrayList<Variable>();
        while (line < lines.size()) {
            for (BasePackage basePackage : defaultPackages) {
                if (lines.get(line).trim().startsWith(basePackage.name)) {
                    basePackage.tryRun(lines.get(line).trim().substring(basePackage.name.length()+1), params, this);
                    break;
                }
            }
            line++;
        }
    }
}
