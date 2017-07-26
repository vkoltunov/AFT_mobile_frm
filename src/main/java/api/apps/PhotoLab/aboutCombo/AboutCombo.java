package api.apps.PhotoLab.aboutCombo;

import api.android.Android;
import api.interfaces.Activity;
import core.MyLogger;

import java.util.concurrent.TimeUnit;

import static api.android.Android.driver;

/**
 * Created by User on 6/26/2017.
 */
public class AboutCombo implements Activity {

    public AboutComboUiObjects uiObject = new AboutComboUiObjects();

    @Override
    public AboutCombo waitToLoad(){
        MyLogger.log.info("Waiting for About This Combo Page");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        if (uiObject.title().size() > 0){
            return Android.app.photoLab.aboutCombo;
        }
        else throw new AssertionError("About This Combo Page failed to load/open");
    }

    public void checkPage() {
        try{
            MyLogger.log.info("Check All Page elements for exists.");
            uiObject.title().waitToAppear(3);
            uiObject.comboBy().waitToAppear(2);
            uiObject.close().waitToAppear(2);
        }catch (AssertionError e) {
            throw new AssertionError("All Page elements for exists check failed.");
        }
    }

    public void tapClose(){
        try{
            MyLogger.log.info("Tap Close button.");
            uiObject.close().tap();
        }catch (AssertionError e) {
            throw new AssertionError("Close button failed to tap/load");
        }
    }
}
