package com.epam.lab.test;

import com.epam.lab.businessObject.LoginBO;
import com.epam.lab.businessObject.MessageBO;
import com.epam.lab.model.Message;
import com.epam.lab.model.User;
import com.epam.lab.util.DriverSingleton;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Test class
 */
public class GmailTest {

    @Test(dataProvider = "dataFromCSVandExel", dataProviderClass = DataProviderClass.class, alwaysRun = true)
//    @Test(dataProvider = "data", dataProviderClass = DataProviderClass.class, alwaysRun = true)  //for 5 tests and DOM parser
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

    @AfterMethod
    public void tearDown() {
        DriverSingleton.quit();
    }

}
