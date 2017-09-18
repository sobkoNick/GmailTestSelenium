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

    @FindBy(xpath = "//*[@id=\"identifierNext\"]/content")
    private Button nextBtn;
    @FindBy(xpath = "//*[@id=\"identifierId\"]")
    private Input loginInput;

    public PageObject typeLoginAndSubmit(String login) {
        WebDriverWait waitForLoginInput = new WebDriverWait(getDriver(), 2);
        waitForLoginInput.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"identifierId\"]")));
        loginInput.setText(login);
        nextBtn.click();
        return new GmailInputPasswPage();
    }
}
