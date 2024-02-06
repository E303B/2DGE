package Shared;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;

public final class ConfigReader {
    private final String path = "config.ecf";
    private HashMap<String, Object> configValues;
    private ArrayList<String> unused;
    private LogManager logger;

    public ConfigReader() throws IOException {
        logger = Start.mainRunner.mainLogger;
        configValues = new HashMap<String, Object>();
        unused = new ArrayList<String>();
        String[] lines = Tools.readFile(path).split("\n");
        for (String s : lines) {
            String line = s.trim();
            if (line.startsWith("//"))
                continue;
            int splitter = line.indexOf("=");
            if (splitter == -1) {
                logger.error("Unable to parse \"" + s + '"');
                continue;
            }
            String key = line.substring(0, splitter);
            Object value = Tools.parseValue(line.substring(splitter + 1).trim(), configValues, true);
            configValues.put(key, value);
            unused.add(key);
        }
    }

    public Object getValue(String key) {
        if (unused.contains(key))
            unused.remove(key);
        return configValues.get(key);
    }

    public Object getValue(String key, Object defaultValue) {
        Object value = getValue(key);

        return value == null ? defaultValue : value;
    }

    public void logConfigWarnings() {
        for (int i = 0; i < unused.size(); i++) {
            logger.warning("Config file has unused fields: " + unused.get(i));
        }
    }
}