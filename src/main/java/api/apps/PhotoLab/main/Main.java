package api.apps.PhotoLab.main;

import api.android.Android;
import api.apps.PhotoLab.categories.Categories;
import api.apps.PhotoLab.menu.Menu;
import api.apps.PhotoLab.pictures.Pictures;
import api.apps.PhotoLab.profile.Profile;
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
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            if (!(uiObject.menu().size() > 0)){
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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Boolean bFlag = false;
        uiObject.bottomNavigation().waitToAppear(10);
        if (categoryBarName.contains("Feed") || categoryBarName.contains("Inspiration") || categoryBarName.contains("Explore") || categoryBarName.contains("Combos") || categoryBarName.contains("Community") ){
            if (!bFlag) {
                bFlag=scrollBarTo("Feed");
                categoryBarName = "Feed";
            }
            if (!bFlag) {
                bFlag=scrollBarTo("Inspiration");
                categoryBarName = "Inspiration";
            }
            if (!bFlag) {
                bFlag=scrollBarTo("Explore");
                categoryBarName = "Explore";
            }
            if (!bFlag) {
                bFlag=scrollBarTo("Combos");
                categoryBarName = "Combos";
            }
            if (!bFlag) {
                bFlag=scrollBarTo("Community");
                categoryBarName = "Community";
            }
        } else bFlag= scrollBarTo(categoryBarName);

        if (bFlag){
            uiObject.barItem(categoryBarName).tap();
            return Android.app.photoLab.main.waitToLoad();
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
            //MyLogger.log.info("Hello!");
            uiObject.categoryItem(categoryName).tap();
            return Android.app.photoLab.categories.categoryTitle_check(categoryName);
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

    public Boolean existItem(String itemName, Boolean flag){
        MyLogger.log.info("Check exist item category or effect named '"+itemName+"'.");
        if (scrollToCategory(itemName) == flag) return true;
        else {
            MyLogger.log.info("Check Irem '"+itemName+"' exists failed.");
            return false;
        }
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

    public Profile openProfile(){
        try{
            MyLogger.log.info("Open Inspiration user profile.");
            uiObject.barItem("My profile").tap();
            return Android.app.photoLab.profile.waitToLoad();
        }catch (AssertionError e) {
            throw new AssertionError("Inspiration profile page not found.");
        }
    }

    private Boolean scrollToCategory(String categoryName){
        Dimension size;
        Boolean bflag = false;
        int endx = uiObject.category_area().getLocation().getX();
        //MyLogger.log.info("Hello AREA !");
        int ind_count = uiObject.category_items().size() - 1;
        //MyLogger.log.info("Hello ITEMS !");

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

    private Boolean scrollBarTo2(String itemName){

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
