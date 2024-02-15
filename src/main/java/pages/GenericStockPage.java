package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GenericStockPage extends BasePage {

    @FindBy(css = "#instrument-inner-page")
    private WebElement tradingConditionsTable;

    @FindBy(css = "[data-xm-qa-name='margin_requirement__value'] .red")
    private WebElement marginRequirementValue;

    @FindBy(css = "[data-xm-qa-name='symbols__value'] .red")
    private WebElement symbolValue;

    @FindBy(css = "[data-xm-qa-name='description__value'] .red")
    private WebElement descriptionValue;

    @FindBy(css = ".hidden-xs [data-xm-qa-name='spreads_as_low_as__value'] .red")
    private WebElement spreadsAsLowAsValue;

    @FindBy(css = "[data-xm-qa-name='min_max_trade_size__value'] .red")
    private WebElement minMaxTradeSizeValue;

    @FindBy(css = "[data-xm-qa-name='swap_value_in_margin_currency_long__value'] .red")
    private WebElement swapValueInMarginCurrencyLongValue;

    @FindBy(css = "[data-xm-qa-name='swap_value_in_margin_currency_short__value'] .red")
    private WebElement swapValueInMarginCurrencyShortValue;

    @FindBy(css = "[data-xm-qa-name='limit_and_stop_levels__title'] .red")
    private WebElement limitAndStopLevelsValue;


    public List<String> getTradingConditionsTableContent(){
        waitUntilVisible(tradingConditionsTable);
        List<String> tradingConditionsContent = List.of(
                marginRequirementValue.getText(),
                symbolValue.getText(),
                descriptionValue.getText(),
                spreadsAsLowAsValue.getText(),
                minMaxTradeSizeValue.getText(),
                swapValueInMarginCurrencyLongValue.getText(),
                swapValueInMarginCurrencyShortValue.getText(),
                limitAndStopLevelsValue.getText()
        );
        return tradingConditionsContent.stream().map(String::trim).sorted().toList();
    }

}
