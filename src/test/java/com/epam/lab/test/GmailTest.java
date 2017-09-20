package com.epam.lab.test;

import com.epam.lab.businessObject.LoginBO;
import com.epam.lab.businessObject.MessageBO;
import com.epam.lab.model.Message;
import com.epam.lab.model.User;
import com.epam.lab.util.DOMParser;
import com.epam.lab.util.DriverSingleton;
import com.epam.lab.util.EnvProperties;
import org.testng.Assert;
import org.testng.annotations.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 * Test class
 */
public class GmailTest {
    private EnvProperties envProperties;
    private DOMParser domParser;
    private List<User> users;
    private List<Message> messages;
    private final int attributeCount = 2; // count of attributes that will be passed to test by dataProvider
    private final String sendMessageText = "надіслано";
    private final String deleteMessageText = "Ланцюжок повідомлень перенесено в \"Кошик\"";

    @BeforeClass
    public void setUp() throws IOException, SAXException, ParserConfigurationException {
        envProperties = new EnvProperties();
        domParser = new DOMParser();
        users = domParser.getUserData(envProperties.getXMLPathForUsers());
        messages = domParser.getMessageData(envProperties.getXMLPathForMessages());
    }

    @Test(dataProvider = "data")
    public void writeAndDeleteMessageGmail_ValidEmailAndPasswordGiven_ShouldPassTheTest(User user, Message message) {
        LoginBO loginBO = new LoginBO();
        Assert.assertTrue(loginBO.login(user).contains(user.getLogin()));
        MessageBO messageBO = new MessageBO();
        messageBO.writeMessage(message);
        Assert.assertTrue(messageBO.verifySendingMessage().contains(sendMessageText));
        Assert.assertTrue(messageBO.verifyDeletingMessage(message).contains(deleteMessageText));
    }

    @DataProvider(name = "data", parallel = true)
    public Object[][] getData() throws IOException, SAXException, ParserConfigurationException {
        Object dataForTest[][] = new Object[users.size()][attributeCount];
        for (int i = 0; i < users.size(); i++) {
            dataForTest[i][0] = users.get(i);
            dataForTest[i][1] = messages.get(i);
        }
        return dataForTest;
    }

    @AfterMethod
    public void tearDown() {
        DriverSingleton.quit();
    }

    @AfterClass
    public void release() {
        envProperties = null;
        users = null;
        messages = null;
        domParser = null;
    }
}
