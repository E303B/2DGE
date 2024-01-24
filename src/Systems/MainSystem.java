package Systems;

import java.util.ArrayList;
import Shared.PrototypeManager;
import Shared.Window;
import Shared.Start;

import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;

public class MainSystem {
    public PrototypeManager prototypeManager;
    public ArrayList<BaseSystem> systems;

    public MainSystem() {
        prototypeManager = new PrototypeManager("./Prototypes");
        loadAllSystems();
    }

    public MainSystem(boolean autoload) {
        prototypeManager = new PrototypeManager("./Prototypes");
        if (autoload) {
            autoloadSystems();
        } else
        loadAllSystems();
    }
    
    private void autoloadSystems(){
        systems = new ArrayList<BaseSystem>();
        for(Class system:BaseSystem.class.getClasses()){
            try {
                systems.add((BaseSystem)system.getDeclaredConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void loadAllSystems() {
        systems = new ArrayList<BaseSystem>();
        systems.add(new GameObjectSystem());
        systems.add(new TextureSystem());
        systems.add(new CameraSystem());
    }

    public BaseSystem getSystem(String name) {
        for (BaseSystem system : systems) {
            if (system.getClass().getName() == name) {
                return system;
            }
        }
        return null;
    }

    public BaseSystem getSystem(Class sys) {
        for (BaseSystem system : systems) {
            if (system.getClass() == sys) {
                return system;
            }
        }
        return null;
    }

    public void run() {
        Window window = Start.mainRunner.mainWindow;
        window.renderBuf = new BufferedImage(window.getWidth(), window.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (BaseSystem system : systems) {
            system.run();
        }
    }
}
