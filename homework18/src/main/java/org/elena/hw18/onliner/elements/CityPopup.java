package org.elena.hw18.onliner.elements;

import org.elena.hw18.onliner.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CityPopup {

    private static final By CITY_POPUP =
            By.xpath("//*[contains(@class,'popover-style__container')]");

    private static final By CITY_SELECTOR =
            By.xpath("//*[contains(@class,'small-alter')]");

    public WebElement scrollToCityPopup() {
        return Browser.waitForElementToBeVisible(CITY_POPUP);
    }

    public void acceptCityPopup() {
        Browser.waitForElementToBeClickable(CITY_SELECTOR).click();
    }
}
