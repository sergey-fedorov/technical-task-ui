package pages;

import components.CommonComponent;
import components.DesktopHeader;
import components.Header;
import components.MobileHeader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import utils.ScreenSize;
import utils.WebDriverManager;

public abstract class BasePage {

    protected WebDriver driver;
    protected Wait<WebDriver> wait;
    private final Wait<WebDriver> fluentWait;

    public BasePage(){
        driver = WebDriverManager.getWebDriver();
        wait = WebDriverManager.getExplicitWait();
        fluentWait = WebDriverManager.getFluentWait();
        PageFactory.initElements(driver, this);
    }

    public Header getHeader(ScreenSize screenSize){
        return screenSize.equals(ScreenSize.SMALL) ? new MobileHeader() : new DesktopHeader();
    }

    public CommonComponent getCommonComponent(){
        return new CommonComponent();
    }


    protected void openUrl(String url){
        driver.get(url);
    }

    protected WebElement waitUntilVisible(WebElement webElement){
        return wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    protected WebElement waitUntilClickable(By by){
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    protected void waitUntilNotPresent(By by){
        wait.until(ExpectedConditions.numberOfElementsToBe(by, 0));
    }

    protected WebElement waitUntilClickable(WebElement webElement){
        return wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    protected void clickElement(WebElement webElement){
        waitUntilClickable(webElement).click();
    }

    protected void clickElement(By by){
        waitUntilClickable(by).click();
    }

    protected void clickElementIgnoringException(WebElement webElement){
        fluentWait.until(d ->
                {
                    webElement.click();
                    return true;
                }
        );
    }

    protected void type(WebElement webElement, CharSequence... keysToSend){
        waitUntilVisible(webElement).sendKeys(keysToSend);
    }


}
