package com.epam.lab.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DriverSingleton {
    private static int counterOfRunningThreads = 0;
    static EnvProperties envProperties = new EnvProperties();
    private static final int MAX_THREAD_COUNT = envProperties.getParallelTestsCount(); // maximum count of browsers running at one time
    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    private DriverSingleton() {
    }

    public static WebDriver getInstance() {
        if (webDriverThreadLocal.get() != null) {
            return webDriverThreadLocal.get();
        }
        System.setProperty(envProperties.getDriverType(), envProperties.getDriverPath());
        synchronized (webDriverThreadLocal) {
            while (counterOfRunningThreads == MAX_THREAD_COUNT) {
                webDriverThreadLocal.notify();
            }
            counterOfRunningThreads++;
            WebDriver webDriver = new ChromeDriver();
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            webDriverThreadLocal.set(webDriver);
        }
        return webDriverThreadLocal.get();
    }

    public static void quit() {
        try {
            counterOfRunningThreads--;
            webDriverThreadLocal.get().quit();
        } finally {
            webDriverThreadLocal.remove();
        }
    }
}