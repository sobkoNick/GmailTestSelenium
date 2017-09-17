package com.epam.lab.page;

import com.epam.lab.DriverSingleton;
import com.epam.lab.wrapper.MyFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

/**
 *
 */
public class PageObject {
    private WebDriver driver;
    public PageObject() {
        driver = DriverSingleton.getInstance();
        driver.manage().window().maximize();
        PageFactory.initElements( new MyFieldDecorator(new DefaultElementLocatorFactory(driver)), this);
    }

    public WebDriver getDriver() {
        return driver;
    }
}
