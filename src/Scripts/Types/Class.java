package Scripts.Types;

public class Class extends TypeRestriction {
    public Class() {
        super();
        available = new java.lang.Class[1];
        available[0] = ScriptClass.class;
    }
    @Override
    public boolean isAvailable(java.lang.Object value) {
        return value==null||ScriptClass.class.isInstance(value);
    }
}
