package Scripts.Types;

import Shared.Tools;

public class Integer extends TypeRestriction{
    @Override
    public boolean isAvailable(java.lang.Object value) {
        return value==null||Tools.isInteger(value.toString());
    }
}
