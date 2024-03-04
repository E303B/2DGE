package Scripts.Types;

import Shared.Tools;

public class Number extends TypeRestriction {

    @Override
    public boolean isAvailable(java.lang.Object value) {
        return value == null || Tools.isInteger(value.toString()) || Tools.isFloat(value.toString());
    }
}
