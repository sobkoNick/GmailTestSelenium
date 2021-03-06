package com.epam.lab.page;

import com.epam.lab.util.DriverSingleton;
import com.epam.lab.wrapper.MyFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

/**
 * Basic class for all pages
 */
public class PageObject {
    public static final int waitTimeInSeconds = 5; // time for visibilityOfElementLocated
    private WebDriver driver;
    public PageObject() {
        driver = DriverSingleton.getInstance();
        driver.manage().window().maximize();
        // set up custom decorator
        PageFactory.initElements(new MyFieldDecorator(new DefaultElementLocatorFactory(driver)), this);
    }

    public WebDriver getDriver() {
        return driver;
    }
}
