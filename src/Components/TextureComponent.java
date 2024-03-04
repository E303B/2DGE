package Components;

import java.util.ArrayList;

import org.w3c.dom.Node;

import Scripts.ScriptRunner;
import Shared.Start;

public class TextureComponent extends Component {
    public TextureComponent(Node attributes) {
        super(attributes);
    }

    public TextureComponent() {
        super();
    }

    public String path = "Blank.png";
    public ArrayList<Object> parents = new ArrayList<Object>();
    public float renderWidth = 1f, renderHeight = 1f;

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

    @Override
    public void call(Object[] params, ScriptRunner runner) {
        if (params.length < 2) {
            Start.mainRunner.mainLogger
                    .error("Call TextureComponent takes 2 arguments, but " + params.length + " given");
            return;
        }
        switch (params[0].toString()) {
            case "GetPath":
                runner.setVar(params[1].toString(), path);
                break;
            case "SetPath":
                path = params[1].toString();
                break;
            case "GetWidth":
                runner.setVar(params[1].toString(), renderWidth);
                break;
            case "SetWidth":
                renderWidth = (float) params[1];
                break;
            case "GetHeight":
                runner.setVar(params[1].toString(), renderHeight);
                break;
            case "SetHeight":
                renderHeight = (float) params[1];
                break;
            default:
                break;
        }
    }

    @Override
    public void init() {
        parents = new ArrayList<Object>();
        if (parents != null)
            parents.add(VectorComponent.class);
        path = getAttribute("path", "Blank.png").toString();
        renderWidth = Float.parseFloat(getAttribute("width", 1).toString());
        renderHeight = Float.parseFloat(getAttribute("height", 1).toString());
    }
}
