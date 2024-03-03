package Scripts.Types;

public class String extends TypeRestriction {
    public String() {
        super();
        available = new java.lang.Class[1];
        available[0] = java.lang.String.class;
    }

    @Override
    public boolean isAvailable(java.lang.Object value) {
        return value == null || value.equals(value.toString());
    }

}
