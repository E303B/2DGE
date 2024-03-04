package Scripts.Types;

import Shared.Tools;

public class Byte extends TypeRestriction {
    @Override
    public boolean isAvailable(java.lang.Object value) {
        return value==null||Tools.isByte(value.toString());
    }
}
