package components;

import org.openqa.selenium.By;
import utils.PageFactoryManager;

public class DesktopHeader extends Header {

    private String menuItemByNameXpath = "//*[contains(@class,'main_nav')]/a[contains(text(),'%s')]";
    private String subItemByNameXpath = "//*[@class='dropdown']//*[contains(text(),'%s')]";


    @Override
    public <T> T navigateTo(String menuItemName, String subItemName, Class<T> pageClass) {
        clickElement(By.xpath(String.format(menuItemByNameXpath, menuItemName)));
        clickElement(By.xpath(String.format(subItemByNameXpath, subItemName)));
        return PageFactoryManager.get(pageClass);
    }
}
