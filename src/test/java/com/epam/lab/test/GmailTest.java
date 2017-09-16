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
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 *
 */
public class GmailTest {
    private WebDriver webDriver;
    private EnvProperties envProperties;
    private DOMParser domParser;
    private List<UserAndMessageDetails> users;
    private UserAndMessageDetails user;

    @BeforeClass
    public void setUp() throws IOException, SAXException, ParserConfigurationException {
        webDriver = DriverSingleton.getWebDriver();
        envProperties = new EnvProperties();
        domParser = new DOMParser();
        users = domParser.getUserData(envProperties.getXMLPath());
        user = users.get(0);
    }

    @Test
    public void mainTest() {
        LoginBO loginBO = new LoginBO(webDriver);
        Assert.assertTrue(loginBO.login(user).contains(user.getLogin()));
        MessageBO messageBO = new MessageBO();
        Assert.assertTrue(messageBO.writeMessage(user).contains("надіслано"));
        Assert.assertTrue(messageBO.verifyMessage(user).contains("Ланцюжок повідомлень перенесено в \"Кошик\""));
    }

/*
//    @Test
//    public void goToLogin() {
//        StartPage startPage = new StartPage();
//        startPage.goToLoginPage(envProperties.getBaseUrl());
//    }
//
//    @Test
//    public void inputMailTest() {
//        GmailLoginPage gmailLoginPage = new GmailLoginPage();
//        gmailLoginPage.typeLoginAndSubmit(users.get(0).getLogin());
//    }
//
//    @Test
//    public void inputPasswordTest() {
//        GmailInputPasswPage gmailInputPasswPage = new GmailInputPasswPage();
//        gmailInputPasswPage.inputPasswordAndSubmit(users.get(0).getPassword());
//    }
//
//    @Test
//    public void composeTest() {
//        WriteLetterPage writeLetterPage = new WriteLetterPage();
//        writeLetterPage.clickOnCompose();
//    }
//
//    @Test
//    public void writeLetterTest() {
//        WriteLetterPage writeLetterPage = new WriteLetterPage();
//        writeLetterPage.writeLetter(users.get(0).getMailTo(), users.get(0).getSubject(), users.get(0).getMessageText());
//    }
//
//    @Test
//    public void sendMessageTest() {
//        WriteLetterPage writeLetterPage = new WriteLetterPage();
//        writeLetterPage.sendLetter();
//    }
//
//    @Test
//    public void goToSendPageTest() {
//        WriteLetterPage writeLetterPage = new WriteLetterPage();
//        writeLetterPage.clickSendHref();
//    }
//
//    @Test
//    public void verifyMessageTest() {
//        SendPage sendPage = new SendPage();
//        Assert.assertTrue(sendPage.verifyInSendPage(users.get(0).getSubject()) != null);
//    }
//
//    @Test
//    public void removeMessageTest() {
//        SendPage sendPage = new SendPage();
//        Assert.assertTrue(sendPage.removeMessage(users.get(0).getSubject()) != null);
//    }
*/
    @AfterClass
    public void release() {
        webDriver.quit();
        webDriver = null;
        envProperties = null;
        domParser = null;
    }
}
