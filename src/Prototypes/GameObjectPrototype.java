package Prototypes;

import Components.TextureComponent;
import Components.VectorComponent;
import Types.BaseType;
import Types.GameObject;
import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Node;

public class GameObjectPrototype extends Prototype {

    public Class<GameObject> assignedType = GameObject.class;

    public GameObjectPrototype(String id, ArrayList<Node> components) {
        super(id, components);
        tryAddComponent(new VectorComponent(new HashMap<String, Object>()));
        tryAddComponent(new TextureComponent(new HashMap<String, Object>()));
    }

    public GameObjectPrototype(String id, ArrayList<Node> components, String parent) {
        super(id, components, parent);
        tryAddComponent(new VectorComponent(new HashMap<String, Object>()));
        tryAddComponent(new TextureComponent(new HashMap<String, Object>()));
    }
}
