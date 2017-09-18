package com.epam.lab.businessObject;

import com.epam.lab.EnvProperties;
import com.epam.lab.Message;
import com.epam.lab.User;
import com.epam.lab.page.SendPage;
import com.epam.lab.page.WriteLetterPage;

/**
 *
 */
public class MessageBO {
    private EnvProperties envProperties = new EnvProperties();
    private WriteLetterPage writeLetterPage = new WriteLetterPage();
    public void writeMessage(Message message) {
        writeLetterPage.clickOnCompose();
        writeLetterPage.writeLetter(message.getReceiver(), message.getSubject(), message.getText());
    }
    public String verifySendingMessage() {
        String messageToReturn = writeLetterPage.sendLetter();
        writeLetterPage.clickSendHref(envProperties.getSendUrl());
        return messageToReturn;
    }
    public String verifyDeletingMessage(Message message) {
        SendPage sendPage = new SendPage();
        sendPage.verifyInSendPage(message.getSubject());
        sendPage.removeMessage();
        return sendPage.verifyDeleteMessageApear();
    }
}
