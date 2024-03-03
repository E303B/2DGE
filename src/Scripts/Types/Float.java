package Scripts.Types;

import Shared.Tools;

public class Float extends TypeRestriction{
    public Float(){
        super();
        available = new java.lang.Class[1];
        available[0] = java.lang.Float.class;
    }
    @Override
    public boolean isAvailable(java.lang.Object value) {
        return value==null||Tools.isFloat(value.toString());
    }
}
