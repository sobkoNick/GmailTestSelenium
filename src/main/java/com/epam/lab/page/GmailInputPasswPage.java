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
    @FindBy(xpath = "//*[@type=\"password\"]")
    private Input passwordInput;
    @FindBy(xpath = "//*[@class=\"CwaK9\"]")
    private Button nextBtn;

    public void inputPasswordAndSubmit(String password) {
        WebDriverWait waitForPasswordInput = new WebDriverWait(getDriver(), PageObject.waitTimeInSeconds);
        waitForPasswordInput.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@type=\"password\"]")));
        passwordInput.setText(password);
        nextBtn.click();
    }

    public String checkLoginComplete() {
        return getDriver().findElement(By.xpath("//*[@id=\"loading\"]/div[1]/div[1]")).getAttribute("innerHTML");
    }
}
