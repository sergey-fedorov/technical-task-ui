package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class StocksPage extends BasePage {

    @FindBy(css = "#DataTables_Table_0_filter input")
    private WebElement dataTableSearchFld;

    @FindBy(css = "#DataTables_Table_0 [data-xm-qa-name='symbolWithDescription']")
    private WebElement symbolWithDescription;

    @FindBy(xpath = "//tbody//*[not(@style='display: none;')]/a")
    private WebElement readMoreBtn;

    private String countryFilterByNameCss = ".table-country-filter [data-value='%s']";
    private String dataTableRowCss = "#DataTables_Table_0 tbody tr";
    private String dataTableRowValuesXpath = "(//*[@id='DataTables_Table_0']//td[not(contains(@style,'display: none') ) and @data-xm-qa-name] | //*[@id='DataTables_Table_0']//*[@class='dtr-data'])[position()!=last()][position()!=1]";


    public StocksPage filterByCountry(String country){
        clickElement(By.cssSelector(String.format(countryFilterByNameCss, country)));
        return this;
    }

    public StocksPage searchInDataTable(String searchQuery){
        type(dataTableSearchFld, searchQuery);
        return this;
    }

    public int getDataTableRowsCount(){
        return driver.findElements(By.cssSelector(dataTableRowCss)).size();
    }

    public String getRowSymbolWithDescription(){
        return waitUntilVisible(symbolWithDescription).getText();
    }

    public String getRowSymbolDescription(){
        return getRowSymbolWithDescription().substring(0, getRowSymbolWithDescription().indexOf('('));
    }

    public List<String> getRowContent(){
        List<String> rowData = driver.findElements(By.xpath(dataTableRowValuesXpath))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        rowData.add(getRowSymbolDescription());
        return rowData.stream().map(String::trim).sorted().toList();
    }

    public StocksPage expandRow(){
        clickElement(symbolWithDescription);
        return this;
    }

    public GenericStockPage clickRowReadMoreBtn(){
        clickElement(readMoreBtn);
        return new GenericStockPage();
    }

}
