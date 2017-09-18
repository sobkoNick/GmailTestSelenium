package com.epam.lab.businessObject;

import com.epam.lab.util.EnvProperties;
import com.epam.lab.model.Message;
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
        writeLetterPage.writeAndSendLetter(message.getReceiver(), message.getSubject(), message.getText());
    }

    public String verifySendingMessage() {
        String messageToReturn = writeLetterPage.verifyLetterSending();
        writeLetterPage.goToSendPage(envProperties.getSendUrl());
        return messageToReturn;
    }

    public String verifyDeletingMessage(Message message) {
        SendPage sendPage = new SendPage();
        sendPage.verifyMessageInSendPage(message.getSubject());
        sendPage.removeMessageFromSend();
        return sendPage.verifyMessageDeleting();
    }
}
