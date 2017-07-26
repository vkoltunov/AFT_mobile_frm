package api.apps.PhotoLab.art;

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
public class Art implements Activity {

    public ArtUiObjects uiObject = new ArtUiObjects();

    @Override
    public Art waitToLoad(){
        try{
            MyLogger.log.info("Waiting for Add art Style page");
            uiObject.addArtStyle().waitToAppear(5);
            return Android.app.photoLab.art;
        }catch (AssertionError e) {
            throw new AssertionError("Add art Style page failed to load/open");
        }
    }

    public Result tapDone(){
        try{
            MyLogger.log.info("Tap Done button for Add art Style page.");
            uiObject.done().tap();
            return Android.app.photoLab.result.waitToLoad();
        }catch (AssertionError e) {
            throw new AssertionError("Done button for Add art Style page failed to tap.");
        }
    }

    public void selectArt (Integer effectNumber){
        MyLogger.log.info("Select art effect '"+effectNumber+"'.");
        if (scrollTo(effectNumber)){
            uiObject.art_byIndex(effectNumber).tap();
            uiObject.done().waitToAppear(10);
        }
        else throw new AssertionError("Art effect '"+effectNumber+"' not found.");
    }

    private Boolean scrollTo(Integer itemNumber){

        //Point location;
        //Dimension size, size2;
        //Boolean bflag = false;
        //Boolean beofflag = false;
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //size = driver.manage().window().getSize();
        //int startx = (int) (size.width * 0.9);
        //int endx = (int) (size.width * 0.1);

        //location = uiObject.art_firsInstance().getLocation();
        //size2 = uiObject.art_firsInstance().getSize();
        //int ypos = (int) (location.getY() + size2.getHeight() / 2);
        //while (!(uiObject.art_byIndex(0).size() > 0)) {
        //    driver.swipe(endx, ypos, startx, ypos, 500);
        //}

        //int index_count = uiObject.artCounts().size() - 1;

        int index_count = uiObject.artCounts().size() - 1;

        if (uiObject.art_byIndex(itemNumber).size() > 0) return true;
        else return false;

        //String curind = "";

        //while ((!bflag) && (!beofflag)) {
        //    if (uiObject.artItem(itemName).size() > 0) {
        //        bflag = true;
        //        MyLogger.log.info("Art item found.");
        //        break;
        //    } else if (uiObject.art_byIndex(index_count).getText().contains(curind)) {
        //        beofflag = true;
        //        MyLogger.log.info("Art item not found. ");
        //        break;
        //    }
        //    curind = uiObject.art_byIndex(index_count).getText();
        //    driver.swipe(startx, ypos, endx, ypos, 500);
        //}
        //return bflag;
    }

}
