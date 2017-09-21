package com.epam.lab.page;

import com.epam.lab.wrapper.Button;
import com.epam.lab.wrapper.Input;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 */
public class GmailLoginPage extends PageObject {

    @FindBy(xpath = "//*[@class=\"CwaK9\"]")
    private Button nextBtn;
    @FindBy(xpath = "//*[@type=\"email\"]")
    private Input loginInput;

    public void typeLoginAndSubmit(String login) {
        WebDriverWait waitForLoginInput = new WebDriverWait(getDriver(), PageObject.waitTimeInSeconds);
        waitForLoginInput.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"identifierId\"]")));
        loginInput.setText(login);
        nextBtn.click();
    }
}
