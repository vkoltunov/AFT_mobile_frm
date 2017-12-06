package api.apps.Xmas.menu;

import api.android.Android;
import api.apps.Xmas.about.About;
import api.apps.Xmas.categories.Categories;
import api.apps.Xmas.main.Main;
import api.interfaces.Activity;
import core.MyLogger;
import org.openqa.selenium.Dimension;

import java.util.concurrent.TimeUnit;

import static api.android.Android.driver;

/**
 * Created by User on 4/4/2017.
 */
public class Menu implements Activity {

    public MenuUiObjects uiObject = new MenuUiObjects();

    @Override
    public Menu waitToLoad(){
        try{
            MyLogger.log.info("Waiting for Menu bar");
            uiObject.home().waitToAppear(10);
            return Android.app.xmas.menu;
        }catch (AssertionError e) {
            throw new AssertionError("Menu bar failed to load/open");
        }
    }

    public Categories tapItem(String itemName){
        MyLogger.log.info("Tap menu item '"+itemName+"'.");
        if (scrollToItem(itemName)){
            uiObject.item(itemName).tap();
            return Android.app.xmas.categories.waitToLoad();
        }
        else throw new AssertionError("Menu item '"+itemName+"' tap failed.");
    }

    public Menu open(){
        try{
            MyLogger.log.info("Open Menu");
            Dimension size;
            size = driver.manage().window().getSize();
            int ypos = (size.getHeight()/2);
            int startx = (int)(size.width * 0.05);
            int endx = (int)(size.width * 0.9);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.swipe(startx, ypos, endx, ypos, 2200);
            if (uiObject.home().size() > 0) {
                return Android.app.xmas.menu;
            } else return null;
        }catch (AssertionError e) {
            throw new AssertionError("Menu activity failed to load/open");
        }
    }

    public Boolean itemExists(String itemName){
        MyLogger.log.info("Check is menu item '"+itemName+"' exists");
        if (scrollToItem(itemName)) return true;
        else {
            MyLogger.log.info("Check menu item '"+itemName+"' exists failed.");
            return false;
        }
    }

    public Main tapHome(){
        MyLogger.log.info("Tap menu Home button");
        //if (scrollToItem("Home")) {
        //    MyLogger.log.info("Scroll to Home is OK !");
            waitToLoad();
            uiObject.home().tap();
            return Android.app.xmas.main;
        //}
        //else throw new AssertionError("Menu Home button tap failed.");
    }

    public Menu tapMenu(){
        MyLogger.log.info("Tap menu button.");
        if (uiObject.menu().size() > 0) {
            uiObject.menu().tap();
            return Android.app.xmas.menu.waitToLoad();
        }
        else throw new AssertionError("Menu button isn't available.");
    }

    public About tapAbout(){
        MyLogger.log.info("Tap menu About button");
        if (scrollToItem("About")){
            uiObject.about().tap();
            return Android.app.xmas.about;
        }
        else throw new AssertionError("Menu About button tap failed.");
    }

    private Boolean scrollToItem(String pointName){
        Dimension size;
        Boolean bflag = false;

        size = driver.manage().window().getSize();
        int startx = (int) (size.width * 0.1);
        int starty = (int) (size.height * 0.9);
        int endy = (int) (size.height * 0.1);

        int ind_count = uiObject.list_item().size() - 1;

        String lastdesc = "";
        String currdesc = uiObject.by_index(ind_count).getText();
        Boolean beofflag = false;

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        if (pointName.contains("Home")){
            MyLogger.log.info("It's HOME!");
            if (uiObject.home().size() != 0) bflag = true;
        }

        while ((!bflag) && (!beofflag)) {
            if (uiObject.item(pointName).size() > 0) {
                bflag = true;
                MyLogger.log.info("Menu point found. ");
                break;
            } else if (lastdesc.contains(currdesc)) {
                beofflag = true;
                MyLogger.log.info("Menu point is not found.");
            }

            lastdesc = currdesc;
            driver.swipe(startx + 20, starty, startx + 20, endy, 1000);
            //ind_count = uiObject.list_item().size() - 1;
            currdesc = uiObject.last_item().getText();
        }
        return bflag;
    }

}
