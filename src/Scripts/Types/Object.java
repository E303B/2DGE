package Scripts.Types;

import Scripts.ScriptObject;

public class Object extends TypeRestriction {

    @Override
    public boolean isAvailable(java.lang.Object value) {
        return value==null||ScriptObject.class.isInstance(value);
    }
}
