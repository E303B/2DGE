package Scripts.Types;

import Shared.Tools;

public class Integer extends TypeRestriction{
    public Integer(){
        super();
        available = new java.lang.Class[1];
        available[0] = java.lang.Integer.class;
    }
    @Override
    public boolean isAvailable(java.lang.Object value) {
        return value==null||Tools.isInteger(value.toString());
    }
}
