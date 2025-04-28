package org.elena.hw18.onliner.elements;

import org.elena.hw18.onliner.webDriver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TopMenu {

    private static final String ITEM_PATTERN = "//nav//*[contains(text(),'%s')]";

    public void clickItem(TopMenuEnum topMenuEnum) {
        String xPath = String.format(ITEM_PATTERN, topMenuEnum.getValue());
        By topMenuItemLocator = By.xpath(xPath);
        WebElement element = Browser.waitForElementToBeClickable(topMenuItemLocator);
        element.click();
    }
}
