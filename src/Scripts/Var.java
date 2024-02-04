package Scripts;

import java.lang.reflect.InvocationTargetException;
import Shared.Start;

import Scripts.Types.TypeRestriction;

public class Var {
    public String name;
    protected Object data;
    @SuppressWarnings("rawtypes")
    public Class type;
    public boolean constant;

    public Var(String name, @SuppressWarnings("rawtypes") Class type, boolean constant) {
        this.name = name;
        this.type = type;
        this.constant = constant;
        data = null;
    }

    public Var(String name, @SuppressWarnings("rawtypes") Class type, boolean constant, Object initialValue) {
        this.name = name;
        this.type = type;
        this.constant = constant;
        trySetData(initialValue);
    }

    @SuppressWarnings({ "unchecked"})
    public void trySetData(Object value) {
        if (data != null && constant) {
            Start.mainRunner.mainLogger.error("Unable to change constant variable value");
            return;
        }
        if (type != null) {
            try {
                if (!((TypeRestriction) type.getConstructor().newInstance()).isAvailable(value)) {
                    Start.mainRunner.mainLogger.error("Unable to set value");
                    return;
                }
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                e.printStackTrace();
                return;
            }
        }
        data = value;
    }

    public Object getData() {
        return data;
    }
}
