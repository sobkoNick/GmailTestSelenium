package com.epam.lab.test;

import com.epam.lab.*;
import com.epam.lab.businessObject.LoginBO;
import com.epam.lab.businessObject.MessageBO;
import org.testng.Assert;
import org.testng.annotations.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 *
 */
public class GmailTest {
    private EnvProperties envProperties;
    private DOMParser domParser;
    private List<User> users;
    private List<Message> messages;

    @BeforeClass
    public void setUp() throws IOException, SAXException, ParserConfigurationException {
        envProperties = new EnvProperties();
        domParser = new DOMParser();
        users = domParser.getUserData(envProperties.getXMLPathForUsers());
        messages= domParser.getMessageData(envProperties.getXMLPathForMessages());
    }

    @Test(dataProvider = "userData")
    public void writeAndDeleteMessageGmail_ValidEmailAndPasswordGiven_ShouldPassTheTest(User user, Message message) {
        LoginBO loginBO = new LoginBO();
        Assert.assertTrue(loginBO.login(user).contains(user.getLogin()));
        MessageBO messageBO = new MessageBO();
        messageBO.writeMessage(message);
        Assert.assertTrue(messageBO.verifySendingMessage().contains("надіслано"));
        Assert.assertTrue(messageBO.verifyDeletingMessage(message).contains("Ланцюжок повідомлень перенесено в \"Кошик\""));
    }

    @DataProvider(name = "userData", parallel = true)
    public Object[][] getData() {
        Object data [][] = new Object[users.size()][2];
        for (int i = 0; i < users.size();i++) {
            data[i][0] = users.get(i);
            data[i][1] = messages.get(i);
        }
        return data;
//        return new Object[][] { { users.get(0), messages.get(0) } , { users.get(1), messages.get(1) }, { users.get(2), messages.get(2) } , { users.get(3), messages.get(3) }};
    }
//    @AfterMethod
//    public void tearDown() {
//        DriverSingleton.getInstance().quit();
//    }

    @AfterClass
    public void release() {
//        DriverSingleton.nullDriver();
        envProperties = null;
        domParser = null;
    }
}
