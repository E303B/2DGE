package Shared;

import java.io.IOException;
import java.time.LocalDateTime;

//Save all logs in specail file
public class LogManager {
    public String path;
    public int warnings, errors, logs;

    public LogManager(String path) throws IOException {
        this.path = path;
        logs = warnings = errors = 0;
    }

    public void log(String text) {
        logs++;
        Tools.append("[LOG] " + LocalDateTime.now() + ": " + text + "\n", path);
        System.out.println("[LOG] " + LocalDateTime.now() + ": " + text);
    }

    public void error(String text) {
        errors++;
        Tools.append("[ERROR] " + LocalDateTime.now() + ": " + text + "\n", path);
        System.err.println("[ERROR] " + LocalDateTime.now() + ": " + text);
    }

    public void warning(String text) {
        warnings++;
        Tools.append("[WARN] " + LocalDateTime.now() + ": " + text + "\n", path);
        System.out.println("[WARN] " + LocalDateTime.now() + ": " + text);
    }
}
