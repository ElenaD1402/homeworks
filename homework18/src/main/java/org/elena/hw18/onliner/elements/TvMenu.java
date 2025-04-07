package org.elena.hw18.onliner.elements;

import org.elena.hw18.onliner.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.elena.hw18.onliner.webDriver.Browser.TIME_OUT_IN_SECONDS;

public class TvMenu {

    private final static By TV_MANUFACTURER_SCROLL =
            By.xpath("//*[@class='catalog-form__label-title' and contains(text(),'Производитель')]");

    private final static String TV_FILTER_PATH = "//*[contains(@class,'checkbox-item')][text()='%s']";

    private final static By WAIT_SEARCHING =
            By.xpath("//*[@class='catalog-interaction__state catalog-interaction__state_initial catalog-interaction__state_disabled catalog-interaction__state_control']");

    private final static By TV_ALL_RESULTS_FILTER =
            By.xpath("//*[@class='catalog-form__link catalog-form__link_primary-additional catalog-form__link_base-additional catalog-form__link_font-weight_semibold catalog-form__link_nodecor']");

    public WebElement scrollToManufacturer() {
        return Browser.getWebDriver().findElement(TV_MANUFACTURER_SCROLL);
    }

    public void tvManufacturerFilter(String tvManufacturerName) {
        WebElement tvFilter = Browser.getWebDriver().findElement(By.xpath(String.format(TV_FILTER_PATH, tvManufacturerName)));
        tvFilter.click();
    }

    public void waitSearching() {
        WebDriverWait wait = new WebDriverWait(Browser.getWebDriver(), Duration.ofSeconds(TIME_OUT_IN_SECONDS));
        wait.until(ExpectedConditions.presenceOfElementLocated(WAIT_SEARCHING));
    }

    public boolean tvManufacturerFilterResult(String tvManufacturerName) {
        List<WebElement> listFilterResult = Browser.getWebDriver().findElements(TV_ALL_RESULTS_FILTER);
        boolean trueFilter = true;
        for (WebElement element : listFilterResult) {
            if (!element.getText().contains(tvManufacturerName)) {
                trueFilter = false;
                break;
            }
        }
        return trueFilter;
    }
}
