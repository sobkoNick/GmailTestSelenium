package com.epam.lab;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 */
public class EnvProperties {
    private Properties properties;

    public EnvProperties() {

        properties = new Properties();

        try {
            properties.load(new FileInputStream(new File("src\\main\\resources\\data.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getBaseUrl () {
        return properties.getProperty("baseUrl");
    }
    public String getDriverType() {
        return properties.getProperty("driverType");
    }
    public String getDriverPath() {
        return properties.getProperty("driverPath");
    }
    public String getXMLPathForUsers() {
        return properties.getProperty("xmlDataPathUser");
    }
    public String getXMLPathForMessages() {
        return properties.getProperty("xmlDataPathMessages");
    }
    public String getSendUrl() {
        return properties.getProperty("sendHref");
    }
}
