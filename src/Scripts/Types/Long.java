package Scripts.Types;

import Shared.Tools;

public class Long extends TypeRestriction {
    public Long() {
        super();
        available = new java.lang.Class[1];
        available[0] = java.lang.Long.class;
    }
    @Override
    public boolean isAvailable(java.lang.Object value) {
        return value==null||Tools.isLong(value.toString());
    }
}
