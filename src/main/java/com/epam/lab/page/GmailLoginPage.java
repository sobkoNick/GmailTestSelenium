package com.epam.lab.page;

import com.epam.lab.wrapper.Button;
import com.epam.lab.wrapper.Input;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

/**
 *
 */
public class GmailLoginPage extends PageObject {

//    @FindBy(xpath = "//*[@id=\"identifierId\"]")
//    private WebElement loginInput;
//    @FindBy(xpath = "//*[@id=\"identifierNext\"]/content")
//    private WebElement nextBtn;

    @FindBy(xpath = "//*[@id=\"identifierNext\"]/content")
    private Button nextBtn;
    @FindBy(xpath = "//*[@id=\"identifierId\"]")
    private Input loginInput;

    public PageObject typeLoginAndSubmit(String login) {
        loginInput.setText(login);
        //Input input = new InputImpl(loginInput);
//        loginInput.setTextToInput(login);
        //Button button = new ButtonImpl(nextBtn);
        nextBtn.click();
//        loginInput.sendKeys(login);
//        nextBtn.click();
        return new GmailInputPasswPage();
    }
}
