package Scripts.Types;

public class Any extends TypeRestriction {
    public Any(){
        available = new Class[1];
        available[0] = Object.class;
    }
}
