package org.elena.hw18.onliner.pages;

import org.elena.hw18.onliner.elements.ElectronicsCatalogMenu;
import org.elena.hw18.onliner.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TvElectronicsCatalogPage extends CatalogPage {

    private static final By PAGE_LOCATOR =
            By.xpath("//div[@class='catalog-form__offers-list']");

    @Override
    public boolean isPageOpened() {
        WebElement pageElement = Browser.waitForElementToBeVisible(PAGE_LOCATOR);
        return pageElement != null && pageElement.isDisplayed();
    }

}
