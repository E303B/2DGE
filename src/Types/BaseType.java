package Types;

import java.util.ArrayList;

import Components.Component;
import Prototypes.GameObjectPrototype;
import Prototypes.Prototype;

public class BaseType {
    public ArrayList<Component> components;

    public BaseType(Prototype prototype) {
        this.components = (ArrayList<Component>) prototype.components.clone();
    }

    public Component getComponent(String name) {
        for (Component i : components) {
            if (i.getClass().getName() == name) {
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
