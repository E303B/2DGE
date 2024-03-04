package Types;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import Components.Component;
import Prototypes.Prototype;
import Shared.Start;

public class BaseInstance {
    public ArrayList<Component> components;
    public String prototypeId;

    public BaseInstance(Prototype prototype) {
        prototypeId = prototype.id;
        loadComponents(prototype);
    }

    private void loadComponents(Prototype prototype) {
        this.components = new ArrayList<Component>();
        for (Component component : prototype.components) {
            try {
                this.components.add(component.copy());
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                e.printStackTrace();
                Start.mainRunner.mainLogger.error(
                        "Caught " + e.getClass().getSimpleName() + " while loading " + component.getClass().getName()
                                + " for new instance of " + prototypeId + ": " + e.getLocalizedMessage());
            }
        }
    }

    public void tryAddComponent(String name){
        if(getComponent(name)==null)return;
        try {
            components.add((Component) Class.forName(name).getConstructor().newInstance());
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            Start.mainRunner.mainLogger.error("Caught "+e.getClass().getSimpleName()+" while adding component");
            e.printStackTrace();
        }
    }

    public void tryRemoveComponent(String name){
        if(getComponent(name)!=null)return;
        for (Component component : components) {
            if(component.getClass().getName().equals(name)){
                components.remove(component);
                break;
            }
        }
    }

    public Component getComponent(String name) {
        for (Component i : components) {
            if (i.getClass().getName().equals(name)) {
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
