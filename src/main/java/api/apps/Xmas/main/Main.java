package api.apps.Xmas.main;

import api.android.Android;
import api.apps.Xmas.menu.Menu;
import api.interfaces.Activity;
import core.MyLogger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

import java.util.concurrent.TimeUnit;

import static api.android.Android.driver;

/**
 * Created by User on 4/29/2017.
 */
public class Main implements Activity {

    public MainUiObjects uiObject = new MainUiObjects();

    @Override
    public Main waitToLoad(){
        try{
            MyLogger.log.info("Waiting for Main page.");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            if (!(uiObject.navigationContainer().size() > 0)){
                Android.app.xmas.menu.open();
                Android.app.xmas.menu.tapHome();
            }
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            return Android.app.xmas.main;
        }catch (AssertionError e) {
            throw new AssertionError("Main page failed to load/open");
        }
    }

    public Menu tapMenu(){
        try{
            MyLogger.log.info("Tap to Menu icon.");
            uiObject.menu().tap();
            return Android.app.xmas.menu.waitToLoad();
        }catch (AssertionError e) {
            throw new AssertionError("Menu icon failed to tap/load");
        }
    }

    public Main selectCategoryBar (String categoryBarName){
        MyLogger.log.info("Select category bar '"+categoryBarName+"'.");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Boolean bFlag = false;
        bFlag= scrollBarTo(categoryBarName);
        if (bFlag){
            uiObject.barItem(categoryBarName).tap();
            return Android.app.xmas.main.waitToLoad();
        } else throw new AssertionError("Category bar '"+categoryBarName+"' not found.");
    }

    public void checkedMenuBar (String categoryBarName, Boolean status){
        MyLogger.log.info("Category bar '"+categoryBarName+"' is active in application.");
        Boolean currStatus = uiObject.barItem(categoryBarName).isChecked();
        if (status.equals(currStatus)) MyLogger.log.info("Category bar '"+categoryBarName+"' active status is "+currStatus);
        else throw new AssertionError("Category bar '"+categoryBarName+"' active status is not "+status);
    }

    public Boolean selectCategory(String categoryName){
        MyLogger.log.info("Select category '"+categoryName+"'.");
        //waitToLoad();
        if (scrollToCategory(categoryName)) {
            uiObject.categoryItem(categoryName).tap();
            return Android.app.xmas.categories.categoryTitle_check(categoryName);
        }
        else throw new AssertionError("Category '"+categoryName+"' not found.");
    }

    public Boolean existItem(String itemName, Boolean flag){
        MyLogger.log.info("Check exist item category or effect named '"+itemName+"'.");
        if (scrollToCategory(itemName) == flag) return true;
        else {
            MyLogger.log.info("Check Irem '"+itemName+"' exists failed.");
            return false;
        }
    }

    private Boolean scrollToCategory(String categoryName){
        Dimension size;
        Boolean bflag = false;
        int endx = uiObject.category_area().getLocation().getX();
        int ind_count = uiObject.category_items().size() - 1;

        if (ind_count < 0) return false;
        else {
            String lastdesc; // = "";
            String currdesc; // = uiObject.categoryItem_last().getText();
            lastdesc = "";
            ind_count = uiObject.category_items().size() - 1;
            currdesc = uiObject.categoryItem_by_index(ind_count).getText();
            Boolean beofflag = false;

            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

            while ((!bflag) && (!beofflag)) {
                if (uiObject.categoryItem(categoryName).size() > 0) {
                    bflag = true;
                    MyLogger.log.info("Item '"+categoryName+"' found. ");
                    break;
                } else if (lastdesc.contains(currdesc)) {
                    beofflag = true;
                    MyLogger.log.info("Item '"+categoryName+"' is not found.");
                    break;
                }
                lastdesc = currdesc;
                size = uiObject.category_area().getSize();
                int endy = uiObject.category_area().getLocation().getY() + (int) (size.height * 0.2);
                int starty = uiObject.category_area().getLocation().getY() + (int) (size.height * 0.85);
                driver.swipe(endx + 150, starty, endx + 150, endy, 1400);

                ind_count = uiObject.category_items().size() - 1;
                currdesc = uiObject.categoryItem_by_index(ind_count).getText();

            }
            return bflag;
        }
    }

    private Boolean scrollBarTo(String itemName){
        if (uiObject.barItem(itemName).size() > 0) return true;
        else return false;
    }

}
