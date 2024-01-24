package Systems;

import java.util.ArrayList;

import Types.GameObject;

public class GameObjectSystem extends BaseSystem {
    ArrayList<GameObject> gameObjects;

    public GameObjectSystem(){
        gameObjects=new ArrayList<GameObject>();
        
    }

    @Override
    public void run() {
    }

}
