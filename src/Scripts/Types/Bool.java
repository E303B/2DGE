package Scripts.Types;

public class Bool extends TypeRestriction{
    public Bool(){
        available = new Class[1];
        available[0] = java.lang.Boolean.class;
    }
}
