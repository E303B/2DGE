package Shared;

import java.util.ArrayList;

import org.w3c.dom.NodeList;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import Prototypes.*;
import Components.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class PrototypeManager {
    public ArrayList<Prototype> prototypes;
    public ArrayList<XMLPrototype> xmlPrototypes;

    private void tryLoadPrototype(XMLPrototype prototype) {
        try {
            if (hasPrototype(prototype.id))
                return;
            Prototype newPrototype;
            if (prototype.parent != "") {
                if ((!hasPrototype(prototype.parent))) {
                    if (!getIsParent(prototype, getXMLPrototype(prototype.parent)))
                        tryLoadPrototype(getXMLPrototype(prototype.parent));
                    else
                        return;
                }
                newPrototype = (Prototype) Prototype.getPrototypeClass(prototype.type)
                        .getConstructor(String.class, NodeList.class, String.class).newInstance(prototype.id,
                                prototype.components, prototype.parent);
            } else
                newPrototype = (Prototype) Prototype.getPrototypeClass(prototype.type)
                        .getConstructor(String.class, NodeList.class).newInstance(prototype.id,
                                prototype.components);
            prototypes.add(newPrototype);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            if (Start.logOnStart)
                Start.mainRunner.mainLogger
                        .error("Catch " + e.getClass().getName() + " while loading prototype " + prototype.id);
        }
    }

    public final boolean hasPrototype(String name) {
        for (Prototype prototype : prototypes) {
            if (prototype.id == name)
                return true;
        }
        return false;
    }

    public final Prototype getPrototype(String name) {
        for (Prototype prototype : prototypes) {
            if (prototype.id == name)
                return prototype;
        }
        return null;
    }

    public final XMLPrototype getXMLPrototype(String name) {
        for (XMLPrototype prototype : xmlPrototypes) {
            if (prototype.id == name)
                return prototype;
        }
        return null;
    }

    private final String readFile(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    private final String readFile(Path path) throws IOException {
        return new String(Files.readAllBytes(path));
    }

    private final ArrayList<File> filterByFileExtension(File[] files, String extension) {
        ArrayList<File> result = new ArrayList<File>();
        for (File file : files) {
            if (file.getName().endsWith("." + extension.toLowerCase())) {
                result.add(file);
            }
        }
        return result;
    }

    private File[] getAllFilesInDirectory(String path) {
        File directory = new File(path);
        if (!(directory.exists() && directory.isDirectory()))
            return null;
        return directory.listFiles();
    }

    public PrototypeManager(String path) {
        if (Start.logOnStart)
            Start.mainRunner.mainLogger.log("Init prototype manager");
        ArrayList<File> files = filterByFileExtension(getAllFilesInDirectory(path), "xml");
        if (files == null) {
            return;
        }
        if (Start.logOnStart)
            Start.mainRunner.mainLogger.log("Parsing prototypes");
        xmlPrototypes = new ArrayList<XMLPrototype>();
        prototypes = new ArrayList<Prototype>();
        ArrayList<XMLPrototype> overridePrototypes = new ArrayList<XMLPrototype>();
        for (File file : files) {
            ArrayList<XMLPrototype> filePrototypes = XMLPrototype.loadPrototypes(file);
            for (XMLPrototype prototype : filePrototypes) {
                if (prototype.override)
                    overridePrototypes.add(prototype);
                else
                    xmlPrototypes.add(prototype);
            }
        }
        if (Start.logOnStart)
            Start.mainRunner.mainLogger.log("Prototypes parsed");
        for (XMLPrototype prototype : xmlPrototypes) {
            tryLoadPrototype(prototype);
        }
        for (XMLPrototype prototype : overridePrototypes) {
            try {
                if (!hasPrototype(prototype.id))
                    continue;
                getPrototype(prototype.id).overideComponents(prototype.components);
            } catch (IllegalArgumentException | SecurityException e) {
                e.printStackTrace();
            }
        }
        if (Start.logOnStart)
            Start.mainRunner.mainLogger.log("Finished prototype manager initialization");
    }

    public Prototype searchPrototypeById(String id) {
        for (Prototype pr : prototypes) {
            if (pr.id == id) {
                return pr;
            }
        }
        return null;
    }

    private boolean getIsParent(XMLPrototype child, XMLPrototype parent) {
        if (child.parent == null) {
            return false;
        }
        if (child.parent == parent.id) {
            return true;
        }
        return getIsParent(getXMLPrototype(child.parent), parent);
    }

    private boolean getIsParent(Prototype child, Prototype parent) {
        if (child.parent == null) {
            return false;
        }
        if (child.parent == parent) {
            return true;
        }
        return getIsParent(child.parent, parent);
    }

    public Prototype searchPrototypeById(String id, String parentUnavailable) {
        for (Prototype pr : prototypes) {
            if (pr.id == id && !getIsParent(pr, searchPrototypeById(parentUnavailable))) {
                return pr;
            }
        }
        return null;
    }
}
