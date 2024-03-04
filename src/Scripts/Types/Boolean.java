package Scripts.Types;

import Shared.Tools;

public class Boolean extends TypeRestriction{

    @Override
    public boolean isAvailable(java.lang.Object value) {
        return value==null||Tools.isBool(value.toString());
    }
}
