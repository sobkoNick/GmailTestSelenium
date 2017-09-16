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
public class GmailInputPasswPage extends PageObject {
    @FindBy(xpath = "//*[@id=\"password\"]/div[1]/div/div[1]/input")
    private Input passwordInput;

    @FindBy(xpath = "//*[@id=\"passwordNext\"]")
    private Button nextBtn;

    public PageObject inputPasswordAndSubmit(String password) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        passwordInput.setText(password);
        nextBtn.click();
        return new WriteLetterPage();
    }

    public String loginIsGood() {
        return getDriver().findElement(By.xpath("//*[@id=\"loading\"]/div[1]/div[1]")).getAttribute("innerHTML");
    }
}
