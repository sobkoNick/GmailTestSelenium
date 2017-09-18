package com.epam.lab.page;

import com.epam.lab.wrapper.Button;
import com.epam.lab.wrapper.CheckBox;
import com.epam.lab.wrapper.Label;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

/**
 *
 */
public class SendPage extends PageObject {
    private static final Logger LOGGER = Logger.getLogger(SendPage.class);

    @FindBy(xpath = "//*[@class=\"xS\"]/div/div[2]")
    private Label subjectElement;

    @FindBy(xpath = "/html/body/div[7]/div[3]/div/div[2]/div[1]/div[2]/div/div/div/div/div[2]/div/div/div/div[2]/div[3]/div[1]/div/table/tbody/tr[1]/td[2]/div")
    private CheckBox mailCheckBox;

    @FindBy(xpath = "//*[@class=\"aeH\"]/div[2]/div[1]/div[1]/div/div/div[2]/div[3]/div")
    private Button deleteMessageBtn;

    @FindBy(xpath = "//button[@name=\"ok\"]")
    private Button okDeleteBtn;

    public PageObject verifyInSendPage(String subject) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("elem = " + subjectElement.getText());
        if (subjectElement.getText().toLowerCase().contains(subject.toLowerCase())) {
            return this;
        } else {
            return null;
        }
    }

    public PageObject removeMessage() {
        mailCheckBox.clickOnCheckField();
        deleteMessageBtn.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        okDeleteBtn.click();
        return this;
    }

    public String verifyDeleteMessageApear() {
//        try {
            return getDriver().findElement(
                    By.xpath("/html/body/div[7]/div[3]/div/div[1]/div[5]/div[1]/div/div[3]/div/div/div[2]")).getText();
//        } finally {
////            getDriver().quit();
//        }
    }
}

