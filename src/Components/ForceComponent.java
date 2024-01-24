package Components;

import java.util.HashMap;

public class ForceComponent extends Component {
    Object[] parents = new Component[1];

    public ForceComponent(HashMap<String, Object> attributes) {
        super(attributes);
        parents[0] = VectorComponent.class;
    }

    float speedX = 0f, speedY = 0f;

}
