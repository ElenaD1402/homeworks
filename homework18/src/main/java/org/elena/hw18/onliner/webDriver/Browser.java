package org.elena.hw18.onliner.webDriver;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Browser {

    public static final long DEFAULT_TIME_OUT = 5L;
    public static final int TIME_OUT_IN_SECONDS = 10;

    private static WebDriver webDriver;

    private Browser() {
    }

    public static WebDriver getWebDriver() {
        if (webDriver == null) {
            initDriver();
        }
        return webDriver;
    }

    public static JavascriptExecutor getJavascriptExecutor() {
        return (JavascriptExecutor) getWebDriver();
    }

    public static void initDriver() {
        webDriver = BrowserFactory.createDriver(Configuration.getBrowserEnum());
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(DEFAULT_TIME_OUT));
    }

    public static WebElement waitForElementToBeClickable(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(TIME_OUT_IN_SECONDS));
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
            WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(TIME_OUT_IN_SECONDS));
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
