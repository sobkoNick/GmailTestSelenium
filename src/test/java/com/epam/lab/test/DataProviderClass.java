package com.epam.lab.test;

import com.epam.lab.model.Message;
import com.epam.lab.model.User;
import com.epam.lab.util.CSVExelParser;
import com.epam.lab.util.DOMParser;
import com.epam.lab.util.EnvProperties;
import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 *
 */
public class DataProviderClass {
    private static final int attributeCount = 2; // count of attributes that will be passed to test by dataProvider
    private static final Logger LOGGER = Logger.getLogger(DataProviderClass.class);

    @DataProvider(name = "data", parallel = true)
    public static Object[][] getData() throws IOException, SAXException, ParserConfigurationException {
        LOGGER.info("dataProvider starts");
        EnvProperties envProperties = new EnvProperties();
        DOMParser domParser = new DOMParser();
        List<User> users = domParser.getUserData(envProperties.getXMLPathForUsers());
        List<Message> messages = domParser.getMessageData(envProperties.getXMLPathForMessages());
        LOGGER.info("data was successfully readed.");
        Object dataForTest[][] = new Object[users.size()][attributeCount];
        for (int i = 0; i < users.size(); i++) {
            dataForTest[i][0] = users.get(i);
            dataForTest[i][1] = messages.get(i);
        }
        LOGGER.info("dataProvider ends");
        return dataForTest;
    }

    @DataProvider(name = "dataFromCSVandExel", parallel = true)
    public static Object[][] getDataFromCSVAndXLSX() throws IOException, SAXException, ParserConfigurationException {
        LOGGER.info("dataProvider starts");
        EnvProperties envProperties = new EnvProperties();
        List<User> users = CSVExelParser.readUsersFromCSV(envProperties.getUsersPathCSV());
        List<Message> messages = CSVExelParser.readMessagesFromXLSXFile(envProperties.getMessagesPathXLSX());
        LOGGER.info("data was successfully readed.");
        Object dataForTest[][] = new Object[users.size()][attributeCount];
        for (int i = 0; i < users.size(); i++) {
            dataForTest[i][0] = users.get(i);
            dataForTest[i][1] = messages.get(i);
        }
        LOGGER.info("dataProvider ends");
        return dataForTest;
    }
}
