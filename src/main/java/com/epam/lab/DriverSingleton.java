package com.epam.lab;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DriverSingleton {

    static EnvProperties envProperties = new EnvProperties();
    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    private DriverSingleton() {
    }

    public static WebDriver getInstance() {

        if (webDriverThreadLocal.get() != null) {

            return webDriverThreadLocal.get();
        }

        WebDriver instance;
        System.setProperty(envProperties.getDriverType(), envProperties.getDriverPath());
        instance = new ChromeDriver();
        instance.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriverThreadLocal.set(instance);

        return webDriverThreadLocal.get();
    }
}