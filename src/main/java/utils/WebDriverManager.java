package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebDriverManager {

    private static final int IMPLICIT_TIMEOUT = 5;
    private static final int EXPLICIT_TIMEOUT = 10;

    private static WebDriver webDriver;

    public static Wait<WebDriver> getExplicitWait(){
        return new WebDriverWait(getWebDriver(), Duration.ofSeconds(EXPLICIT_TIMEOUT));
    }

    public static Wait<WebDriver> getFluentWait(){
        return new FluentWait<>(getWebDriver())
                        .withTimeout(Duration.ofSeconds(EXPLICIT_TIMEOUT))
                        .pollingEvery(Duration.ofMillis(300))
                        .ignoring(ElementNotInteractableException.class)
                        .ignoring(NotFoundException.class);
    }

    public static WebDriver getWebDriver(){
        if(webDriver == null)
            initWebDriver();
        return webDriver;
    }

    private static void initWebDriver(){
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        ChromeOptions chromeOptions = new ChromeOptions();
        if(headless) chromeOptions.addArguments("--headless=new");
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
        webDriver = new ChromeDriver(chromeOptions);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_TIMEOUT));
    }

    public static void setWindowSize(ScreenSize screenSize) {
        switch (screenSize) {
            case SMALL -> setWindowSize(800, 600);
            case MEDIUM -> setWindowSize(1024, 768);
            case MAX -> maximizeWindowSize();
        }
    }

    public static void setWindowSize(int width, int height) {
        getWebDriver().manage().window().setSize(new Dimension(width, height));
    }

    public static void maximizeWindowSize() {
        getWebDriver().manage().window().maximize();
    }

    public static void quit() {
        getWebDriver().quit();
        webDriver = null;
    }

}
