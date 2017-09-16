package com.epam.lab.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 *
 */
public class StartPage extends PageObject {

    public PageObject goToLoginPage(String url) {
        getDriver().get(url);
        getDriver().findElement(By.xpath("/html/body/nav/div/a[2]")).click();
        return new GmailLoginPage();
    }
}
