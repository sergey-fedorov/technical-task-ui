package utils;

import java.lang.reflect.InvocationTargetException;

public final class PageFactoryManager {

    public static  <T> T get(Class<T> pageClass){
        T page = null;
        try {
            page = pageClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e ) {
            e.printStackTrace();
        }
        return page;
    }

}
