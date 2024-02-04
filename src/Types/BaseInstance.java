package Types;

import java.util.ArrayList;

import Components.Component;
import Prototypes.Prototype;

public class BaseInstance {
    public ArrayList<Component> components;
    public String prototypeId;
    @SuppressWarnings("unchecked")
    public BaseInstance(Prototype prototype) {
        prototypeId=prototype.id;
        this.components = (ArrayList<Component>) prototype.components.clone();
    }

    public Component getComponent(String name) {
        for (Component i : components) {
            if (i.getClass().getSimpleName().equals(name)) {
                return i;
            }
        }
        return null;
    }

    public Component getComponent(Class<Component> name) {
        for (Component i : components) {
            if (i.getClass() == name) {
                return i;
            }
        }
        return null;
    }
}
