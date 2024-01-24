package Prototypes;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import Components.Component;
import Shared.XMLPrototype;
import Shared.Start;
import Types.BaseType;

import org.w3c.dom.Node;

public class Prototype {
    public ArrayList<Component> components;
    public String id;
    public Prototype parent;
    public Class<BaseType> assignedType;

    protected HashMap<String, Object> getAttributesForNode(Node node) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            Node child = node.getChildNodes().item(i);
            result.put(child.getNodeName(), child.getChildNodes());
        }
        return result;
    }

    public final void overideComponents(ArrayList<Node> components) {
        for (Node component : components) {
            try {
                if (!hasComponent((Class<Component>) Class.forName(component.getNodeName()))) {

                    tryAddComponent((Component) Class.forName(component.getNodeName()).getConstructor()
                            .newInstance(getAttributesForNode(component)));

                    continue;
                }
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException
                    | ClassNotFoundException e) {
                e.printStackTrace();
                continue;
            }
            Component assignedComponent = getComponent(component.getNodeName());
            for(int i=0;i<component.getChildNodes().getLength();i++){
                Node attribute = component.getChildNodes().item(i);
                assignedComponent.tryOverrideAttribute(attribute.getNodeName(), attribute.getNodeValue());
            }
        }
    }

    protected final ArrayList<Component> loadClassComponents(String name){
        if(Start.mainRunner.mainSystem.prototypeManager.hasPrototype(name))
            return Start.mainRunner.mainSystem.prototypeManager.getPrototype(name).components;
        return null;
    }

    protected final void loadComponents(ArrayList<Node> components) {
        for(Node component:components){
            try {
                this.components.add((Component) Class.forName(component.getNodeName()).getConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException
                    | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public Prototype(String id, ArrayList<Node> components) {
        this.id = id;
        this.parent = null;
        this.components = new ArrayList<Component>();
        loadComponents(components);
    }

    public Prototype(String id, ArrayList<Node> components, String parent) {
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
        if (component.parents == null) {
            return true;
        }
        for (Object i : component.parents) {
            if (!components.contains(i)) {
                return false;
            }
        }
        return true;
    }

    public final void tryAddComponent(Component component) {
        if (hasComponent((Class<Component>) component.getClass()) || hasParents(component)) {
            return;
        }
        components.add(component);
    }

    private static final Class<Prototype> getPrototypeClass(String name) {
        for (Class prototypeClass : Prototype.class.getClasses()) {
            if (prototypeClass.getName() == name)
                return prototypeClass;
        }
        return Prototype.class;
    }

    public final Component getComponent(String name) {
        for (Component component : components) {
            if (component.getClass().getName() == name)
                return component;
        }
        return null;
    }

    public static void loadPrototype(XMLPrototype object) {
        try {
            Prototype prototype = getPrototypeClass(object.type).getConstructor().newInstance(object.id,
                    object.components,
                    Start.mainRunner.mainSystem.prototypeManager.searchPrototypeById(object.parent, object.id));
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            return;
        }

    }

}
