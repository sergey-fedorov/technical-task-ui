package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.PageFactoryManager;

public class MobileHeader extends Header {

    @FindBy(css = ".toggleLeftNav")
    private WebElement menuHamburger;

    @FindBy(css = "#navigation-collapse")
    private WebElement menuPanel;

    private String expandableMenuItemByNameXpath = "//*[@id='navigation-collapse']//*[text()='Trading']//ancestor::a";
    private String subItemByNameXpath= "//*[@class='navbar-nav__list']//*[contains(text(),'%s')]";

    @Override
    public <T> T navigateTo(String menuItemName, String subItemName, Class<T> pageClass) {
        clickElement(menuHamburger);
        waitUntilVisible(menuPanel);
        clickElement(By.xpath(String.format(expandableMenuItemByNameXpath, menuItemName)));
        clickElement(By.xpath(String.format(subItemByNameXpath, subItemName)));
        return PageFactoryManager.get(pageClass);
    }

}
