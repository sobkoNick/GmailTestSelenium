package com.epam.lab.wrapper;

import org.openqa.selenium.WebElement;

/**
 * Basic class for wrappers
 */
public class Element implements IElement {
    protected WebElement webElement;

    public Element(WebElement webElement) {
        this.webElement = webElement;
    }

    public String getText() {
        return webElement.getText();
    }
}
