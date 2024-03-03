package Scripts.Types;

public abstract class TypeRestriction {
    public TypeRestriction(){
        this.name=this.getClass().getSimpleName();
    }
    public java.lang.String name;
    @SuppressWarnings("rawtypes")
    protected static java.lang.Class[] available = {};

    public abstract boolean isAvailable(java.lang.Object value);
}
