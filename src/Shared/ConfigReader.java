package Shared;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class ConfigReader {
    private final String path = "config.ecf";
    private HashMap<String, Object> configValues;
    private ArrayList<String> unused;
    private LogManager logger;

    private final String readFile(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isBool(String s) {
        try {
            Boolean.parseBoolean(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isFloat(String s) {
        try {
            Float.parseFloat(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isVar(String s) {
        return configValues.containsKey(s);
    }

    private Object parseValue(String value, String keyString) {
        //Add here every new config data type
        if (value.charAt(0) == '"') {
            if (value.charAt(value.length() - 1) == '"')
                return value.substring(1, value.length() - 1);
            else
                logger.error(
                        "String must end with \", but got " + value.charAt(value.length() - 1) + ". At " + keyString);
        } else if (value.charAt(0) == '\'') {
            if (value.charAt(value.length() - 1) == '\'') {
                if (value.substring(1, value.length() - 1).length() == 1)
                    return value.substring(1, value.length() - 1).charAt(0);
                else
                    logger.error("Char length must be 1. At " + keyString);
            } else {
                logger.error(
                        "Char must end with \', but got " + value.charAt(value.length() - 1) + ". At " + keyString);
            }
        } else if (isInteger(value))
            return Integer.parseInt(value);
        else if (isFloat(value))
            return Float.parseFloat(value);
        else if (isBool(value))
            return Boolean.parseBoolean(value);
        else if (isVar(value))
            return getValue(value);
        else
            logger.error("Unable to parse " + value + ". At " + keyString);
        return null;
    }

    public ConfigReader() throws IOException {
        logger = Start.mainRunner.mainLogger;
        configValues = new HashMap<String, Object>();
        unused = new ArrayList<String>();
        String[] lines = readFile(path).split("\n");
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
            Object value = parseValue(line.substring(splitter + 1).trim(), key);
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