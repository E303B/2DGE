package Components;

import java.util.ArrayList;

import org.w3c.dom.Node;

public class ForceComponent extends Component {
    public ArrayList<Object> parents = new ArrayList<Object>();

    public ForceComponent(Node attributes) {
        super(attributes);
        parents.add(VectorComponent.class);
    }

    public ForceComponent() {
        super();
        parents.add(VectorComponent.class);
    }

    float speedX = 0f, speedY = 0f;

}
