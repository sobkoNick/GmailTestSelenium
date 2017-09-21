package com.epam.lab.businessObject;

import com.epam.lab.util.EnvProperties;
import com.epam.lab.model.Message;
import com.epam.lab.page.SendPage;
import com.epam.lab.page.WriteLetterPage;

/**
 *
 */
public class MessageBO {
    private EnvProperties envProperties;
    private WriteLetterPage writeLetterPage;
    private SendPage sendPage;

    public MessageBO() {
        envProperties = new EnvProperties();
        writeLetterPage = new WriteLetterPage();
        sendPage = new SendPage();
    }

    public void writeMessage(Message message) {
        writeLetterPage.clickOnCompose();
        writeLetterPage.writeAndSendLetter(message.getReceiver(), message.getSubject(), message.getText());
    }

    public boolean verifySendingMessage() {
        return writeLetterPage.verifyLetterSending();
    }

    public void goToSendPage() {
        writeLetterPage.goToSendPage(envProperties.getSendUrl());
    }

    public String verifyMessageInSendPage() {
        return sendPage.verifyMessageInSendPage();
    }

    public String verifyDeletingMessage() {
        sendPage.removeMessageFromSend();
        return sendPage.verifyMessageDeleting();
    }
}
