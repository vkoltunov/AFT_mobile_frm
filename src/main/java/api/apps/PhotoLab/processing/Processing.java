package api.apps.PhotoLab.processing;

import api.android.Android;
import api.apps.PhotoLab.result.Result;
import api.interfaces.Activity;
import core.MyLogger;
import core.UiObject;
import io.appium.java_client.android.AndroidKeyCode;

import java.util.concurrent.TimeUnit;

import static api.android.Android.driver;

/**
 * Created by User on 5/3/2017.
 */
public class Processing implements Activity {

    public ProcessingUiObjects uiObject = new ProcessingUiObjects();

    @Override
    public Object waitToLoad(){
        try{
            MyLogger.log.info("Waiting for Precessing activity");
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            if ((!(uiObject.processing().size() > 0)) && (!(uiObject.share().size() > 0))) {
                MyLogger.log.info("Advertisment banner!");
                Android.driver.pressKeyCode(AndroidKeyCode.BACK);
            }

            while (uiObject.processing().size() > 0){
                driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            }

            if (uiObject.goPRO().size() > 0) {
                return Android.app.photoLab.goPRO.waitToLoad();
            } else {
                driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                return Android.app.photoLab.result.waitToLoad();
            }
        }catch (AssertionError e) {
            throw new AssertionError("Precessing activity failed to load/open");
        }
    }
}
