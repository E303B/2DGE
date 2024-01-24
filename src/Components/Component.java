package Components;

import java.util.HashMap;

public abstract class Component {
    public Object[] parents = null;

    public Component(HashMap<String, Object> attributes){

    }

    public void tryOverrideAttribute(String name, Object value){
    }

    protected Object trySearchAttribute(String attribute, HashMap<String, Object> attributes) {
        if (attributes.keySet().contains(attribute)) {
            return attributes.get(attribute);
        }
        return null;
    }

    protected Object trySearchAttribute(String attribute, HashMap<String, Object> attributes, Object defaultValue) {
        if (attributes.keySet().contains(attribute)) {
            return attributes.get(attribute);
        }
        return defaultValue;
    }
}