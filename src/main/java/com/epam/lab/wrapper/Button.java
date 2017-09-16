package com.epam.lab.wrapper;

import org.openqa.selenium.WebElement;

/**
 *
 */
public class Button extends Element {
    public Button(WebElement webElement) {
        super(webElement);
    }
    public void click() {
        webElement.click();
    }
}
