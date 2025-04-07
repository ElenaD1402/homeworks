package org.elena.hw18.onliner.webDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
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
                webDriver = new ChromeDriver(getChromeOptions());
            }
            case REMOTE_CHROME -> {
                try {
                    webDriver = new RemoteWebDriver(new URL(Configuration.getRemoteDriverUrl()),getChromeOptions());
                } catch (MalformedURLException ex) {
                    System.out.println("Cannot create a driver with URL = " + Configuration.getRemoteDriverUrl());
                    webDriver = null;
                }

            }
            case FIREFOX -> {
                webDriver = new FirefoxDriver();
            }
            case REMOTE_FIREFOX -> {
                try {
                    var firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
                    webDriver = new RemoteWebDriver(new URL(Configuration.getRemoteDriverUrl()), firefoxOptions);
                } catch (MalformedURLException ex) {
                    System.out.println("Cannot create a driver with URL = " + Configuration.getRemoteDriverUrl());
                    webDriver = null;
                }
            }
            case EDGE -> {
                webDriver = new EdgeDriver();
            }
            case REMOTE_EDGE -> {
                try {
                    webDriver = new RemoteWebDriver(new URL(Configuration.getRemoteDriverUrl()), new EdgeOptions());
                } catch (MalformedURLException e) {
                    System.out.println("Cannot create a driver with URL = " + Configuration.getRemoteDriverUrl());
                    webDriver = null;
                }
            }
            default -> {
                throw new RuntimeException(browserEnum + " is not supported");
            }
        }
        return webDriver;
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        Map<String,Object> prefs = new HashMap<>();
        prefs.put("safebrowsing.enabled", true);
        prefs.put("download.default_directory","C:\\Users\\Lena\\Downloads\\");
        prefs.put("browser.helperApps.neverAsk.saveToDisk","application/pdf;text/csv;application/octet-stream;application/x-msdownload");
        chromeOptions.setExperimentalOption("prefs",prefs);
        chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        return chromeOptions;
    }
}
