package Scripts.Types;

import Scripts.ScriptObject;

public class Object extends TypeRestriction {
    public Object() {
        super();
        available = new java.lang.Class[1];
        available[0] = ScriptObject.class;
    }

    @Override
    public boolean isAvailable(java.lang.Object value) {
        return value==null||ScriptObject.class.isInstance(value);
    }
}
