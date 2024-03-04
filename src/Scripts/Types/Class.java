package Scripts.Types;

public class Class extends TypeRestriction {
    @Override
    public boolean isAvailable(java.lang.Object value) {
        return value==null||ScriptClass.class.isInstance(value);
    }
}
