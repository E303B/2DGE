package Scripts.Types;

public class Number extends TypeRestriction{
    public Number(){
        available = new Class[2];
        available[0] = java.lang.Integer.class;
        available[1] = java.lang.Float.class;
    }
}
