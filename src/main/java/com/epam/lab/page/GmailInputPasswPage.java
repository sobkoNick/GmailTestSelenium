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

        WebDriverWait waitForOne = new WebDriverWait(getDriver(), 10);
        waitForOne.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")));

        WebDriverWait waitForOne2 = new WebDriverWait(getDriver(), 10);
        waitForOne2.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")));

        WebDriverWait waitForOne3 = new WebDriverWait(getDriver(), 10);
        waitForOne3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")));

        passwordInput.setText(password);
        nextBtn.click();
        return new WriteLetterPage();
    }

    public String loginIsGood() {
        return getDriver().findElement(By.xpath("//*[@id=\"loading\"]/div[1]/div[1]")).getAttribute("innerHTML");
    }
}
