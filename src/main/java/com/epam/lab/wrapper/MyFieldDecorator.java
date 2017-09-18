package com.epam.lab.wrapper;

/**
 *
 */
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.lang.reflect.Field;

public class MyFieldDecorator extends DefaultFieldDecorator {
    public MyFieldDecorator(ElementLocatorFactory factory) {
        super(factory);
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        // decorates default WebElement
        if (WebElement.class.isAssignableFrom(field.getType())) {
            return super.decorate(loader, field);
        }
        // decorates wrappers
        else {
            if (Button.class.isAssignableFrom(field.getType())) {
                ElementLocator locator = factory.createLocator(field);
                Button button = new Button(proxyForLocator(loader, locator));
                return button;
            }
            else if(Label.class.isAssignableFrom(field.getType())) {
                ElementLocator locator = factory.createLocator(field);
                Label label = new Label(proxyForLocator(loader,locator));
                return label;
            } else if (Input.class.isAssignableFrom(field.getType())) {
                ElementLocator locator = factory.createLocator(field);
                Input input = new Input(proxyForLocator(loader, locator));
                return input;
            }
            else if (CheckBox.class.isAssignableFrom(field.getType())) {
                ElementLocator locator = factory.createLocator(field);
                CheckBox checkBox = new CheckBox(proxyForLocator(loader, locator));
                return checkBox;
            }
        }
        return null;
    }
}