package Prototypes;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import Components.Component;
import Shared.Start;
import Types.BaseInstance;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Prototype {
    public ArrayList<Component> components;
    public String id;
    public Prototype parent;
    public Class<BaseInstance> assignedType;

    protected HashMap<String, Object> getAttributesForNode(Element node) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element child = (Element) node.getChildNodes().item(i);
                result.put(child.getTagName(), child.getChildNodes());
            }
        }
        return result;
    }

    @SuppressWarnings({ "unchecked" })
    public final void overideComponents(NodeList overrideComponents) {
        for (int i = 0; i < overrideComponents.getLength(); i++) {
            Element component = (Element) overrideComponents.item(i);
            try {
                if (!hasComponent((Class<Component>) Class.forName(component.getTagName()))) {

                    tryAddComponent((Component) Class.forName(component.getTagName()).getConstructor(Node.class)
                            .newInstance(getAttributesForNode(component)));

                    continue;
                }
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException
                    | ClassNotFoundException e) {
                e.printStackTrace();
                continue;
            }
            Component assignedComponent = getComponent(component.getTagName());
            for (int j = 0; j < component.getChildNodes().getLength(); j++) {
                Node attribute = component.getChildNodes().item(j);
                assignedComponent.tryOverrideAttribute(attribute.getNodeName(), attribute.getNodeValue());
            }
        }
    }

    protected final ArrayList<Component> loadClassComponents(String name) {
        if (Start.mainRunner.mainSystem.prototypeManager.hasPrototype(name))
            return Start.mainRunner.mainSystem.prototypeManager.getPrototype(name).components;
        return null;
    }

    protected final void loadComponents(NodeList components) {
        for (int i = 0; i < components.getLength(); i++) {
            Node component = components.item(i);
            try {
                if (component.getNodeType() == Node.ELEMENT_NODE) {
                    String name = ((Element) component).getTagName();
                    try {
                        this.components.add((Component) (Component.getComponentClass(name)
                                .getConstructor(Node.class).newInstance(component)));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                e.printStackTrace();
            }
        }
    }

    public Prototype(String id, NodeList components) {
        this.id = id;
        this.parent = null;
        this.components = new ArrayList<Component>();
        loadComponents(components);
    }

    @SuppressWarnings({ "unchecked" })
    public Prototype(String id, NodeList components, String parent) {
        this.id = id;
        this.parent = Start.mainRunner.mainSystem.prototypeManager.searchPrototypeById(parent, id);
        this.components = (ArrayList<Component>) this.parent.components.clone();
        loadComponents(components);
    }

    protected final boolean hasComponent(Class<Component> component) {
        for (Component i : components) {
            if (component.isInstance(i)) {
                return true;
            }
        }
        return false;
    }

    protected final boolean hasParents(Component component) {
        if (component.getParents() == null) {
            return true;
        }
        for (Object i : component.getParents()) {
            if (!components.contains(i)) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings({ "unchecked" })
    public final void tryAddComponent(Component component) {
        if (hasComponent((Class<Component>) component.getClass()) || hasParents(component)) {
            return;
        }
        components.add(component);
    }

    @SuppressWarnings({ "unchecked" })
    public static final Class<Prototype> getPrototypeClass(String name) {
        try {
            return (Class<Prototype>) Class.forName(name);
        } catch (ClassNotFoundException e) {
            return Prototype.class;
        }
    }

    public final Component getComponent(String name) {
        for (Component component : components) {
            if (component.getClass().getName().equals(name))
                return component;
        }
        return null;
    }

}
