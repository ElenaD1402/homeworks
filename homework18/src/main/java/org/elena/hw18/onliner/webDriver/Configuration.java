package org.elena.hw18.onliner.webDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

    private static Properties properties;

    private Configuration() {
    }

    public static Properties getProperties() {
        if (properties == null) {
            initProperties();
        }
        return properties;
    }

    private static void initProperties() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/project.properties"));
        } catch (IOException ex) {
            System.out.println("Can't read properties");
        }
    }

    public static BrowserEnum getBrowserEnum() {
        return BrowserEnum.valueOf(getProperties().get("browser").toString());
    }

    public static String getBaseUrl() {
        return getProperties().get("baseUrl").toString();
    }

    public static String getDownloadDirectory() {
        return properties.get("downloadDirectory").toString();
    }

    public static String getRemoteDriverUrl() {
        return properties.getProperty("remoteWebDriverUrl");
    }
}
