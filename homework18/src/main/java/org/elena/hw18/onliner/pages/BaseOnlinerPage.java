package org.elena.hw18.onliner.pages;

import org.elena.hw18.onliner.elements.TopMenu;

public abstract class BaseOnlinerPage {

    private TopMenu topMenu;

    public abstract boolean isPageOpened();

    protected BaseOnlinerPage() {
        topMenu = new TopMenu();
    }

    public TopMenu getTopMenu() {
        return topMenu;
    }
}
