package com.rustaronline.mobile.webservicetest;

import javax.xml.parsers.*;
import org.xml.sax.InputSource;
import org.w3c.dom.*;
import java.io.*;

/**
 * Created by gio on 6/23/16.
 */
public class ParseXMLString {
    public static String xmlAsString(String xmlRecords, String node, String element) {
        try {

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource input = new InputSource();
            input.setCharacterStream(new StringReader(xmlRecords));

            NodeList nodeList = builder.parse(input).getElementsByTagName(node);
            Element body = (Element) nodeList.item(0);

            nodeList = body.getElementsByTagName(element);
            Element result = (Element) nodeList.item(0);
            return getCharacterFromElement(result);

        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error...";
        }
    }

    public static String getCharacterFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            return ((CharacterData) child).getData();
        }
        return "?";
    }
}
