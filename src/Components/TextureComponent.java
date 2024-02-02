package Components;

import java.util.ArrayList;

import org.w3c.dom.Node;

public class TextureComponent extends Component {
    public String path = "Blank.png";
    public ArrayList<Object> parents = new ArrayList<Object>();
    public float renderWidth, renderHeight = 1f;

    public TextureComponent(Node attributes) {
        super(attributes);
        parents.add(VectorComponent.class);
        path = (String) trySearchAttribute("path", this.attributes, path);
        renderWidth = (float) trySearchAttribute("width", this.attributes, renderWidth);
        renderHeight = (float) trySearchAttribute("height", this.attributes, renderHeight);
    }

    public TextureComponent() {
        super();
        parents.add(VectorComponent.class);
    }

    @Override
    public void tryOverrideAttribute(String name, Object value) {
        switch (name) {
            case "path":
                path = (String) value;
                break;

            case "width":
                renderWidth = (float) value;
                break;
            case "height":
                renderHeight = (float) value;
                break;
            default:
                break;
        }
    }
}
