package Components;

import java.util.HashMap;

public class TextureComponent extends Component {
    public String path = "Blank.png";
    Object[] parents = new Component[1];
    public float renderWidth, renderHeight = 1f;

    public TextureComponent(HashMap<String, Object> attributes) {
        super(attributes);
        parents[0] = VectorComponent.class;
        path = (String) trySearchAttribute("path", attributes, path);
        renderWidth = (float) trySearchAttribute("width", attributes, renderWidth);
        renderHeight = (float) trySearchAttribute("height", attributes, renderHeight);
    }

    @Override
    public void tryOverrideAttribute(String name, Object value){
        switch (name) {
            case "path":
                path=(String) value;
                break;
        
            case "width":
                renderWidth=(float) value;
                break;
            case "height":
                renderHeight=(float) value;
                break;
            default:
                break;
        }
    }
}
