package Scripts;

import java.io.File;
import java.io.IOException;

import Shared.Tools;

public final class Script {
    public String srcCode;
    public String path;
    public String name;

    public Script(String path) throws IOException {
        this.path = path;
        srcCode = Tools.readFile(path);
        name = new File(path).getName();
    }

    public Script(File file) throws IOException {
        this.path = file.getPath();
        srcCode = Tools.readFile(file);
        name = file.getName();
    }

    public void run(Object params) {
        new ScriptRunner(srcCode, params);
    }

    public void run() {
        run(null);
    }
}
