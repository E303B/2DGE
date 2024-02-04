package Shared;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


//Add here all functions which is used in different classes
public abstract class Tools {
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
        try {
            Boolean.parseBoolean(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static final boolean isFloat(String s) {
        try {
            Float.parseFloat(s);
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
