package api.apps.PhotoLab.save;

import api.android.Android;
import api.apps.PhotoLab.dialogs.Dialogs;
import api.interfaces.Activity;
import core.MyLogger;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.springframework.objenesis.instantiator.android.AndroidSerializationInstantiator;

import java.util.concurrent.TimeUnit;

import static api.android.Android.driver;

/**
 * Created by User on 5/4/2017.
 */
public class Save implements Activity {

    public SaveUiObjects uiObject = new SaveUiObjects();

    @Override
    public Save waitToLoad(){
        try{
            MyLogger.log.info("Waiting for Save & Share page");
            uiObject.saveAndShare().waitToAppear(5);
            return Android.app.photoLab.save;
        }catch (AssertionError e) {
            throw new AssertionError("Save & Share page failed to load/open");
        }
    }

    public void tapSaveToDevice(){
        try{
            MyLogger.log.info("Tap to Save to device option.");
            uiObject.menu().tap();
            uiObject.saveToDevice().waitToAppear(3).tap();
        }catch (AssertionError e) {
            throw new AssertionError("Save to device option failed to tap.");
        }
    }

    public void selectTarget(String target){
        try{
            MyLogger.log.info("Tap to save option target '"+target+"'.");
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            if (uiObject.tutorialShare().size() > 0) uiObject.tutorialShare().tap();
            uiObject.save_target(target).waitToAppear(5).tap();
        }catch (AssertionError e) {
            throw new AssertionError("Tap to save option target '"+target+"' failed.");
        }
    }

    public void selectDownload(){
        try{
            MyLogger.log.info("Tap to save option Download.");
            uiObject.download().tap();
        }catch (AssertionError e) {
            throw new AssertionError("Save option Download failed to tap.");
        }
    }

    public Dialogs tapWatemark() {
        try{
            MyLogger.log.info("Tap to watemark #photolab.");

            Point location;
            Dimension size, size2;
            Boolean bflag = false;
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

            size = driver.manage().window().getSize();
            int startx = size.width;
            int xpos = startx;
            int xpos2 = xpos;

            location = uiObject.collage().getLocation();
            size2 = uiObject.collage().getSize();
            int ypos = (int) (location.getY() + size2.getHeight() * 0.95);
            int ypos2 = (int) ypos/2;
            while ((ypos > ypos2) && (!bflag)){
                while ((!bflag) && (xpos > startx/2)) {
                    xpos = (int)(xpos * 0.9);
                    TouchAction touchAction=new TouchAction(driver);
                    touchAction.tap(xpos, ypos).perform();
                    if (Android.app.photoLab.dialogs.uiObject.dialog().size() > 0) bflag = true;
                }
                xpos = xpos2;
                ypos = (int)(ypos * 0.95);
            }
            if (xpos > startx/2) return Android.app.photoLab.dialogs.waitToLoad();
            else throw new AssertionError("Watemark #photolab not found.");
        }catch (AssertionError e) {
            throw new AssertionError("Watemark #photolab failed to tap.");
        }
    }

    public Save checkSaveShareTutorial(){
        try{
            MyLogger.log.info("Check Save and Share Tutorial.");
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            Boolean bflag = false;
            if (uiObject.tutorialLogo().size() > 0){
                MyLogger.log.info("Text 'Tap the logo to remove it' exist.");
                bflag = true;
            }
            if (uiObject.tutorialShare().size() > 0){
                MyLogger.log.info("Text 'Share your masterpiece with other Photo Lab users' exist.");
                bflag = true;
            }
            if (bflag) uiObject.tutorialShare().tap();
            return Android.app.photoLab.save.waitToLoad();
        }catch (AssertionError e) {
            throw new AssertionError("Save and Share Tutorial failed to check.");
        }
    }

}
