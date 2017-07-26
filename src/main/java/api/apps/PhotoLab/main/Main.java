package api.apps.PhotoLab.main;

import api.android.Android;
import api.apps.PhotoLab.categories.Categories;
import api.apps.PhotoLab.menu.Menu;
import api.apps.PhotoLab.pictures.Pictures;
import api.apps.PhotoLab.store.Store;
import api.interfaces.Activity;
import core.MyLogger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

import java.util.Properties;
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
            if (!(uiObject.actionBar().size() > 0)){
                Android.app.photoLab.menu.open();
                Android.app.photoLab.menu.tapHome();
            }
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            return Android.app.photoLab.main;
        }catch (AssertionError e) {
            throw new AssertionError("Main page failed to load/open");
        }
    }

    public Menu tapMenu(){
        try{
            MyLogger.log.info("Tap to Menu icon.");
            uiObject.menu().tap();
            return Android.app.photoLab.menu.waitToLoad();
        }catch (AssertionError e) {
            throw new AssertionError("Menu icon failed to tap/load");
        }
    }

    public Store tapPro(){
        try{
            MyLogger.log.info("Tap Pro icon.");
            uiObject.pro().tap();
            return Android.app.photoLab.store.waitToLoad();
        }catch (AssertionError e) {
            throw new AssertionError("Pro icon failed to tap/load");
        }
    }

    public void tapInstall(){
        try{
            MyLogger.log.info("Tap Install button.");
            uiObject.install().tap();
        }catch (AssertionError e) {
            throw new AssertionError("Install button failed to tap/load");
        }
    }

    public Main selectCategoryBar (String categoryBarName){
        MyLogger.log.info("Select category bar '"+categoryBarName+"'.");
        if (scrollBarTo(categoryBarName)){
            uiObject.barItem(categoryBarName).tap();
            return Android.app.photoLab.main.waitToLoad();
        }
        else throw new AssertionError("Category bar '"+categoryBarName+"' not found.");
    }

    public Categories selectCategory(String categoryName){
        MyLogger.log.info("Select category '"+categoryName+"'.");
        waitToLoad();
        if (scrollToCategory(categoryName)) {
            uiObject.categoryItem(categoryName).tap();
            return Android.app.photoLab.categories.waitToLoad();
        }
        else throw new AssertionError("Category '"+categoryName+"' not found.");
    }

    public Pictures selectItemByIndex(int index){
        try{
            MyLogger.log.info("Select inspiration element with index '"+index+"'.");
            waitToLoad();
            uiObject.inspirationItem(index).tap();
            return Android.app.photoLab.pictures.waitToLoad();
        }catch (AssertionError e) {
            throw new AssertionError("Inspiration element with index '"+index+"' not found.");
        }
    }

    public Pictures selectEffect(String effectName){
        MyLogger.log.info("Select effect '"+effectName+"'.");
        if (scrollToCategory(effectName)) {
            uiObject.categoryItem(effectName).tap();
            return Android.app.photoLab.pictures.waitToLoad();
        }
        else throw new AssertionError("Effect '"+effectName+"' not found.");
    }

    public Main existItem(String itemName, Boolean flag){
        MyLogger.log.info("Check exist item category or effect named '"+itemName+"'.");
        if (scrollToCategory(itemName) == flag) {

            MyLogger.log.info("Item category or effect '"+itemName+"' found result: "+ flag);
            return Android.app.photoLab.main;
        }
        else throw new AssertionError("Item category or effect named '"+itemName+"' found result: "+flag);
    }

    public Main tapDelete(){
        try{
            MyLogger.log.info("Tap to Delete button for selected effect.");
            uiObject.delete().tap();
            return Android.app.photoLab.main.waitToLoad();
        }catch (AssertionError e) {
            throw new AssertionError("Delete effect failed to tap.");
        }
    }

    public Main longClickEffect(String effectName){
        MyLogger.log.info("Long Click to effect named "+effectName);
        if (scrollToCategory(effectName)) {
            uiObject.categoryItem(effectName).longClick();
            return Android.app.photoLab.main.waitToLoad();
        } else throw new AssertionError("Long Click to effect failed. Maybe effect doesn't exist");
    }

    public Main selectBlock(String name){
        try{
            MyLogger.log.info("Select inspiration block '"+name+"'.");
            uiObject.inspirationBlock(name).tap();
            return Android.app.photoLab.main.waitToLoad();
        }catch (AssertionError e) {
            throw new AssertionError("Inspiration block '"+name+"' failed to select.");
        }
    }

    private Boolean scrollToCategory(String categoryName){
        Dimension size;
        Boolean bflag = false;

        //size = driver.manage().window().getSize();

        int endx = uiObject.category_area().getLocation().getX();
        size = uiObject.category_area().getSize();
        int endy = uiObject.category_area().getLocation().getY() + (int) (size.height * 0.2);
        int starty = uiObject.category_area().getLocation().getY() + (int) (size.height * 0.85);

        //int endx = (int) (size.width * 0.1);
        //int starty = (int) (size.height * 0.99);
        //int endy = (int) (size.height * 0.01);

        int ind_count = uiObject.category_items().size() - 1;

        if (ind_count < 0) return false;
        else {
            String lastdesc = "";
            String currdesc = uiObject.categoryItem_last().getText();

            while (!(uiObject.categoryItem_by_desc(0).size()>0)) {
                //driver.swipe(endx + 50, (int)(endy*1.5), endx + 50, (int)(starty*1.2), 1000);

                driver.swipe(endx + 50, endy, endx + 50, starty, 500);
                //lastdesc = currdesc;
                //currdesc = uiObject.categoryItem_last().getText();
            }

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
                endy = uiObject.category_area().getLocation().getY() + (int) (size.height * 0.15);
                starty = uiObject.category_area().getLocation().getY() + (int) (size.height * 0.85);
                driver.swipe(endx + 50, starty, endx + 50, endy, 1000);
                ind_count = uiObject.category_items().size() - 1;
                currdesc = uiObject.categoryItem_by_index(ind_count).getText();
            }
            return bflag;
        }
    }

    private Boolean scrollBarTo(String itemName){

        Point location;
        Dimension size, size2;
        Boolean bflag = false;
        Boolean beofflag = false;
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        size = driver.manage().window().getSize();
        int startx = (int) (size.width * 0.9);
        int endx = (int) (size.width * 0.1);

        location = uiObject.firsInstanceBarItem().getLocation();
        size2 = uiObject.firsInstanceBarItem().getSize();
        int ypos = (int) (location.getY() + size2.getHeight() / 2);
        while (!(uiObject.barItem_by_index(0).size() > 0)) {
            driver.swipe(endx, ypos, startx, ypos, 500);
        }

        int curind = uiObject.bar_items().size() - 1;
        int index_count = 0;

        while ((!bflag) && (!beofflag)) {
            if (uiObject.barItem(itemName).size() > 0) {
                bflag = true;
                MyLogger.log.info("Bar item found. ");
                break;
            } else if (!(uiObject.barItem_by_index(curind).size() > 0)) {
                beofflag = true;
                MyLogger.log.info("Bar item not found. ");
                break;
            }
            driver.swipe(startx, ypos, endx, ypos, 500);
            index_count = uiObject.bar_items().size();
            curind = curind + index_count;
        }
        return bflag;
    }

}