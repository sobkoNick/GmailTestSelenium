package com.epam.lab;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class DOMParser {
    static final Logger LOGGER = Logger.getLogger(DOMParser.class);
    public List<User> getUserData(String path) throws ParserConfigurationException, IOException, SAXException {
        List<User> users = new ArrayList<User>();
        LOGGER.info("DOM parser starts");
        File fileXML = new File(path);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = documentBuilderFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fileXML);

        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("user");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                User user = getUser((Element) node);
                users.add(user);
            }
        }
        LOGGER.info("DOM parser ends");
        return users;
    }

    private static User getUser(Element node) {
        User user = new User();
        Element element = node;
        user.setLogin(element.getElementsByTagName("login").item(0).getTextContent());
        user.setPassword(element.getElementsByTagName("password").item(0).getTextContent());
        return user;
    }

    public List<Message> getMessageData(String path) throws ParserConfigurationException, IOException, SAXException {
        List<Message> messages = new ArrayList<Message>();
        LOGGER.info("DOM parser starts");
        File fileXML = new File(path);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = documentBuilderFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fileXML);

        doc.getDocumentElement().normalize();

        NodeList nodeList = doc.getElementsByTagName("message");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Message message = getMessage((Element) node);
                messages.add(message);
            }
        }
        LOGGER.info("DOM parser ends");
        return messages;
    }
    private static Message getMessage(Element node) {
        Message message = new Message();
        Element element = node;
        message.setReceiver(element.getElementsByTagName("receiver").item(0).getTextContent());
        message.setSubject(element.getElementsByTagName("subject").item(0).getTextContent());
        message.setText(element.getElementsByTagName("text").item(0).getTextContent());
        return message;
    }
}
