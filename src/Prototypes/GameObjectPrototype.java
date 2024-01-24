package Prototypes;

import Components.TextureComponent;
import Components.VectorComponent;
import Types.BaseType;
import Types.GameObject;

import java.util.HashMap;

public class GameObjectPrototype extends Prototype {

    public Class<GameObject> assignedType = GameObject.class;

    public GameObjectPrototype(String id, HashMap<String, HashMap<String, Object>> components) {
        super(id, components);
        tryAddComponent(new VectorComponent());
        tryAddComponent(new TextureComponent());
    }

    public GameObjectPrototype(String id, HashMap<String, HashMap<String, Object>> components, Prototype parent) {
        super(id, components, parent);
        tryAddComponent(new VectorComponent());
        tryAddComponent(new TextureComponent());
    }
}
