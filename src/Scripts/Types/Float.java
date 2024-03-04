package Scripts.Types;

import Shared.Tools;

public class Float extends TypeRestriction{
    @Override
    public boolean isAvailable(java.lang.Object value) {
        return value==null||Tools.isFloat(value.toString());
    }
}
