package Shared;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

//Add here all functions which is used in different classes
public abstract class Tools {
    public static final Object parseValue(String value, HashMap<String, Object> variables, boolean ignoreRestrictions) {
        String temp = value;
        if (temp.startsWith("\"") && temp.length() >= 3 && temp.endsWith("\""))
            return temp.substring(1, temp.length() - 1);
        else if (temp.startsWith("'") && temp.length() >= 3 && temp.endsWith("'"))
            return temp.substring(1, temp.length() - 1);
        else if (!temp.startsWith("\"") && !temp.startsWith("'") && (ignoreRestrictions || temp.trim() != temp)) {
            temp = temp.trim();

            if (isShort(temp))
                return Short.parseShort(temp);
            else if (isInteger(temp))
                return Integer.parseInt(temp);
            else if (isFloat(temp))
                return Float.parseFloat(temp);
            else if (isLong(temp))
                return Long.parseLong(temp);
            else if (isDouble(temp))
                return Double.parseDouble(temp);
            else if (isBool(temp))
                return Boolean.parseBoolean(temp);
            else if (variables.containsKey(temp))
                return variables.get(temp);
            else if (isByte(temp))
                return Byte.parseByte(temp);
        }
        return null;
    }

    public final static Object parseValue(String value, HashMap<String, Object> variables) {
        return parseValue(value, variables, false);
    }

    public static final ArrayList<File> filterByFileExtension(File[] files, String extension) {
        ArrayList<File> result = new ArrayList<File>();
        for (File file : files) {
            if (file.getName().endsWith("." + extension.toLowerCase())) {
                result.add(file);
            }
        }
        return result;
    }

    public static final File[] getAllFilesInDirectory(String path) {
        File directory = new File(path);
        if (!(directory.exists() && directory.isDirectory()))
            return null;
        return directory.listFiles();
    }

    public static final ArrayList<String> splitBy(String s, String chars) {
        ArrayList<String> result = new ArrayList<String>();
        String temp = "";
        for (char c : s.toCharArray()) {
            if (chars.indexOf(c) > -1) {
                if (!temp.isEmpty())
                    result.add(temp);
                temp = "";
            } else
                temp += c;
        }
        if (!temp.isEmpty())
            result.add(temp);
        return result;
    }

    public static final String readFile(File file) throws IOException {
        return new String(Files.readAllBytes(file.toPath()));
    }

    public static final String readFile(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    public static final String readFile(Path path) throws IOException {
        return new String(Files.readAllBytes(path));
    }

    public static final boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static final boolean isBool(String s) {
        return s.toLowerCase().equals("true")||s.toLowerCase().equals("false");
    }

    public static final boolean isFloat(String s) {
        try {
            Float.parseFloat(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static final boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static final boolean isShort(String s) {
        try {
            Short.parseShort(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static final boolean isLong(String s) {
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static final boolean isByte(String s) {
        try {
            Byte.parseByte(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void write(String text, String path) {
        try {
            FileWriter mainFileWriter = new FileWriter(path);
            mainFileWriter.write(text);
            mainFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void append(String text, String path) {
        try {
            FileWriter mainFileWriter = new FileWriter(path, true);
            mainFileWriter.append(text);
            mainFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
