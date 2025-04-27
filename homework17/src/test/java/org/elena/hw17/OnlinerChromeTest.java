package org.elena.hw17;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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

    @DataProvider(name = "paramsTopMenuList")
    public static Object[][] paramsTopMenuList() {
        return new Object[][]{
                {"a[href='https://catalog.onliner.by']", "Каталог"},
                {"a[href='https://www.onliner.by']", "Новости"},
                {"a[href='https://ab.onliner.by']", "Автобарахолка"},
                {"a[href='https://r.onliner.by/pk']", "Дома и квартиры"},
                {"a[href='https://s.onliner.by/tasks']", "Услуги"},
                {"a[href='https://baraholka.onliner.by/']", "Барахолка"},
                {"a[href='https://forum.onliner.by/']", "Форум"}
        };
    }

    @Test(dependsOnMethods = {"testOnlinerPageCanBeOpened"}, dataProvider = "paramsTopMenuList")
    public void testTopMenuList(String cssSelector, String topMenuItem) {
        By searchTopMenuLocator = By.className("b-main-navigation");
        WebElement searchTopMenu = webDriver.findElement(searchTopMenuLocator);
        try {
            Assert.assertTrue(searchTopMenu.findElement(By.cssSelector(cssSelector)).isDisplayed());
        } catch (NoSuchElementException ex) {
            throw new RuntimeException("\"" + topMenuItem + "\" is not found");
        }
    }
}
