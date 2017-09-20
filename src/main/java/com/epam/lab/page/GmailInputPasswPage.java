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
public class GmailInputPasswPage extends PageObject {
    @FindBy(xpath = "//*[@id=\"password\"]/div[1]/div/div[1]/input")
    private Input passwordInput;
    @FindBy(xpath = "//*[@id=\"passwordNext\"]")
    private Button nextBtn;

    public PageObject inputPasswordAndSubmit(String password) {
        WebDriverWait waitForPasswordInput = new WebDriverWait(getDriver(), PageObject.waitTimeInSeconds);
        waitForPasswordInput.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")));
        passwordInput.setText(password);
        nextBtn.click();
        return new WriteLetterPage();
    }

    public String checkLoginComplete() {
        return getDriver().findElement(By.xpath("//*[@id=\"loading\"]/div[1]/div[1]")).getAttribute("innerHTML");
    }
}
