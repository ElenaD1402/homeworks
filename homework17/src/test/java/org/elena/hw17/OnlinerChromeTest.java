package org.elena.hw17;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class OnlinerChromeTest {
    WebDriver webDriver;

    static {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver.exe");
    }

    @BeforeMethod
    public void initialize() {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.get("https://www.onliner.by/");
        webDriver.manage().timeouts().pageLoadTimeout(2L, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test
    public void testOnlinerPageCanBeOpened() {
        By searchTopMenuLocator = By.className("b-main-navigation");
        WebElement searchTopMenu = webDriver.findElement(searchTopMenuLocator);
        Assert.assertTrue(searchTopMenu.isDisplayed());
    }

    @Test(dependsOnMethods = {"testOnlinerPageCanBeOpened"})
    public void testTopMenuList() {
        By searchTopMenuLocator = By.className("b-main-navigation");
        WebElement searchTopMenu = webDriver.findElement(searchTopMenuLocator);
        SoftAssert topMenuListChecker = new SoftAssert();
        topMenuListChecker.assertTrue(searchTopMenu.findElement(By.cssSelector("a[href='https://catalog.onliner.by']")).isDisplayed());
        topMenuListChecker.assertTrue(searchTopMenu.findElement(By.cssSelector("a[href='https://www.onliner.by']")).isDisplayed());
        topMenuListChecker.assertTrue(searchTopMenu.findElement(By.cssSelector("a[href='https://ab.onliner.by']")).isDisplayed());
        topMenuListChecker.assertTrue(searchTopMenu.findElement(By.cssSelector("a[href='https://r.onliner.by/pk']")).isDisplayed());
        topMenuListChecker.assertTrue(searchTopMenu.findElement(By.cssSelector("a[href='https://s.onliner.by/tasks']")).isDisplayed());
        topMenuListChecker.assertTrue(searchTopMenu.findElement(By.cssSelector("a[href='https://baraholka.onliner.by/']")).isDisplayed());
        topMenuListChecker.assertTrue(searchTopMenu.findElement(By.cssSelector("a[href='https://forum.onliner.by/']")).isDisplayed());
        topMenuListChecker.assertAll();
    }
}
