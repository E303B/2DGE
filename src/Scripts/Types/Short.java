package Scripts.Types;

import Shared.Tools;

public class Short extends TypeRestriction {
    public Short() {
        super();
        available = new java.lang.Class[1];
        available[0] = java.lang.Short.class;
    }
    @Override
    public boolean isAvailable(java.lang.Object value) {
       return value==null||Tools.isShort(value.toString());
    }
}
