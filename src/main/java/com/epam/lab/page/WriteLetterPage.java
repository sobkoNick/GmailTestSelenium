package com.epam.lab.page;

import com.epam.lab.wrapper.Button;
import com.epam.lab.wrapper.Input;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 *
 */
public class WriteLetterPage extends  PageObject {
    @FindBy(css = "textarea.vO")
    private Input sendToInput;
    @FindBy(css = "input.aoT")
    private Input subjectInput;
    @FindBy(css = "div.Am.Al.editable.LW-avf")
    private Input messageInput;
    @FindBy(css = "div.T-I.J-J5-Ji.aoO.T-I-atl.L3")
    private Button sendBtn;

    public PageObject clickOnCompose() {
        getDriver().findElement(By.cssSelector("div.T-I.J-J5-Ji.T-I-KE.L3")).click();
        return this;
    }

    public PageObject writeLetter(String receiver, String subject, String message) {
        sendToInput.setText(receiver);
        subjectInput.setText(subject);
        messageInput.setText(message);
        return this;
    }

    public String sendLetter() {
        sendBtn.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println("mes = " + getDriver().findElement(By.xpath("/html/body/div[7]/div[3]/div/div[1]/div[5]/div[1]/div/div[3]/div/div/div[2]")).getAttribute("innerHTML"));
        return getDriver().findElement(By.xpath("/html/body/div[7]/div[3]/div/div[1]/div[5]/div[1]/div/div[3]/div/div/div[2]")).getAttribute("innerHTML");
    }

    public PageObject clickSendHref() {
        getDriver().get("https://mail.google.com/mail/u/0/#sent");
        return new SendPage();
    }
}
