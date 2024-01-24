package Prototypes;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import Components.Component;
import Shared.XMLPrototype;
import Shared.Start;
import Types.BaseType;

public class Prototype {
    public ArrayList<Component> components;
    public String id;
    public Prototype parent;
    public Class<BaseType> assignedType;

    public Prototype(String id, HashMap<String, HashMap<String, Object>> components) {
        this.id = id;
        this.parent = null;
        this.components = new ArrayList<Component>();
    }

    public Prototype(String id, HashMap<String, HashMap<String, Object>> components, Prototype parent) {
        this.id = id;
        this.parent = parent;
        this.components = (ArrayList<Component>) parent.components.clone();
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

    public static void loadPrototype(XMLPrototype object) {
        try {
            Prototype prototype=getPrototypeClass(object.type).getConstructor().newInstance(object.id, object.components, Start.mainRunner.mainSystem.prototypeManager.searchPrototypeById(object.parent,object.id));
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
            return;
        }


    }

}
