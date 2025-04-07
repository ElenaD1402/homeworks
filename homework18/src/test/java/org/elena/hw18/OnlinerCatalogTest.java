package org.elena.hw18;

import org.elena.hw18.onliner.elements.CatalogMenuEnum;
import org.elena.hw18.onliner.elements.ElectronicsCatalogMenuEnum;
import org.elena.hw18.onliner.elements.TopMenuEnum;
import org.elena.hw18.onliner.pages.CatalogPage;
import org.elena.hw18.onliner.pages.ElectronicsCatalogPage;
import org.elena.hw18.onliner.pages.MainPage;
import org.elena.hw18.onliner.pages.electronics.TvPage;
import org.elena.hw18.onliner.webDriver.Browser;
import org.elena.hw18.onliner.webDriver.Configuration;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class OnlinerCatalogTest {

    CatalogPage catalogPage;
    ElectronicsCatalogPage electronicsCatalogPage;
    TvPage tvPage;

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
    public void testTvPageCanBeOpened() {
        catalogPage.getCatalogMenu().clickItem(CatalogMenuEnum.ELECTRONICS);
        electronicsCatalogPage = new ElectronicsCatalogPage();
        electronicsCatalogPage.getElectronicsCatalogMenu().clickItem(ElectronicsCatalogMenuEnum.TV);
        electronicsCatalogPage.openTvPage(ElectronicsCatalogMenuEnum.TV);
        tvPage = new TvPage();
        Assert.assertTrue(tvPage.isPageOpened(), "Телевизоры page is not opened");
    }

    @DataProvider(name = "tvManufacturer")
    private String[] getTvManufacturer() {
        return new String[]{"Samsung"};
    }

    @Test(dependsOnMethods = {"testTvPageCanBeOpened"}, dataProvider = "tvManufacturer")
    public void testTvManufacturerFilter(String nameTvManufacturer) {
        catalogPage.getCatalogMenu().clickItem(CatalogMenuEnum.ELECTRONICS);
        electronicsCatalogPage = new ElectronicsCatalogPage();
        electronicsCatalogPage.getElectronicsCatalogMenu().clickItem(ElectronicsCatalogMenuEnum.TV);
        electronicsCatalogPage.openTvPage(ElectronicsCatalogMenuEnum.TV);
        tvPage = new TvPage();
        Browser.getJavascriptExecutor().executeScript("arguments[0].scrollIntoView();", tvPage.getCityPopup().scrollToCityPopup());
        tvPage.getCityPopup().acceptCityPopup();
        Browser.getJavascriptExecutor().executeScript("arguments[0].scrollIntoView();", tvPage.getTvMenu().scrollToManufacturer());
        tvPage.getTvMenu().tvManufacturerFilter(nameTvManufacturer);
        tvPage.getTvMenu().waitSearching();
        Assert.assertTrue(tvPage.getTvMenu().tvManufacturerFilterResult(nameTvManufacturer),
                "Test is Failed, TV name doesn't contain " + nameTvManufacturer);
    }

    @AfterMethod
    public void tearDown(Method method) {
        System.out.println("Test " + method.getName() + " is finished");
        Browser.close();
    }
}
