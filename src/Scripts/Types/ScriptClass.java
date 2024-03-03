package Scripts.Types;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import Scripts.ScriptObject;
import Scripts.Var;

public class ScriptClass extends TypeRestriction {
    private ArrayList<Var> attributes;
    private ScriptClass parent;

    public ScriptClass(java.lang.String name) {
        this.name = name;
        this.attributes = new ArrayList<Var>();
        parent = null;

    }

    public ArrayList<Var> getAttributes() {
        return attributes;
    }

    @SuppressWarnings("unchecked")
    public ScriptClass(java.lang.String name, ScriptClass parent) {
        this.name = name;
        this.parent = parent;
        this.attributes = (ArrayList<Var>) parent.attributes.clone();
    }

    public void trySetAttribute(java.lang.String name, java.lang.Object value) {
        if (!hasAttribute(name))
            return;
        setAttribute(name, value);
    }

    public java.lang.Object getAttribute(java.lang.String name) {
        for (Var var : attributes) {
            if (var.name.equals(name))
                return var.getData();
        }
        return null;
    }

    public boolean hasAttribute(java.lang.String name) {
        for (Var var : attributes) {
            if (var.name.equals(name))
                return true;
        }
        return false;
    }

    public void tryAddAttribute(java.lang.String name) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        tryAddAttribute(name, null, Any.class.getConstructor().newInstance(), false, false);
    }

    public void tryAddAttribute(java.lang.String name, TypeRestriction typeRestriction) {
        tryAddAttribute(name, null, typeRestriction, false, false);
    }

    public void tryAddAttribute(java.lang.String name, java.lang.Object value) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        tryAddAttribute(name, value, Any.class.getConstructor().newInstance(), false, false);
    }

    public void tryAddAttribute(java.lang.String name, java.lang.Object value, TypeRestriction typeRestriction) {
        tryAddAttribute(name, value, typeRestriction, false, false);
    }

    public void tryAddAttribute(java.lang.String name, java.lang.Object value, boolean constant) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        tryAddAttribute(name, value, Any.class.getConstructor().newInstance(), true, false);
    }

    public void tryAddAttribute(java.lang.String name, java.lang.Object value, TypeRestriction typeRestriction,
            boolean constant, boolean override) {
        if (!override && this.parent != null && this.parent.hasAttribute(name))
            return;
        if (hasAttribute(name))
            setAttribute(name, value);
        else
            createAttribute(name, value, typeRestriction, constant);
    }

    private void setAttribute(java.lang.String name, java.lang.Object value) {
        for (Var var : attributes) {
            if (var.name == name) {
                var.trySetData(value);
                break;
            }
        }
    }

    private void createAttribute(java.lang.String name, java.lang.Object value, TypeRestriction typeRestriction,
            boolean constant) {
        attributes.add(new Var(name, typeRestriction, constant, value));
    }

    @Override
    public boolean isAvailable(java.lang.Object value) {
        return (value instanceof ScriptObject) && (((ScriptObject) value).parent == this);
    }
}
