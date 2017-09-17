package com.epam.lab.businessObject;

import com.epam.lab.Message;
import com.epam.lab.User;
import com.epam.lab.page.SendPage;
import com.epam.lab.page.WriteLetterPage;

/**
 *
 */
public class MessageBO {
    public String writeMessage(Message message) {
        WriteLetterPage writeLetterPage = new WriteLetterPage();
        writeLetterPage.clickOnCompose();
        writeLetterPage.writeLetter(message.getReceiver(), message.getSubject(), message.getText());
        String messageToReturn = writeLetterPage.sendLetter();
        writeLetterPage.clickSendHref();
        return messageToReturn;
    }
    public String verifyMessage(Message message) {
        SendPage sendPage = new SendPage();
        sendPage.verifyInSendPage(message.getSubject());
        sendPage.removeMessage(message.getSubject());
        return sendPage.verifyDeleteMessageApear();
    }
}
