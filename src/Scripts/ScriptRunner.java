package Scripts;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import Scripts.Functions.BaseFunction;
import Scripts.Packages.BasePackage;
import Scripts.Types.TypeRestriction;
import Shared.Tools;

public final class ScriptRunner implements Runnable {
    private ArrayList<BasePackage> defaultPackages;
    private ArrayList<BaseFunction> defaultFunctions;
    public int line;
    public ArrayList<java.lang.String> lines;
    public ArrayList<Var> variables;
    public ArrayList<TypeRestriction> customTypeRestrictions;
    @SuppressWarnings("rawtypes")
    public java.lang.Class[] defaultTypeRestrictions = {
            Scripts.Types.Any.class,
            Scripts.Types.String.class,
            Scripts.Types.Boolean.class,
            Scripts.Types.Float.class,
            Scripts.Types.Integer.class,
            Scripts.Types.Number.class,
            Scripts.Types.Long.class,
            Scripts.Types.LongNumber.class,
            Scripts.Types.Short.class,
            Scripts.Types.Byte.class,
            Scripts.Types.Double.class,
            Scripts.Types.Object.class,
            Scripts.Types.Class.class
    };;
    private java.lang.Object[] params;

    private ScriptRunner parent;

    public HashMap<java.lang.String, java.lang.Object> convertVariablesToHashMap() {
        HashMap<java.lang.String, java.lang.Object> result = new HashMap<java.lang.String, java.lang.Object>();
        for (Var var : variables)
            result.put(var.name, var.data);
        return result;
    }

    public boolean hasVar(java.lang.String name) {
        if (parent == null) {
            for (Var variable : variables) {
                if (variable.name.equals(name))
                    return true;
            }
            return false;
        } else
            return parent.hasVar(name);
    }

    public void setVar(java.lang.String name, java.lang.Object value) {
        if (parent == null) {
            for (Var variable : variables) {
                if (variable.name.equals(name))
                    variable.trySetData(value);
            }
        } else
            parent.setVar(name, value);
    }

    public void deleteVar(String name) {
        if (parent == null) {
            for (Var var : variables) {
                if(var.name.equals(name)){
                    variables.remove(var);
                    return;
                }
            }
        }
        else parent.deleteVar(name);
    }

    public java.lang.Object getVar(java.lang.String name) {
        if (parent == null) {
            for (Var variable : variables) {
                if (variable.name.equals(name))
                    return variable.getData();
            }
            return null;
        } else
            return parent.getVar(name);
    }

    private void initPackages() {
        defaultPackages = new ArrayList<BasePackage>();
        defaultFunctions = new ArrayList<BaseFunction>();
        defaultPackages.add(new Scripts.Packages.Console());
        defaultPackages.add(new Scripts.Packages.Variable.Variable());
        defaultPackages.add(new Scripts.Packages.Math.Math());
        defaultPackages.add(new Scripts.Packages.Move());
        defaultPackages.add(new Scripts.Packages.Bool());
        defaultPackages.add(new Scripts.Packages.Class.Class());
        defaultPackages.add(new Scripts.Packages.Object.Object());
        defaultPackages.add(new Scripts.Packages.Func());

        defaultFunctions.add(new Scripts.Functions.If());
        defaultFunctions.add(new Scripts.Functions.Param());
        defaultFunctions.add(new Scripts.Functions.System());
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public boolean hasType(java.lang.String name) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        if (parent == null) {
            for (Class type : defaultTypeRestrictions) {
                if (((TypeRestriction) type.getConstructor().newInstance()).name == name) {
                    return true;
                }
            }
            for (TypeRestriction type : customTypeRestrictions) {
                if (type.name == name) {
                    return true;
                }
            }
        } else
            parent.hasType(name);
        return false;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public TypeRestriction getType(java.lang.String name) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        if (parent == null) {
            for (Class type : defaultTypeRestrictions) {
                if (((TypeRestriction) type.getConstructor().newInstance()).name.equals(name)) {
                    return (TypeRestriction) type.getConstructor().newInstance();
                }
            }
            for (TypeRestriction type : customTypeRestrictions) {
                if (type.name.equals(name)) {
                    return type;
                }
            }
        } else
            return parent.getType(name);
        return null;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Class getTypeClass(java.lang.String name) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        if (parent == null) {
            for (Class type : defaultTypeRestrictions) {
                if (((TypeRestriction) type.getConstructor().newInstance()).name == name) {
                    return type;
                }
            }
            for (TypeRestriction type : customTypeRestrictions) {
                if (type.name == name) {
                    return type.getClass();
                }
            }
        } else
            return parent.getTypeClass(name);
        return null;
    }

    public void newType(TypeRestriction type) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        if (parent == null) {
            if (hasType(type.name))
                return;
            this.customTypeRestrictions.add(type);
        } else
            parent.newType(type);
    }

    public ScriptRunner(java.lang.String srcCode, java.lang.Object[] params) {
        initPackages();
        customTypeRestrictions = new ArrayList<TypeRestriction>();
        lines = Tools.splitBy(srcCode, "\n");
        variables = new ArrayList<Var>();
        this.params = params;
        parent = null;

    }

    public ScriptRunner(ArrayList<java.lang.String> srcCode, java.lang.Object[] params) {
        initPackages();
        customTypeRestrictions = new ArrayList<TypeRestriction>();
        lines = srcCode;
        variables = new ArrayList<Var>();
        this.params = params;
        parent = null;

    }

    public ScriptRunner(java.lang.String srcCode, java.lang.Object[] params, ScriptRunner parent) {
        initPackages();
        customTypeRestrictions = parent.customTypeRestrictions;
        lines = Tools.splitBy(srcCode, "\n");
        variables = parent.variables;
        this.params = params;
        this.parent = parent;
    }

    public ScriptRunner(ArrayList<java.lang.String> srcCode, java.lang.Object[] params, ScriptRunner parent) {
        initPackages();
        customTypeRestrictions = parent.customTypeRestrictions;
        lines = srcCode;
        variables = parent.variables;
        this.params = params;
        this.parent = parent;
    }

    public void runScript() {
        line = 0;
        while (line < lines.size()) {
            for (BaseFunction baseFunction : defaultFunctions) {
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
