package com.epam.lab.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DriverSingleton {
    private static int counter = 0;
    private static final int THREAD_COUNT = 3; // maximum count of browsers running at one time
    static EnvProperties envProperties = new EnvProperties();
    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    private DriverSingleton() {
    }

    public static WebDriver getInstance() {
        if (webDriverThreadLocal.get() != null) {
            return webDriverThreadLocal.get();
        }
        System.setProperty(envProperties.getDriverType(), envProperties.getDriverPath());
        synchronized (webDriverThreadLocal) {
            while (counter == THREAD_COUNT) {
                webDriverThreadLocal.notify();
            }
            counter++;
            WebDriver instance = new ChromeDriver();
            instance.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            webDriverThreadLocal.set(instance);
        }
        return webDriverThreadLocal.get();
    }

    public static void quit() {
        try {
            counter--;
            webDriverThreadLocal.get().quit();
        } finally {
            webDriverThreadLocal.remove();
        }
    }
}