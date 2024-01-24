package Components;

import java.util.HashMap;

public class VectorComponent extends Component {
    String name = "VectorComponent";
    public float x, y;

    @Override
    public void loadComponent(HashMap<String, Object> attributes) {
        x = (float) trySearchAttribute("x", attributes, x);
        y = (float) trySearchAttribute("y", attributes, y);
    }
}
