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

    public void selectAnimate (Integer effectNumber){
        MyLogger.log.info("Select animate effect '"+effectNumber+"'.");
        if (scrollTo(effectNumber)) {
            uiObject.animate_byIndex(effectNumber).tap();
            uiObject.done().waitToAppear(10);
        }
        else throw new AssertionError("Animate effect '"+effectNumber+"' not found.");
    }

    private Boolean scrollTo(Integer itemNumber){

        Point location;
        Dimension size, size2;
        Boolean bflag = false;
        Boolean beofflag = false;
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //size = driver.manage().window().getSize();
        //int startx = (int) (size.width * 0.9);
        //int endx = (int) (size.width * 0.1);

        //location = uiObject.animate_byIndex(0).getLocation();
        //size2 = uiObject.animate_byIndex(0).getSize();
        //int ypos = (int) (location.getY() + size2.getHeight() / 2);
        int index_count = uiObject.animateCounts().size() - 1;

        if (uiObject.animate_byIndex(itemNumber).size() > 0) return true;
        else return false;

        //String curind = "";

        //while ((!bflag) && (!beofflag)) {
        //    if (uiObject.animateItem(itemName).size() > 0) {
        //        bflag = true;
        //        MyLogger.log.info("Animate item found.");
        //        break;
        //    } else if (uiObject.animate_byIndex(index_count).getText().contains(curind)) {
        //        beofflag = true;
        //        MyLogger.log.info("Animate item not found. ");
        //        break;
        //    }
        //    curind = uiObject.animate_byIndex(index_count).getText();
        //    driver.swipe(startx, ypos, endx, ypos, 1000);
        //}
        //return bflag;
    }

}
