package com.epam.lab.businessObject;

import com.epam.lab.UserAndMessageDetails;
import com.epam.lab.page.PageObject;
import com.epam.lab.page.SendPage;
import com.epam.lab.page.WriteLetterPage;

/**
 *
 */
public class MessageBO {
    public String writeMessage(UserAndMessageDetails user) {
        WriteLetterPage writeLetterPage = new WriteLetterPage();
        writeLetterPage.clickOnCompose();
        writeLetterPage.writeLetter(user.getMailTo(), user.getSubject(), user.getMessageText());
        String message = writeLetterPage.sendLetter();
        writeLetterPage.clickSendHref();
        return message;
    }
    public String verifyMessage(UserAndMessageDetails user) {
        SendPage sendPage = new SendPage();
        sendPage.verifyInSendPage(user.getSubject());
        sendPage.removeMessage(user.getSubject());
        return sendPage.verifyDeleteMessageApear();
    }
}
