package Systems;

import java.util.ArrayList;

import Prototypes.GameObjectPrototype;
import Shared.Start;
import Types.GameObject;

public class GameObjectSystem extends BaseSystem {
    public ArrayList<GameObject> gameObjects;

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

}
