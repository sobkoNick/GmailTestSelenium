package com.epam.lab.test;

import com.epam.lab.businessObject.LoginBO;
import com.epam.lab.businessObject.MessageBO;
import com.epam.lab.model.Message;
import com.epam.lab.model.User;
import com.epam.lab.util.DriverSingleton;
import com.epam.lab.util.TestNGListener;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 *
 */
@Listeners({TestNGListener.class})
public class GmailTestThatFails {
    private static final Logger LOGGER = Logger.getLogger(GmailTestThatFails.class);

    @Test(dataProvider = "dataFromCSVandExel", dataProviderClass = DataProviderClass.class, alwaysRun = true)
//    @Test(dataProvider = "data", dataProviderClass = DataProviderClass.class, alwaysRun = true)  //for 5 tests and DOM parser
    public void writeAndDeleteMessageGmail_InValidEmailAndPasswordGiven_ShouldFailTheTest(User user, Message message) {
        user.setPassword("wrong!");
        LOGGER.info("test stars");
        LoginBO loginBO = new LoginBO();
        Assert.assertTrue(loginBO.login(user).contains(user.getLogin()));
        LOGGER.info("login test pass");
        MessageBO messageBO = new MessageBO();
        messageBO.writeMessage(message);
        Assert.assertTrue(messageBO.verifySendingMessage());
        LOGGER.info("MessageSending test pass");
        messageBO.goToSendPage();
        Assert.assertTrue(messageBO.verifyMessageInSendPage().contains(message.getSubject()));
        LOGGER.info("Message is in SendPage test pass");
        Assert.assertFalse(messageBO.verifyDeletingMessage().contains(message.getSubject())); // subject will be different
        LOGGER.info("MessageDeleting test pass");
        LOGGER.info("test ends");
    }

    @AfterMethod
    public void tearDown() {
        DriverSingleton.quit();
    }
}
