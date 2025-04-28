package org.elena.hw18.onliner.elements;

import org.elena.hw18.onliner.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TvMenu {

    private final static By TV_MANUFACTURER_SCROLL =
            By.xpath("//*[@class='catalog-form__label-title' and contains(text(),'Производитель')]");

    private final static String TV_FILTER_PATH = "//*[contains(@class,'checkbox-item')][text()='%s']";

    private final static By WAIT_SEARCHING =
            By.xpath("//*[@class='catalog-interaction__state catalog-interaction__state_initial catalog-interaction__state_disabled catalog-interaction__state_control']");

    private final static By TV_ALL_RESULTS_FILTER =
            By.xpath("//*[@class='catalog-form__link catalog-form__link_primary-additional catalog-form__link_base-additional catalog-form__link_font-weight_semibold catalog-form__link_nodecor']");

    public WebElement scrollToManufacturer() {
        return Browser.waitForElementToBeVisible(TV_MANUFACTURER_SCROLL);
    }

    public void tvManufacturerFilter(String tvManufacturerName) {
        Browser.waitForElementToBeClickable(By.xpath(String.format(TV_FILTER_PATH, tvManufacturerName))).click();
    }

    public void waitSearching() {
        Browser.waitSearching(WAIT_SEARCHING);
    }

    public boolean tvManufacturerFilterResult(String tvManufacturerName) {
        List<WebElement> listFilterResult = Browser.getWebDriver().findElements(TV_ALL_RESULTS_FILTER);
        boolean trueFilter = false;
        for (WebElement element : listFilterResult) {
            if (element.getText().contains(tvManufacturerName)) {
                trueFilter = true;
            } else {
                trueFilter = false;
                break;
            }
        }
        return trueFilter;
    }
}
