package org.elena.hw18.onliner.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Button {

    private final WebElement webElement;

    public Button(WebElement webElement) {
        this.webElement = webElement;
    }

    public String getText() {
        return webElement.findElement(By.tagName("span")).getText();
    }

    public void click() {
        webElement.click();
    }

    public boolean isEnabled() {
        return webElement.isEnabled();
    }
}
