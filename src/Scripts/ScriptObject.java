package Scripts;

import java.util.ArrayList;

import Scripts.Types.ScriptClass;
import Scripts.Types.TypeRestriction;

public class ScriptObject {
    ArrayList<Var> attributes;
    public ScriptClass parent;

    public int size(){
        return attributes.size();
    }

    public ScriptObject() {
        this.attributes = new ArrayList<Var>();
        this.parent = null;
    }

    public ScriptObject(ScriptClass parent) {
        this.parent = parent;
        this.attributes= new ArrayList<Var>();
        for(Var var:parent.getAttributes()){
            this.attributes.add(new Var(var.name, var.type, var.constant, var.getData()));
        }
    }

    public Object getAttribute(String name){
        for (Var var : attributes) {
            if (var.name.equals(name))
                return var.getData();
        }
        return null;
    }

    public boolean hasAttribute(String name) {
        for (Var var : attributes) {
            if (var.name.equals(name))
                return true;
        }
        return false;
    }

    public void tryAddAttribute(String name) {
        tryAddAttribute(name, null, null, false, false);
    }

    public void tryAddAttribute(String name, TypeRestriction typeRestriction) {
        tryAddAttribute(name, null, typeRestriction, false, false);
    }

    public void tryAddAttribute(String name, Object value) {
        tryAddAttribute(name, value, null, false, false);
    }

    public void tryAddAttribute(String name, Object value, TypeRestriction typeRestriction) {
        tryAddAttribute(name, value, typeRestriction, false, false);
    }

    public void tryAddAttribute(String name, Object value, boolean constant) {
        tryAddAttribute(name, value, null, true, false);
    }

    public void tryAddAttribute(String name, Object value, TypeRestriction typeRestriction, boolean constant,
            boolean override) {
        if (!override && this.parent != null && this.parent.hasAttribute(name))
            return;
        if (!hasAttribute(name))
            createAttribute(name, value, typeRestriction, constant);
    }

    public void setAttribute(String name, Object value) {
        for (Var var : attributes) 
        {
            if (var.name.equals(name)) {
                var.trySetData(value);
                break;
            }
        }
    }

    private void createAttribute(String name, Object value, TypeRestriction typeRestriction, boolean constant) {
        attributes.add(new Var(name, typeRestriction, constant, value));
    }
}
