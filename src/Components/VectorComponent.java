package Components;

import java.util.HashMap;

public class VectorComponent extends Component {
    public float x, y;

    public VectorComponent(HashMap<String, Object> attributes){
        super(attributes);
        x = (float) trySearchAttribute("x", attributes, x);
        y = (float) trySearchAttribute("y", attributes, y);
    }


    @Override
    public void tryOverrideAttribute(String name, Object value){
        switch (name) {
            case "x":
                x=(float) value;
                break;
            case "y":
                y=(float) value;
                break;
            default:
                break;
        }
    }
}
