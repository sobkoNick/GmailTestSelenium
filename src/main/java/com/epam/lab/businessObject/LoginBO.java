package com.epam.lab.businessObject;

import com.epam.lab.EnvProperties;
import com.epam.lab.UserAndMessageDetails;
import com.epam.lab.page.*;
import org.openqa.selenium.WebDriver;

/**
 *
 */
public class LoginBO {
    private EnvProperties envProperties = new EnvProperties();

    public String login(UserAndMessageDetails user) {
        StartPage startPage = new StartPage();
        GmailLoginPage gmailLoginPage = (GmailLoginPage) startPage.goToLoginPage(envProperties.getBaseUrl());
        GmailInputPasswPage passwPage = (GmailInputPasswPage) gmailLoginPage.typeLoginAndSubmit(user.getLogin());
        passwPage.inputPasswordAndSubmit(user.getPassword());
        return passwPage.loginIsGood();
    }
}
