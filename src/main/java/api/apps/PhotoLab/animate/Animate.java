package api.apps.PhotoLab.animate;

import api.android.Android;
import api.apps.PhotoLab.result.Result;
import api.apps.PhotoLab.save.Save;
import api.interfaces.Activity;
import core.MyLogger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

import java.util.concurrent.TimeUnit;

import static api.android.Android.driver;

/**
 * Created by User on 5/3/2017.
 */
public class Animate implements Activity {

    public AnimateUiObjects uiObject = new AnimateUiObjects();

    @Override
    public Animate waitToLoad(){
        try{
            MyLogger.log.info("Waiting for Text page");
            uiObject.animate().waitToAppear(5);
            return Android.app.photoLab.animate;
        }catch (AssertionError e) {
            throw new AssertionError("Text page failed to load/open");
        }
    }

    public Result tapDone(){
        try{
            MyLogger.log.info("Tap Done button for Animate page.");
            uiObject.done().waitToAppear(20).tap();
            return Android.app.photoLab.result.waitToLoad();
        }catch (AssertionError e) {
            throw new AssertionError("Done button for Animate page failed to tap.");
        }
    }

    public void selectAnimate (String effectName){
        MyLogger.log.info("Select animate effect '"+effectName+"'.");
        if (scrollTo(effectName)) {
            uiObject.done().waitToAppear(10);
        }
        else throw new AssertionError("Animate effect '"+effectName+"' not found.");
    }

    private Boolean scrollTo(String itemName){

        Point location;
        Dimension size, size2;
        Boolean bflag = false;
        Boolean beofflag = false;
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        size = uiObject.animate_List().getSize();
        int startx = (int) (size.width * 0.9);
        int endx = (int) (size.width * 0.1);

        location = uiObject.animate_List().getLocation();
        size2 = uiObject.animate_List().getSize();
        int ypos = (int) (location.getY() + size2.getHeight() / 2);
        while (!(uiObject.animate_None().size() > 0)) {
            driver.swipe(endx, ypos, startx, ypos, 500);
        }
        String curTitle;
        String lastList = "";
        int index_count = uiObject.animate_items().size();
        String curList = uiObject.animateItem_by_index(index_count-1).getText();

        while ((!bflag) && (!beofflag)) {
            for (int i = 0; i < index_count; i++) {
                uiObject.animateItem_by_index(i).tap();
                uiObject.animate_Result().waitToAppear(10);
                curTitle = uiObject.toolbar_Title().getText();
                if (curTitle.contains(itemName)) {
                    bflag = true;
                    MyLogger.log.info("Art by name found. ");
                    break;
                } else if (lastList.contains(curList)) {
                    beofflag = true;
                    MyLogger.log.info("Art item not found. ");
                    break;
                }
            }
            lastList = curList;
            driver.swipe(startx, ypos, endx, ypos, 1500);
            index_count = uiObject.animate_items().size();
            curList = uiObject.animateItem_by_index(index_count-1).getText();
        }
        return bflag;
    }

}
