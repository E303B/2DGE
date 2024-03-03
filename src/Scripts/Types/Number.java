package Scripts.Types;

import Shared.Tools;

public class Number extends TypeRestriction {
    public Number() {
        super();
        available = new java.lang.Class[2];
        available[0] = java.lang.Integer.class;
        available[1] = java.lang.Float.class;
    }

    @Override
    public boolean isAvailable(java.lang.Object value) {
        return value == null || Tools.isInteger(value.toString()) || Tools.isFloat(value.toString());
    }
}
