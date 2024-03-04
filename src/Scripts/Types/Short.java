package Scripts.Types;

import Shared.Tools;

public class Short extends TypeRestriction {
    @Override
    public boolean isAvailable(java.lang.Object value) {
       return value==null||Tools.isShort(value.toString());
    }
}
