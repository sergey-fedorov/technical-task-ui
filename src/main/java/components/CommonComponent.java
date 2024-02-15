package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class CommonComponent extends BasePage {

    @FindBy(css = ".gtm-acceptDefaultCookieFirstVisit")
    private WebElement acceptCookiesBtn;

    @FindBy(css = "#js-riskCloseButton")
    private WebElement closeRiskDisclosureBtn;

    private String modalFadeCss = ".modal-backdrop";


    public CommonComponent clickAcceptAllCookies(){
        clickElementIgnoringException(acceptCookiesBtn);
        waitUntilNotPresent(By.cssSelector(modalFadeCss));
        return this;
    }

    public CommonComponent clickCloseRiskDisclosure(){
        clickElement(closeRiskDisclosureBtn);
        return this;
    }

}
