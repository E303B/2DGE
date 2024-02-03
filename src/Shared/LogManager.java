package Shared;

import java.io.FileWriter;
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

    protected void write(String text) {
        try {
            FileWriter mainFileWriter = new FileWriter(path, true);
            mainFileWriter.append(text);
            mainFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log(String text) {
        logs++;
        write("[LOG] " + LocalDateTime.now() + ": " + text + "\n");
        System.out.println("[LOG] " + LocalDateTime.now() + ": " + text);
    }

    public void error(String text) {
        errors++;
        write("[ERROR] " + LocalDateTime.now() + ": " + text + "\n");
        System.err.println("[ERROR] " + LocalDateTime.now() + ": " + text);
    }

    public void warning(String text) {
        warnings++;
        write("[WARN] " + LocalDateTime.now() + ": " + text + "\n");
        System.out.println("[WARN] " + LocalDateTime.now() + ": " + text);
    }
}
