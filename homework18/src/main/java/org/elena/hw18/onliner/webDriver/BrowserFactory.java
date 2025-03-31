package org.elena.hw18.onliner.webDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;
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
//                prefs.put("excludeSwitches", Arrays.asList("disable-popup-blocking"));
//                chromeOptions.addArguments("--user-data-dir=C:\\Users\\Lena\\AppData\\Local\\Google\\Chrome\\User Data");
//                chromeOptions.addArguments("--profile-directory=Profile 2");
                chromeOptions.setExperimentalOption("prefs", prefs);
                webDriver = new ChromeDriver(chromeOptions);
            }
            case FIREFOX -> {
                webDriver = new FirefoxDriver();
            }
            default -> {
                throw new RuntimeException(browserEnum + " is not supported");
            }
        }
        return webDriver;
    }
}
