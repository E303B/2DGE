package Scripts.Types;

public class Any extends TypeRestriction {
    public Any(){
        super();
        available = new java.lang.Class[1];
        available[0] = Object.class;
    }

    @Override
    public boolean isAvailable(java.lang.Object value) {
        return true;
    }
}
