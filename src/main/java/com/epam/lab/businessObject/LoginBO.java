package com.epam.lab.businessObject;

import com.epam.lab.EnvProperties;
import com.epam.lab.User;
import com.epam.lab.page.*;

/**
 *
 */
public class LoginBO {
    private EnvProperties envProperties = new EnvProperties();

    public String login(User user) {
        StartPage startPage = new StartPage();
        GmailLoginPage gmailLoginPage = (GmailLoginPage) startPage.goToLoginPage(envProperties.getBaseUrl());
        GmailInputPasswPage passwPage = (GmailInputPasswPage) gmailLoginPage.typeLoginAndSubmit(user.getLogin());
        passwPage.inputPasswordAndSubmit(user.getPassword());
        return passwPage.loginIsGood();
    }
}
