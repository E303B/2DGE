package Scripts.Types;

import Shared.Tools;

public class Double extends TypeRestriction {
    public Double() {
        super();
        available = new java.lang.Class[1];
        available[0] = java.lang.Double.class;
    }

    @Override
    public boolean isAvailable(java.lang.Object value) {
        return value==null||Tools.isDouble(value.toString());
    }
}
