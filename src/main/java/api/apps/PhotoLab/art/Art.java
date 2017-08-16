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

    public void selectArt (String effectName){
        MyLogger.log.info("Select art effect '"+effectName+"'.");
        if (scrollTo(effectName)){
            uiObject.done().waitToAppear(10);
        }
        else throw new AssertionError("Art effect '"+effectName+"' not found.");
    }

    private Boolean scrollTo(String itemName){

        Point location;
        Dimension size, size2;
        Boolean bflag = false;
        Boolean beofflag = false;
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        size = uiObject.art_List().getSize();
        int startx = (int) (size.width * 0.9);
        int endx = (int) (size.width * 0.1);

        location = uiObject.art_List().getLocation();
        size2 = uiObject.art_List().getSize();
        int ypos = (int) (location.getY() + size2.getHeight() / 2);
        while (!(uiObject.art_None().size() > 0)) {
            driver.swipe(endx, ypos, startx, ypos, 500);
        }
        String curTitle;
        String lastList = "";
        int index_count = uiObject.art_items().size();
        String curList = uiObject.artItem_by_index(index_count-1).getText();

        while ((!bflag) && (!beofflag)) {
            for (int i = 0; i < index_count; i++) {
                uiObject.artItem_by_index(i).tap();
                uiObject.art_Result().waitToAppear(10);
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
            index_count = uiObject.art_items().size();
            curList = uiObject.artItem_by_index(index_count-1).getText();
        }
        return bflag;
    }

}
