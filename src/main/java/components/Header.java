package components;


import pages.BasePage;

public abstract class Header extends BasePage {

    public abstract <T> T navigateTo(String menuItemName, String subItemName, Class<T> pageClass);

}
