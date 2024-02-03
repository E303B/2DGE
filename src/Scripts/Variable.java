package Scripts;

import Scripts.Types.TypeRestriction;

public class Variable {
    public String name;
    protected Object data;
    public TypeRestriction type;
    public void trySetData(Object value){

    }
    public Object getData(){
        return data;
    }
}
