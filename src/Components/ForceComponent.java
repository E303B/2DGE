package Components;

import java.util.ArrayList;

import org.w3c.dom.Node;

import Scripts.ScriptRunner;
import Shared.Start;

public class ForceComponent extends Component {
    public ForceComponent(Node attributes) {
        super(attributes);
    }
    public ForceComponent() {
        super();
    }
    public ArrayList<Object> parents = new ArrayList<Object>();

    float speedX = 0f, speedY = 0f;

    @Override
    public void call(Object[] params, ScriptRunner runner) {
        if (params.length < 2) {
            Start.mainRunner.mainLogger.error("Call ForceComponent takes 2 arguments, but " + params.length + " given");
            return;
        }
        switch (params[0].toString()) {
            case "GetSpeedX":
                runner.setVar(params[1].toString(), speedX);
                break;
            case "GetSpeedY":
                runner.setVar(params[1].toString(), speedY);
                break;
            case "SetSpeedX":
                speedX = (float) params[1];
                break;
            case "SetSpeedY":
                speedY = (float) params[1];
                break;
            default:
                break;
        }
    }
    @Override
    public void init() {
        parents.add(VectorComponent.class);
    }

}
