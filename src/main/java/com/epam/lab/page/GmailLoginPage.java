package com.epam.lab.page;

import com.epam.lab.wrapper.Button;
import com.epam.lab.wrapper.Input;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

/**
 *
 */
public class GmailLoginPage extends PageObject {

    @FindBy(xpath = "//*[@id=\"identifierNext\"]/content")
    private Button nextBtn;
    @FindBy(xpath = "//*[@id=\"identifierId\"]")
    private Input loginInput;

    public PageObject typeLoginAndSubmit(String login) {
        loginInput.setText(login);
        nextBtn.click();
        return new GmailInputPasswPage();
    }
}
