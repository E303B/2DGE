package Scripts.Types;

import Shared.Tools;

public class Long extends TypeRestriction {
    @Override
    public boolean isAvailable(java.lang.Object value) {
        return value==null||Tools.isLong(value.toString());
    }
}
