package com.epam.lab.wrapper;

import org.openqa.selenium.WebElement;

/**
 *
 */
public class Input extends Element {
    public Input(WebElement webElement) {
        super(webElement);
    }
    public void setText(String text) {
        webElement.sendKeys(text);
    }
}
