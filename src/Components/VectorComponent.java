package Components;

import org.w3c.dom.Node;

public class VectorComponent extends Component {
    public float x, y;

    public VectorComponent(Node attributes) {
        super(attributes);
        x = (float) trySearchAttribute("x", this.attributes, x);
        y = (float) trySearchAttribute("y", this.attributes, y);
    }

    public VectorComponent() {
        super();
    }

    @Override
    public void tryOverrideAttribute(String name, Object value) {
        switch (name) {
            case "x":
                x = (float) value;
                break;
            case "y":
                y = (float) value;
                break;
            default:
                break;
        }
    }
}
