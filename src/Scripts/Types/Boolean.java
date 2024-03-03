package Scripts.Types;

import Shared.Tools;

public class Boolean extends TypeRestriction{
    public Boolean(){
        super();
        available = new java.lang.Class[1];
        available[0] = java.lang.Boolean.class;
    }

    @Override
    public boolean isAvailable(java.lang.Object value) {
        return value==null||Tools.isBool(value.toString());
    }
}
