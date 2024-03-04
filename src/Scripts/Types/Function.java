package Scripts.Types;

import Scripts.ScriptFunc;

public class Function extends TypeRestriction{

    @Override
    public boolean isAvailable(java.lang.Object value) {
        return value==null||value instanceof ScriptFunc;
    }
    
}
