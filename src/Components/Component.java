package Components;

import java.util.HashMap;

public abstract class Component {
    String name = "";
    public Object[] parents = null;

    public abstract void loadComponent(HashMap<String, Object> attributes);

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
