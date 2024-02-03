package Scripts;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import Shared.Start;
import Shared.Tools;

public final class ScriptManager {
    private ArrayList<Script> scripts;

    public Script getScript(String name) {
        return null;
    }

    public ScriptManager(String path) {
        if (Start.logOnStart)
            Start.mainRunner.mainLogger
                    .log("Init script manager");
        ArrayList<File> files = Tools.filterByFileExtension(Tools.getAllFilesInDirectory(path), "esf");
        scripts = new ArrayList<Script>();
        for (File file : files) {
            try {
                scripts.add(new Script(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (Start.logOnStart)
            Start.mainRunner.mainLogger
                    .log("Finish script manager initialization");
    }

    public boolean runScript(String name, Object params) {
        for (Script script : scripts) {
            if (script.name == name) {
                script.run(params);
                return true;
            }
        }
        return false;
    }

    public void runScript(String name) {
        runScript(name, null);
    }
}
