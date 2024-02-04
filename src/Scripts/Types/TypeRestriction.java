package Scripts.Types;

public abstract class TypeRestriction {
    @SuppressWarnings("rawtypes")
    final static Class[] types = {
            Any.class,
            String.class,
            Bool.class,
            Float.class,
            Integer.class,
            Number.class,
            Long.class,
            LongNumber.class,
            Short.class,
            Byte.class,
            Double.class
    };
    @SuppressWarnings("rawtypes")
    protected static Class[] available = {};

    @SuppressWarnings("rawtypes")
    public static final Class getType(java.lang.String name) {
        for (Class t : types) {
            if (t.getSimpleName().toString().equals(name)) {
                return t;
            }
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
    public final boolean isAvailable(Object value) {
        if (available.length < 1)
            return true;
        for (Class c : available) {
            if (c.isInstance(value))
                return true;
        }
        return false;
    }
}
