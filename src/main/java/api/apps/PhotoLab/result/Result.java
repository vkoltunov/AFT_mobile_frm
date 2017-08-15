package api.apps.PhotoLab.result;

import api.android.Android;
import api.apps.PhotoLab.animate.Animate;
import api.apps.PhotoLab.art.Art;
import api.apps.PhotoLab.save.Save;
import api.apps.PhotoLab.text.Text;
import api.interfaces.Activity;
import core.MyLogger;

import java.util.concurrent.TimeUnit;

import static api.android.Android.driver;

/**
 * Created by User on 5/3/2017.
 */
public class Result implements Activity {

    public ResultUiObjects uiObject = new ResultUiObjects();

    @Override
    public Result waitToLoad(){
        try{
            MyLogger.log.info("Waiting for Result picture page");
            uiObject.menu().waitToAppear(10);
            return Android.app.photoLab.result;
        }catch (AssertionError e) {
            throw new AssertionError("Result picture page failed to load/open");
        }
    }

    public Save tapShare(){
        try{
            MyLogger.log.info("Tap to Save and Share button.");
            uiObject.share().tap();
            return Android.app.photoLab.save.waitToLoad();
        }catch (AssertionError e) {
            throw new AssertionError("Save and Share button failed to tap.");
        }
    }

    public Save tapSaveToDevice(){
            MyLogger.log.info("Tap Save to device menu option.");
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            if (uiObject.share().size() > 0) {
                uiObject.share().tap();
                return Android.app.photoLab.save.waitToLoad();
            }else throw new AssertionError("Save to device button failed to tap.");
    }

    public void ckeckAddToFavorites(){
        try{
            MyLogger.log.info("Check Add to Favorites menu option.");
            uiObject.menu().tap();
            if (!uiObject.addToFavorites().waitToAppear(3).isChecked()) uiObject.addToFavorites().tap();
        }catch (AssertionError e) {
            throw new AssertionError("Add to Favorites menu option failed to check.");
        }
    }

    public Result tapAddEffects(){
        try{
            MyLogger.log.info("Tap to Plus(additional effects) for picture button.");
            uiObject.addEffects().waitToAppear(5).tap();
            uiObject.animate().waitToAppear(5);
            return Android.app.photoLab.result;
        }catch (AssertionError e) {
            throw new AssertionError("Plus(additional effects) for picture button failed to tap.");
        }
    }

    public Text tapAddText(){
        try{
            MyLogger.log.info("Tap to Plus(additional effects text) T button.");
            uiObject.addEffects().tap();
            return Android.app.photoLab.text.waitToLoad();
        }catch (AssertionError e) {
            throw new AssertionError("Plus(additional effects text) T button failed to tap.");
        }
    }


    public Art tapArt(){
        try{
            MyLogger.log.info("Tap to Art effect for picture.");
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            if (uiObject.addArtStyle().size() > 0) uiObject.addArtStyle().tap();
            else {
                uiObject.addEffects().tap();
                uiObject.addArtStyle().waitToAppear(3).tap();
            }
            return Android.app.photoLab.art.waitToLoad();
        }catch (AssertionError e) {
            throw new AssertionError("Art effect for picture failed to tap.");
        }
    }

    public Animate tapAnimate(){
        try{
            MyLogger.log.info("Tap to Animate effect for picture.");
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            if (uiObject.animate().size() > 0) uiObject.animate().tap();
            else {
                uiObject.addEffects().tap();
                uiObject.animate().waitToAppear(3).tap();
            }
            return Android.app.photoLab.animate.waitToLoad();
        }catch (AssertionError e) {
            throw new AssertionError("Animate effect for picture failed to tap.");
        }
    }

    public Text tapText(){
        try{
            MyLogger.log.info("Tap to Text effect for picture.");
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            if (uiObject.addYourText().size() > 0) uiObject.addYourText().tap();
            else {
                uiObject.addEffects().tap();
                uiObject.addYourText().waitToAppear(3).tap();
            }
            return Android.app.photoLab.text.waitToLoad();
        }catch (AssertionError e) {
            throw new AssertionError("Text effect for picture failed to tap.");
        }
    }

}
