package Components;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import Scripts.ScriptRunner;

public abstract class Component {
    protected ArrayList<Object> parents = new ArrayList<Object>();
    protected HashMap<String, Object> attributes;
    protected Node attributeNode;

    public ArrayList<Object> getParents() {
        return parents;
    }

    public abstract void call(Object[] params, ScriptRunner runner);

    public Component(Node attributes) {
        this.parents = new ArrayList<Object>();
        this.attributes = getAttributesForNode(attributes);
        attributeNode = attributes;
        init();
    }

    public Component() {
        this.parents = new ArrayList<Object>();
        this.attributes = new HashMap<String, Object>();
        attributeNode = null;
        init();
    }

    public abstract void init();

    public final Component copy() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException, SecurityException {
        return attributeNode == null ? this.getClass().getConstructor().newInstance()
                : this.getClass().getConstructor(Node.class).newInstance(attributeNode);
    }

    protected final HashMap<String, Object> getAttributesForNode(Node node) {
        HashMap<String, Object> result = new HashMap<>();
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE)
                result.put(((Element) node.getChildNodes().item(i)).getNodeName(),
                        node.getChildNodes().item(i).getTextContent());
        }
        return result;
    }

    public void tryOverrideAttribute(String name, Object value) {
    }

    public Object getAttribute(String key, Object defaultValue) {
        Object value = this.attributes.get(key);
        return value == null ? defaultValue : value;
    }

    @SuppressWarnings({ "unchecked" })
    public static final Class<Component> getComponentClass(String name) throws ClassNotFoundException {
        return (Class<Component>) Class.forName(name);
    }
}
