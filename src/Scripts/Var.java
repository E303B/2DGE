package Scripts;

import Scripts.Types.TypeRestriction;
import Shared.Start;

public class Var {
    public String name;
    protected Object data;
    public TypeRestriction type;
    public boolean constant;

    public Var(String name, TypeRestriction type, boolean constant) {
        this.name = name;
        this.type = type;
        this.constant = constant;
        data = null;
    }

    public Var(String name, TypeRestriction type, boolean constant, Object initialValue) {
        this.name = name;
        this.type = type;
        this.constant = constant;
        trySetData(initialValue);
    }

    public void trySetData(Object value) {
        if (data!=null&&constant) {
            Start.mainRunner.mainLogger.error("Unable to change constant variable value");
            return;
        }
        if(type!=null&&!type.isAvailable(value)){
            Start.mainRunner.mainLogger.error("Unable to set value");
            return;
        }
        data = value;
    }

    public Object getData() {
        return data;
    }
}
