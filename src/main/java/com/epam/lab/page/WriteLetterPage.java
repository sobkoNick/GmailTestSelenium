package com.epam.lab.page;

import com.epam.lab.wrapper.Button;
import com.epam.lab.wrapper.Input;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 */
public class WriteLetterPage extends PageObject {
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

    public PageObject writeAndSendLetter(String receiver, String subject, String message) {
        WebDriverWait waitForElement = new WebDriverWait(getDriver(), 2);
        waitForElement.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.Am.Al.editable.LW-avf")));
        sendToInput.setText(receiver);
        subjectInput.setText(subject);
        messageInput.setText(message);
        sendBtn.click();
        return this;
    }

    public String verifyLetterSending() {
        String elementPath = "/html/body/div[7]/div[3]/div/div[1]/div[5]/div[1]/div/div[3]/div/div/div[2]";
        WebDriverWait waitForElement = new WebDriverWait(getDriver(), 2);
        waitForElement.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementPath)));
        return getDriver().findElement(By.xpath(elementPath)).getAttribute("innerHTML");
    }

    public PageObject goToSendPage(String sendURL) {
        getDriver().get(sendURL);
        return new SendPage();
    }
}
