package org.elena.hw18;

import org.elena.hw18.onliner.elements.CatalogMenuEnum;
import org.elena.hw18.onliner.elements.ElectronicsCatalogMenuEnum;
import org.elena.hw18.onliner.elements.TopMenuEnum;
import org.elena.hw18.onliner.pages.CatalogPage;
import org.elena.hw18.onliner.pages.ElectronicsCatalogPage;
import org.elena.hw18.onliner.pages.MainPage;
import org.elena.hw18.onliner.pages.TvElectronicsCatalogPage;
import org.elena.hw18.onliner.webDriver.Browser;
import org.elena.hw18.onliner.webDriver.Configuration;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OnlinerCatalogTest {

    CatalogPage catalogPage;
    ElectronicsCatalogPage electronicsCatalogPage;
    TvElectronicsCatalogPage tvElectronicsCatalogPage;

    @BeforeMethod
    public void initialize() {
        Browser.getWebDriver().get(Configuration.getBaseUrl());
        MainPage mainPage = new MainPage();
        mainPage.getTopMenu().clickItem(TopMenuEnum.CATALOG);
        catalogPage = new CatalogPage();
    }

    @Test
    public void testCatalogCanBeOpened() {
        Assert.assertTrue(catalogPage.isPageOpened(), "Каталог page is not opened");
    }

    @Test(dependsOnMethods = {"testCatalogCanBeOpened"})
    public void testElectronicsCatalogCanBeOpened() {
        catalogPage.getCatalogMenu().clickItem(CatalogMenuEnum.ELECTRONICS);
        electronicsCatalogPage = new ElectronicsCatalogPage();
        Assert.assertTrue(electronicsCatalogPage.isPageOpened(), "Электроника page is not opened");
    }

    @Test(dependsOnMethods = {"testElectronicsCatalogCanBeOpened"})
    public void testTvElectronicsCatalogCanBeOpened() throws InterruptedException {
        catalogPage.getCatalogMenu().clickItem(CatalogMenuEnum.ELECTRONICS);
        electronicsCatalogPage = new ElectronicsCatalogPage();
        electronicsCatalogPage.getElectronicsCatalogMenu().clickItem(ElectronicsCatalogMenuEnum.TV);
        electronicsCatalogPage.openTvItem();
        tvElectronicsCatalogPage = new TvElectronicsCatalogPage();
        Assert.assertTrue(tvElectronicsCatalogPage.isPageOpened(), "Телевизоры page is not opened");
    }

    @AfterMethod
    public void tearDown() {
        Browser.close();
    }
}
