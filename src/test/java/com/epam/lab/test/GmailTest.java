package com.epam.lab.test;

import com.epam.lab.DOMParser;
import com.epam.lab.DriverSingleton;
import com.epam.lab.EnvProperties;
import com.epam.lab.UserAndMessageDetails;
import com.epam.lab.businessObject.LoginBO;
import com.epam.lab.businessObject.MessageBO;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
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
    private List<UserAndMessageDetails> users;

    @BeforeClass
    public void setUp() throws IOException, SAXException, ParserConfigurationException {
        envProperties = new EnvProperties();
        domParser = new DOMParser();
        users = domParser.getUserData(envProperties.getXMLPath());
    }

    @Test(dataProvider = "userData")
    public void mainTest(UserAndMessageDetails user) {
        LoginBO loginBO = new LoginBO();
        Assert.assertTrue(loginBO.login(user).contains(user.getLogin()));
        MessageBO messageBO = new MessageBO();
        Assert.assertTrue(messageBO.writeMessage(user).contains("надіслано"));
        Assert.assertTrue(messageBO.verifyMessage(user).contains("Ланцюжок повідомлень перенесено в \"Кошик\""));
    }

    @DataProvider(name = "userData", parallel = true)
    public Object[][] getData() {
        return new Object[][] { { users.get(0) } , { users.get(1) }, { users.get(2) } , { users.get(3) }};
    }

    @AfterClass
    public void release() {
        envProperties = null;
        domParser = null;
    }
}
