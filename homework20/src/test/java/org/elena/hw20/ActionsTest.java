package org.elena.hw20;

import org.elena.hw20.webDriver.Browser;
import org.elena.hw20.webDriver.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ActionsTest {

    @Test
    public void testResizable() {
        Browser.getWebDriver().get(Configuration.getBaseUrl() + "/resizable/");
        WebElement frame = Browser.getWebDriver().findElement(By.className("demo-frame"));
        Browser.getWebDriver().switchTo().frame(frame);
        WebElement source = Browser.getWebDriver().findElement(By.id("resizable"));
        int sourceHeight = source.getSize().getHeight();
        int sourceWidth = source.getSize().getWidth();
        System.out.println("Source Height = " + sourceHeight + ", Source Width = " + sourceWidth);
        WebElement corner = source.findElement(By.className("ui-icon-gripsmall-diagonal-se"));
        Actions resize = new Actions(Browser.getWebDriver());
        int x = 100;
        int y = 150;
        resize.clickAndHold(corner).moveByOffset(x, y).release().perform();
        int resizedSourceHeight = source.getSize().getHeight();
        int resizedSourceWidth = source.getSize().getWidth();
        Browser.getWebDriver().switchTo().defaultContent();
        System.out.println("Resized Source Height = " + resizedSourceHeight + ", Resized Source Width = " + resizedSourceWidth);
        Assert.assertEquals(resizedSourceHeight, sourceHeight + y, "Height is " + resizedSourceHeight + ", but should be " + (sourceHeight + y));
        Assert.assertEquals(resizedSourceWidth, sourceWidth + x, "Width is " + resizedSourceWidth + ", but should be " + (sourceWidth + x));
    }

    @Test
    public void testSelectable() {
        Browser.getWebDriver().get(Configuration.getBaseUrl() + "/selectable/");
        WebElement frame = Browser.getWebDriver().findElement(By.className("demo-frame"));
        Browser.getWebDriver().switchTo().frame(frame);
        WebElement list = Browser.getWebDriver().findElement(By.id("selectable"));
        List<WebElement> listItems = new ArrayList<>(list.findElements(By.xpath("//li[contains(text(),'Item ')]")));
        for (WebElement item : listItems) {
            Assert.assertFalse(item.getAttribute("class").contains("ui-selected"), "Some items are already selected");
        }
        String ITEM_PATTERN = "//li[contains(text(),'Item %s')]";
        Actions select = new Actions(Browser.getWebDriver());
        select.keyDown(Keys.CONTROL).click(list.findElement(By.xpath(String.format(ITEM_PATTERN, 1))))
                .click(list.findElement(By.xpath(String.format(ITEM_PATTERN, 3)))).keyUp(Keys.CONTROL).perform();
        int counter = 0;
        for (WebElement item : listItems) {
            if (item.getAttribute("class").contains("ui-selected")) {
                counter++;
                System.out.println(item.getText());
            }
        }
        Browser.getWebDriver().switchTo().defaultContent();
        System.out.println("Selected items count is " + counter);
        Assert.assertNotEquals(counter, 0, "Selected items count is more than 0");
    }

    @Test
    public void testSortable() {
        String[] expectedArrayBeforeSorting = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7"};
        String[] expectedArrayAfterSorting = {"Item 7", "Item 6", "Item 5", "Item 4", "Item 3", "Item 2", "Item 1"};
        Browser.getWebDriver().get(Configuration.getBaseUrl() + "/sortable/");
        WebElement frame = Browser.getWebDriver().findElement(By.className("demo-frame"));
        Browser.getWebDriver().switchTo().frame(frame);
        WebElement list = Browser.getWebDriver().findElement(By.id("sortable"));
        List<WebElement> listItemsBeforeSorting = new ArrayList<>(list.findElements(By.xpath("//li[contains(text(),'Item ')]")));
        String[] actualArrayBeforeSorting = new String[listItemsBeforeSorting.size()];
        int i = 0;
        for (WebElement item : listItemsBeforeSorting) {
            System.out.println(item.getText());
            actualArrayBeforeSorting[i] = item.getText();
            i++;
        }
        Assert.assertEquals(actualArrayBeforeSorting, expectedArrayBeforeSorting, "Items must be sorted in ascending order");
        String ITEM_PATTERN = "//li[contains(text(),'Item %s')]";
        Actions sort = new Actions(Browser.getWebDriver());
        sort.clickAndHold(list.findElement(By.xpath(String.format(ITEM_PATTERN, 7))))
                .moveToElement(list.findElement(By.xpath(String.format(ITEM_PATTERN, 1)))).moveByOffset(0, -5)
                .release().clickAndHold(list.findElement(By.xpath(String.format(ITEM_PATTERN, 6))))
                .moveToElement(list.findElement(By.xpath(String.format(ITEM_PATTERN, 1)))).moveByOffset(0, -5)
                .release().clickAndHold(list.findElement(By.xpath(String.format(ITEM_PATTERN, 5))))
                .moveToElement(list.findElement(By.xpath(String.format(ITEM_PATTERN, 1)))).moveByOffset(0, -5)
                .release().clickAndHold(list.findElement(By.xpath(String.format(ITEM_PATTERN, 1))))
                .moveToElement(list.findElement(By.xpath(String.format(ITEM_PATTERN, 4)))).moveByOffset(0, 5)
                .release().clickAndHold(list.findElement(By.xpath(String.format(ITEM_PATTERN, 2))))
                .moveToElement(list.findElement(By.xpath(String.format(ITEM_PATTERN, 4)))).moveByOffset(0, 5)
                .release().clickAndHold(list.findElement(By.xpath(String.format(ITEM_PATTERN, 3))))
                .moveToElement(list.findElement(By.xpath(String.format(ITEM_PATTERN, 4)))).moveByOffset(0, 5)
                .release().perform();
        List<WebElement> listItemsAfterSorting = new ArrayList<>(list.findElements(By.xpath("//li[contains(text(),'Item ')]")));
        String[] actualArrayAfterSorting = new String[listItemsAfterSorting.size()];
        int j = 0;
        for (WebElement item : listItemsAfterSorting) {
            System.out.println(item.getText());
            actualArrayAfterSorting[j] = item.getText();
            j++;
        }
        Browser.getWebDriver().switchTo().defaultContent();
        Assert.assertEquals(actualArrayAfterSorting, expectedArrayAfterSorting, "Items must be sorted in descending order");
    }

    @AfterMethod
    public void tearDown() {
        Browser.close();
    }
}
