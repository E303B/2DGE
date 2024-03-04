package Scripts.Types;

public class String extends TypeRestriction {

    @Override
    public boolean isAvailable(java.lang.Object value) {
        return value == null || value.equals(value.toString());
    }

}
