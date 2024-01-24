package Shared;

import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import java.io.File;

public final class XMLPrototype {
    public String id = "", parent = "", type = "";
    public boolean override = false;
    public ArrayList<Node> components;

    public XMLPrototype(String type, String id, ArrayList<Node> components, boolean override) {
        this.type = type;
        this.id = id;
        this.components = components;
        this.override = override;
        this.parent = "";
    }

    public XMLPrototype(String type, String id, ArrayList<Node> components, boolean override, String parent) {
        this.type = type;
        this.id = id;
        this.components = components;
        this.override = override;
        this.parent = parent;
    }

    public static ArrayList<XMLPrototype> loadPrototypes(String xmlString) {
        try {
            File inputFile = new File(xmlString);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("*");
            ArrayList<XMLPrototype> result = new ArrayList<XMLPrototype>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                ArrayList<Node> components = new ArrayList<Node>();
                for (int j = 0; j < element.getChildNodes().getLength(); j++) {
                    components.add(element.getChildNodes().item(i));
                }
                XMLPrototype prototype = element.hasAttribute("parent")
                        ? new XMLPrototype(element.getTagName(), element.getAttribute("id"), components,
                                element.hasAttribute("override"),
                                element.getAttribute("parent"))
                        : new XMLPrototype(element.getTagName(), element.getAttribute("id"), components,
                                element.hasAttribute("override"));
                result.add(prototype);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
