package Scripts.Types;

public abstract class TypeRestriction {
    public TypeRestriction(){
        this.name=this.getClass().getSimpleName();
    }
    public java.lang.String name;

    public abstract boolean isAvailable(java.lang.Object value);
}
