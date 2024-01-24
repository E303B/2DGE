package Components;

import java.util.HashMap;

public class TextureComponent extends Component {
    String name = "TextureComponent";
    public String path = "Blank.png";
    Object[] parents = new Component[1];
    public float renderWidth, renderHeight = 1f;

    public TextureComponent() {
        parents[0] = VectorComponent.class;
    }

    @Override
    public void loadComponent(HashMap<String, Object> attributes) {
        path = (String) trySearchAttribute("path", attributes, path);
        renderWidth = (float) trySearchAttribute("width", attributes, renderWidth);
        renderHeight = (float) trySearchAttribute("height", attributes, renderHeight);
    }

}
