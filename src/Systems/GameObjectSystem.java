package Systems;

import java.util.ArrayList;
import java.util.Arrays;

import Prototypes.GameObjectPrototype;
import Scripts.ScriptRunner;
import Shared.Start;
import Types.GameObject;

public class GameObjectSystem extends BaseSystem {
    public ArrayList<GameObject> gameObjects;
    static {
        BaseSystem.systems.add(GameObjectSystem.class);
    }

    public GameObjectSystem() {
        gameObjects = new ArrayList<GameObject>();
    }

    @Override
    public void init() {
        gameObjects.add(new GameObject(
                (GameObjectPrototype) Start.mainRunner.mainSystem.prototypeManager.getPrototype("Test")));

    }

    @Override
    public void run() {
    }

    @Override
    public void call(Object[] params, ScriptRunner runner) {
        if (params.length < 2) {
            Start.mainRunner.mainLogger
                    .error("Call GameObjectSystem takes at least 2 arguments, but " + params.length + " given");
            return;
        }
        switch (params[0].toString()) {
            case "length":
                runner.setVar(params[1].toString(), gameObjects.size());
                break;
            case "object": {
                if (params.length < 4) {
                    Start.mainRunner.mainLogger
                            .error("Call GameObjectSystem takes at least 3 arguments, but " + params.length + " given");
                    return;
                }
                switch (params[1].toString()) {
                    case "prototypeId":
                        runner.setVar(params[3].toString(),
                                gameObjects.get(Integer.parseInt(params[2].toString())).prototypeId);
                        break;
                    case "componentCall":
                        gameObjects.get(Integer.parseInt(params[2].toString())).getComponent(params[3].toString())
                                .call(Arrays.copyOfRange(params, 4, params.length), runner);
                        break;
                    case "componentAdd":
                        gameObjects.get(Integer.parseInt(params[2].toString())).tryAddComponent(params[3].toString());;
                        break;
                    case "componentRemove":
                        gameObjects.get(Integer.parseInt(params[2].toString())).tryRemoveComponent(params[3].toString());
                        break;
                    case "delete":
                        gameObjects.remove(Integer.parseInt(params[2].toString()));
                        break;
                    case "add":
                        gameObjects
                                .add(new GameObject((GameObjectPrototype) Start.mainRunner.mainSystem.prototypeManager
                                        .getPrototype(params[2].toString())));
                        break;
                    default:
                        break;
                }
                break;
            }
            default:
                break;
        }
    }

}
