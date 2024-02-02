package Components;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Node;

public abstract class Component {
    public ArrayList<Object> parents = null;
    protected HashMap<String, Object> attributes;

    public Component(Node attributes) {
        this.attributes = getAttributesForNode(attributes);
    }

    public Component() {
        this.attributes = new HashMap<String, Object>();
    }

    protected final HashMap<String, Object> getAttributesForNode(Node node) {
        HashMap<String, Object> result = new HashMap<>();
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            result.put(node.getChildNodes().item(i).getLocalName(), node.getChildNodes().item(i).getTextContent());
        }
        return result;
    }

    public void tryOverrideAttribute(String name, Object value) {
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

    public static final Class<Component> getComponentClass(String name) throws ClassNotFoundException {
        return (Class<Component>) Class.forName(name);
    }
}
