package Shared;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;

public final class XMLPrototype {
    public String id = "", parent = "", type = "";
    public boolean override = false;
    public NodeList components;

    public XMLPrototype(String type, String id, NodeList components, boolean override) {
        this.type = type;
        this.id = id;
        this.components = components;
        this.override = override;
        this.parent = "";
    }

    public XMLPrototype(String type, String id, NodeList components, boolean override, String parent) {
        this.type = type;
        this.id = id;
        this.components = components;
        this.override = override;
        this.parent = parent;
    }

    public static ArrayList<XMLPrototype> loadPrototypes(File xmlFile) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getChildNodes();
            ArrayList<XMLPrototype> result = new ArrayList<XMLPrototype>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                XMLPrototype prototype = element.hasAttribute("parent")
                        ? new XMLPrototype(element.getTagName(), element.getAttribute("id"), element.getChildNodes(),
                                element.hasAttribute("override"),
                                element.getAttribute("parent"))
                        : new XMLPrototype(element.getTagName(), element.getAttribute("id"), element.getChildNodes(),
                                element.hasAttribute("override"));
                result.add(prototype);
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
