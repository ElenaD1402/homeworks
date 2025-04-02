package org.elena.hw20.webDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class BrowserFactory {

    static {
        System.setProperty("webdriver.chrome.driver", "c:/chromedriver.exe");
    }

    public static WebDriver createDriver(BrowserEnum browserEnum) {
        WebDriver webDriver;
        switch (browserEnum) {
            case CHROME -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("safebrowsing.enabled", true);
                prefs.put("download.default_directory", "C:\\Users\\Lena\\Downloads\\");
                prefs.put("browser.helperApps.neverAsk.saveToDisk", "application/pdf;text/csv;application/octet-stream;application/x-msdownload");
                chromeOptions.setExperimentalOption("prefs", prefs);
                webDriver = new ChromeDriver(chromeOptions);
            }
            default -> {
                throw new RuntimeException(browserEnum + " is not supported");
            }
        }
        return webDriver;
    }
}
