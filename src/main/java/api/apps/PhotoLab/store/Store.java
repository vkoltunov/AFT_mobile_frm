package api.apps.PhotoLab.store;

import api.android.Android;
import api.apps.PhotoLab.main.Main;
import api.interfaces.Activity;
import core.MyLogger;
import io.appium.java_client.android.AndroidKeyCode;

import java.util.concurrent.TimeUnit;

import static api.android.Android.driver;


/**
 * Created by User on 5/29/2017.
 */
public class Store implements Activity {

    public StoreUIObjects uiObject = new StoreUIObjects();

    @Override
    public Store waitToLoad(){
        try{
            MyLogger.log.info("Waiting for Store page.");
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            if (!(uiObject.photoPROTitle().size() > 0) && !(uiObject.photoFREETitle().size() > 0)) uiObject.appUrl().waitToAppear(10);
            return Android.app.photoLab.store;
        }catch (AssertionError e) {
            throw new AssertionError("Store page failed to load/open");
        }
    }

    public void closeStore(){
        try{
            MyLogger.log.info("Close store page.");
            Android.driver.pressKeyCode(AndroidKeyCode.BACK);
        }catch (AssertionError e) {
            throw new AssertionError("Store page failed to close");
        }
    }
}
