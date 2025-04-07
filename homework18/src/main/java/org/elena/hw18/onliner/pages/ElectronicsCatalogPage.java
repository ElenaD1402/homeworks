package org.elena.hw18.onliner.pages;

import org.elena.hw18.onliner.elements.ElectronicsCatalogMenu;
import org.elena.hw18.onliner.elements.ElectronicsCatalogMenuEnum;
import org.elena.hw18.onliner.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ElectronicsCatalogPage extends CatalogPage {

    private static final By PAGE_LOCATOR =
            By.xpath("//div[@class='catalog-navigation-list catalog-navigation-list_active catalog-navigation-list_opened']//div[contains(text(),'Электроника')]");

    public static final By TV_ITEM_LOCATOR =
            By.xpath("//div[@class='catalog-navigation-list__dropdown']//a[@href='https://catalog.onliner.by/tv']");

    private final ElectronicsCatalogMenu electronicsCatalogMenu = new ElectronicsCatalogMenu();

    public ElectronicsCatalogPage() {
        super();
    }

    public ElectronicsCatalogMenu getElectronicsCatalogMenu() {
        return electronicsCatalogMenu;
    }

    @Override
    public boolean isPageOpened() {
        WebElement pageElement = Browser.waitForElementToBeVisible(PAGE_LOCATOR);
        return pageElement != null && pageElement.isDisplayed();
    }

    public void openTvPage(ElectronicsCatalogMenuEnum electronicsCatalogMenuEnum) {
        electronicsCatalogMenu.clickItem(electronicsCatalogMenuEnum);
        WebElement pageElement = Browser.waitForElementToBeClickable(TV_ITEM_LOCATOR);
        pageElement.click();
    }
}
