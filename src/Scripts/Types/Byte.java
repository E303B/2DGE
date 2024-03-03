package Scripts.Types;

import Shared.Tools;

public class Byte extends TypeRestriction {
    public Byte() {
        super();
        available = new java.lang.Class[1];
        available[0] = java.lang.Byte.class;
    }
    @Override
    public boolean isAvailable(java.lang.Object value) {
        return value==null||Tools.isByte(value.toString());
    }
}
