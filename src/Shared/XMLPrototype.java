package Shared;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Components.Component;

import java.io.File;

public final class XMLPrototype {
    public String id = "", parent = "", type = "";
    public HashMap<String, Object> components;

    public XMLPrototype(String type, String id, HashMap<String, Object> components) {
        this.type = type;
        this.id = id;
        this.components = components;
    }

    public XMLPrototype(String type, String id, HashMap<String, Object> components, String parent) {
        this.type = type;
        this.id = id;
        this.components = components;
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
            ArrayList<XMLPrototype> result=new ArrayList<XMLPrototype>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                HashMap<String, Object> components=new HashMap<String, Object>();
                XMLPrototype prototype = element.hasAttribute("parent")?new XMLPrototype(element.getTagName(), element.getAttribute("id"), components, element.getAttribute("parent")):new XMLPrototype(element.getTagName(), element.getAttribute("id"), null);
                result.add(prototype);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
