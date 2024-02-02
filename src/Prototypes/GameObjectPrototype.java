package Prototypes;

import Components.TextureComponent;
import Components.VectorComponent;
import Types.BaseType;
import Types.GameObject;
import org.w3c.dom.NodeList;

public class GameObjectPrototype extends Prototype {

    public Class<GameObject> assignedType = GameObject.class;

    public GameObjectPrototype(String id, NodeList components) {
        super(id, components);
        tryAddComponent(new VectorComponent());
        tryAddComponent(new TextureComponent());
    }

    public GameObjectPrototype(String id, NodeList components, String parent) {
        super(id, components, parent);
        tryAddComponent(new VectorComponent());
        tryAddComponent(new TextureComponent());
    }
}
