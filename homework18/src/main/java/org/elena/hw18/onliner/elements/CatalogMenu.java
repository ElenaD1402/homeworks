package org.elena.hw18.onliner.elements;

import org.elena.hw18.onliner.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CatalogMenu {

    private static final String ITEM_PATTERN = "//div[@class='catalog-navigation-classifier']/a//*[contains(text(),'%s')]";

    public void clickItem(CatalogMenuEnum catalogMenuEnum) {
        String xPath = String.format(ITEM_PATTERN, catalogMenuEnum.getValue());
        By catalogMenuItemLocator = By.xpath(xPath);
        WebElement element = Browser.waitForElementToBeClickable(catalogMenuItemLocator);
        element.click();
    }
}
