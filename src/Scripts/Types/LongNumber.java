package Scripts.Types;

import Shared.Tools;

public class LongNumber extends TypeRestriction {
    public LongNumber() {
        super();
        available = new java.lang.Class[2];
        available[0] = java.lang.Long.class;
        available[1] = java.lang.Double.class;
    }
    @Override
    public boolean isAvailable(java.lang.Object value) {
        return value==null||Tools.isLong(value.toString())||Tools.isDouble(value.toString());
    }
}
