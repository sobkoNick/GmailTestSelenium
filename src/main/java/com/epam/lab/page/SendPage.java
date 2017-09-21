package com.epam.lab.page;

import com.epam.lab.wrapper.Button;
import com.epam.lab.wrapper.CheckBox;
import com.epam.lab.wrapper.Label;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 */
public class SendPage extends PageObject {
    private static final Logger LOGGER = Logger.getLogger(SendPage.class);

    @FindBy(xpath = "(//*[@class=\"xS\"]/div/div[2])[1]")
    private Label subjectElement;

    @FindBy(xpath = "(//*[@class=\"Cp\"])[4]/div/table/tbody/tr[1]/td[2]/div")
    private CheckBox mailCheckBox;

    @FindBy(xpath = "//*[@class=\"aeH\"]/div[2]/div[1]/div[1]/div/div/div[2]/div[3]/div")
    private Button deleteMessageBtn;

    @FindBy(xpath = "//button[@name=\"ok\"]")
    private Button okDeleteBtn;

    public String verifyMessageInSendPage() {
        WebDriverWait waitForSubjectText = new WebDriverWait(getDriver(), PageObject.waitTimeInSeconds);
        waitForSubjectText.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class=\"xS\"]/div/div[2])[1]")));
        return subjectElement.getText();
    }

    public void removeMessageFromSend() {
        mailCheckBox.clickOnCheckField();
        deleteMessageBtn.click();
        WebDriverWait waitForDeleteBtn = new WebDriverWait(getDriver(), PageObject.waitTimeInSeconds);
        waitForDeleteBtn.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name=\"ok\"]")));
        okDeleteBtn.click();
    }

    public String verifyMessageDeleting() {
        String xpathExpression = "(//*[@class=\"Cp\"])[4]/div[1]/table/tbody/tr[3]/td[6]/div/div/div/span[1]";
        WebDriverWait waitForDeletePopUpMessage = new WebDriverWait(getDriver(), PageObject.waitTimeInSeconds);
        waitForDeletePopUpMessage.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathExpression)));
        return getDriver().findElement(
                By.xpath(xpathExpression)).getText();
    }
}

