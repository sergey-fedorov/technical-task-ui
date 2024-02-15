package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import utils.ScreenSize;
import utils.WebDriverManager;

import java.util.List;

public class XmComEndToEndTests extends BaseTest {

    @DataProvider(name = "screens")
    public Object[][] getWindowSize() {
        return new Object[][] {
                { ScreenSize.SMALL },
                { ScreenSize.MEDIUM },
                { ScreenSize.MAX }
        };
    }

    @Test(dataProvider = "screens")
    public void verifyStockTradingConditions(ScreenSize screenSize) {
        HomePage homePage = new HomePage();
        StocksPage stocksPage = new StocksPage();
        GenericStockPage genericStockPage = new GenericStockPage();

        String stockName ="Orkla ASA (ORK.OL)";

        WebDriverManager.setWindowSize(screenSize);
        homePage.open()
                .getCommonComponent()
                .clickAcceptAllCookies()
                .clickCloseRiskDisclosure();
        homePage.getHeader(screenSize)
                .navigateTo("Trading", "Stock CFDs", StocksPage.class)
                .filterByCountry("Norway")
                .searchInDataTable(stockName);

        Assert.assertEquals(stocksPage.getDataTableRowsCount(), 1, "Wrong number of results returned");
        Assert.assertEquals(stocksPage.getRowSymbolWithDescription(), stockName, "Wrong search results returned");

        List<String> dataTableContent = stocksPage.expandRow().getRowContent();
        stocksPage.clickRowReadMoreBtn();
        List<String> tradingConditionsContent = genericStockPage.getTradingConditionsTableContent();

        Assert.assertEquals(tradingConditionsContent, dataTableContent, "Data comparison failed");
    }

}
