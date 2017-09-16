package com.epam.lab.wrapper;

import org.openqa.selenium.WebElement;

/**
 *
 */
public class Label extends Element {
    public Label(WebElement webElement) {
        super(webElement);
    }
    public String getInnerHTML() {
        return webElement.getAttribute("innerHTML");
    }
}
