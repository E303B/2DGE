package Scripts.Types;

import Shared.Tools;

public class Double extends TypeRestriction {

    @Override
    public boolean isAvailable(java.lang.Object value) {
        return value==null||Tools.isDouble(value.toString());
    }
}
