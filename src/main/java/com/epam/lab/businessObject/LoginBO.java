package com.epam.lab.businessObject;

import com.epam.lab.util.EnvProperties;
import com.epam.lab.model.User;
import com.epam.lab.page.*;

/**
 *
 */
public class LoginBO {
    private EnvProperties envProperties;

    public LoginBO() {
        envProperties = new EnvProperties();
    }

    public String login(User user) {
        StartPage startPage = new StartPage();
        startPage.goToLoginPage(envProperties.getBaseUrl());
        GmailLoginPage gmailLoginPage = new GmailLoginPage();
        gmailLoginPage.typeLoginAndSubmit(user.getLogin());
        GmailInputPasswPage passwPage = new GmailInputPasswPage();
        passwPage.inputPasswordAndSubmit(user.getPassword());
        return passwPage.checkLoginComplete();
    }
}
