package org.elena.hw18.onliner.pages.electronics;

import org.elena.hw18.onliner.elements.CityPopup;
import org.elena.hw18.onliner.elements.TvMenu;
import org.elena.hw18.onliner.pages.ElectronicsCatalogPage;
import org.elena.hw18.onliner.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TvPage extends ElectronicsCatalogPage {

    private static final By PAGE_LOCATOR =
            By.xpath("//div[@class='catalog-form__offers-list']");

    private final CityPopup cityPopup = new CityPopup();

    private final TvMenu tvMenu = new TvMenu();

    public TvPage() {
        super();
    }

    public CityPopup getCityPopup() {
        return cityPopup;
    }

    public TvMenu getTvMenu() {
        return tvMenu;
    }

    @Override
    public boolean isPageOpened() {
        WebElement pageElement = Browser.waitForElementToBeVisible(PAGE_LOCATOR);
        return pageElement != null && pageElement.isDisplayed();
    }
}
