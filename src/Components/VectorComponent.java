package Components;

import org.w3c.dom.Node;

import Scripts.ScriptRunner;
import Shared.Start;

public class VectorComponent extends Component {
    public VectorComponent(Node attributes) {
        super(attributes);
    }

    public VectorComponent() {
        super();
    }

    public float x, y;

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

    @Override
    public void call(Object[] params, ScriptRunner runner) {
        if (params.length < 2) {
            Start.mainRunner.mainLogger
                    .error("Call VectorComponent takes 2 arguments, but " + params.length + " given");
            return;
        }
        switch (params[0].toString()) {
            case "GetX":
                runner.setVar(params[1].toString(), x);
                break;
            case "GetY":
                runner.setVar(params[1].toString(), y);
                break;
            case "SetX":
                x = (float) params[1];
                break;
            case "SetY":
                y = (float) params[1];
                break;
            default:
                break;
        }
    }

    @Override
    public void init() {
        x = Float.parseFloat(getAttribute("x", 10).toString());
        y = Float.parseFloat(getAttribute("y", 10).toString());
    }
}
