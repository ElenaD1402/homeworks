package org.elena.hw18.onliner.elements;

import org.elena.hw18.onliner.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ElectronicsCatalogMenu {

    private static final String ITEM_PATTERN = "//div[@class='catalog-navigation-list__aside-list']/div/a[contains(text(),'%s')]";

    public void clickItem(ElectronicsCatalogMenuEnum electronicsCatalogMenuEnum) {
        String xPath = String.format(ITEM_PATTERN, electronicsCatalogMenuEnum.getValue());
        By electronicsCatalogMenuItemLocator = By.xpath(xPath);
        WebElement element = Browser.waitForElementToBeClickable(electronicsCatalogMenuItemLocator);
        element.click();
    }
}
