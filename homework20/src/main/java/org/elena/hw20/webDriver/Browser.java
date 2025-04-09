package org.elena.hw20.webDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Browser {

    public static final long DEFAULT_TIME_OUT = 10L;
    public static final int TIME_OUT_IN_SECONDS = 30;

    private static WebDriver webDriver;

    private Browser() {
    }

    public static WebDriver getWebDriver() {
        if (webDriver == null) {
            initDriver();
        }
        return webDriver;
    }

    public static void initDriver() {
        webDriver = BrowserFactory.createDriver(Configuration.getBrowserEnum());
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().pageLoadTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
    }

    public static WebElement waitForElementToBeClickable(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver(), TIME_OUT_IN_SECONDS);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            WebElement element = getWebDriver().findElement(locator);
            return element;
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static WebElement waitForElementToBeVisible(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver(), TIME_OUT_IN_SECONDS);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            WebElement element = getWebDriver().findElement(locator);
            return element;
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static void close() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }
}
