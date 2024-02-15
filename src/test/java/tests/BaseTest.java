package tests;

import org.testng.annotations.*;
import utils.CustomListener;
import utils.WebDriverManager;

@Listeners({CustomListener.class})
public class BaseTest {

    @BeforeMethod
    public void openBrowser() {
        WebDriverManager.getWebDriver();
    }

    @AfterMethod
    public void driverQuit() {
        WebDriverManager.quit();
    }

}
