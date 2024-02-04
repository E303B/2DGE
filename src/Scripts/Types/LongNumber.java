package Scripts.Types;

public class LongNumber extends TypeRestriction {
    public LongNumber() {
        available = new Class[2];
        available[0] = java.lang.Long.class;
        available[1] = java.lang.Double.class;
    }
}
