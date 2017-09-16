package com.epam.lab;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class DriverSingleton {
    private static WebDriver webDriver = new ChromeDriver();
    private static EnvProperties envProperties =  new EnvProperties();
    static {
        System.setProperty(envProperties.getDriverType(), envProperties.getDriverPath());
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static WebDriver getWebDriver() {
        if (webDriver == null) {
            webDriver = new ChromeDriver();
        }
        return webDriver;
    }

    private DriverSingleton() {
    }
}
