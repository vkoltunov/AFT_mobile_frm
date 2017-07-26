package api.apps.PhotoLab.categories;

import api.android.Android;
import api.apps.PhotoLab.pictures.Pictures;
import api.interfaces.Activity;
import core.MyLogger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

import java.util.concurrent.TimeUnit;

import static api.android.Android.driver;

/**
 * Created by User on 4/4/2017.
 */
public class Categories implements Activity {

    public CategoriesUiObjects uiObject = new CategoriesUiObjects();

    @Override
    public Categories waitToLoad() {
        try{
            MyLogger.log.info("Waiting for Categories frame");
            uiObject.effects().waitToAppear(5);
            return Android.app.photoLab.categories;
        }catch (AssertionError e) {
            throw new AssertionError("Categories frame failed to load/open");
        }
    }

    public Boolean effectExists(String effectName){
        MyLogger.log.info("Check is effect '"+effectName+"' exists");
        if (scrollToEffect(effectName)) return true;
        else {
            MyLogger.log.info("Check effect '"+effectName+"' exists failed.");
            return false;
        }
    }

    public Pictures selectEffect(String effectName){
        MyLogger.log.info("Select effect '"+effectName+"'.");
        Android.app.photoLab.favoriteFlag = false;
        if (scrollToEffect(effectName)) {
            uiObject.effectItem(effectName).tap();
            return Android.app.photoLab.pictures.waitToLoad();
        }
        else throw new AssertionError("Effect '"+effectName+"' not found.");
    }

    private Boolean scrollToEffect(String effectName){
        Dimension size;
        Boolean bflag = false;


        int endx = uiObject.category_area().getLocation().getX();
        size = uiObject.category_area().getSize();
        int endy = uiObject.category_area().getLocation().getY() + (int) (size.height * 0.2);
        int starty = uiObject.category_area().getLocation().getY() + (int) (size.height * 0.85);

        int ind_count = uiObject.effect_items().size() - 1;
        if (ind_count < 0) return false;
        else {
            String lastdesc = "";
            String currdesc = uiObject.effectItem_last().getText();
            Boolean beofflag = false;

            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

            while ((!bflag) && (!beofflag)) {
                if (uiObject.effectItem(effectName).size() > 0) {
                    bflag = true;
                    MyLogger.log.info("Category item found. ");
                    break;
                } else if (lastdesc.contains(currdesc)) {
                    beofflag = true;
                    MyLogger.log.info("Category item is not found.");
                    break;
                }
                lastdesc = currdesc;
                driver.swipe(endx + 50, starty, endx + 50, endy, 2200);
                currdesc = uiObject.effectItem_last().getText();
            }
            return bflag;
        }
    }

}
