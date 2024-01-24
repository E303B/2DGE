package Components;

import java.util.HashMap;

public class ForceComponent extends Component {
    String name = "ForceComponent";
    Object[] parents = new Component[1];

    public ForceComponent() {
        parents[0] = VectorComponent.class;
    }

    float speedX = 0f, speedY = 0f;

    @Override
    public void loadComponent(HashMap<String, Object> attributes) {

    }

}
