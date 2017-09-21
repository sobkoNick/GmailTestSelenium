package com.epam.lab.test;

import com.epam.lab.businessObject.LoginBO;
import com.epam.lab.businessObject.MessageBO;
import com.epam.lab.model.Message;
import com.epam.lab.model.User;
import com.epam.lab.util.DOMParser;
import com.epam.lab.util.DriverSingleton;
import com.epam.lab.util.EnvProperties;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 * Test class
 */
public class GmailTest {
    private final int attributeCount = 2; // count of attributes that will be passed to test by dataProvider

    @Test(dataProvider = "data")
    public void writeAndDeleteMessageGmail_ValidEmailAndPasswordGiven_ShouldPassTheTest(User user, Message message) {
        LoginBO loginBO = new LoginBO();
        Assert.assertTrue(loginBO.login(user).contains(user.getLogin()));
        MessageBO messageBO = new MessageBO();
        messageBO.writeMessage(message);
        Assert.assertTrue(messageBO.verifySendingMessage());
        messageBO.goToSendPage();
        Assert.assertTrue(messageBO.verifyMessageInSendPage().contains(message.getSubject()));
        Assert.assertFalse(messageBO.verifyDeletingMessage().contains(message.getSubject())); // subject will be different
    }

    @DataProvider(name = "data", parallel = true)
    public Object[][] getData() throws IOException, SAXException, ParserConfigurationException {

        EnvProperties envProperties = new EnvProperties();
        DOMParser domParser = new DOMParser();
        List<User> users = domParser.getUserData(envProperties.getXMLPathForUsers());
        List<Message> messages = domParser.getMessageData(envProperties.getXMLPathForMessages());

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

}
