package Scripts.Types;

import java.util.ArrayList;

public abstract class TypeRestriction {
    private static ArrayList<TypeRestriction> types=new ArrayList<TypeRestriction>();
    @SuppressWarnings("rawtypes")
    protected ArrayList<Class> available;
    @SuppressWarnings("rawtypes")
    public TypeRestriction() {
        available = new ArrayList<Class>();
    }

    public static TypeRestriction getType(String name){
        for(TypeRestriction t:types){
            if(t.getClass().getSimpleName().equals(name))return t;
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
    public final boolean isAvailable(Object value) {
        for (Class c : available) {
            if (c.isInstance(value))
                return true;
        }
        return false;
    }
}
