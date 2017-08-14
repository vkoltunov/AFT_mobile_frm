package api.apps.PhotoLab.advertisement;

import api.android.Android;
import api.interfaces.Activity;
import core.MyLogger;
import core.Timer;

import java.util.concurrent.TimeUnit;

import static api.android.Android.driver;

/**
 * Created by User on 6/19/2017.
 */
public class Advertisement implements Activity {

    public AdvertisementUiObjects uiObject = new AdvertisementUiObjects();

    @Override
    public Advertisement waitToLoad(){
        MyLogger.log.info("Waiting for Advertisement Page");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        if ((uiObject.advertisement().size() > 0) || (uiObject.unmute().size() > 0) || (uiObject.video().size() > 0)) return Android.app.photoLab.advertisement;
        else throw new AssertionError("Advertisement Page failed to load/open");
    }

    public void tapClose(){
        try{
            MyLogger.log.info("Tap to Close Advertisement Page.");
            int counter = 0;
            driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
            while ((!(Android.app.photoLab.save.uiObject.saveAndShare().size() > 0)) && (counter < 7)){
                counter ++;
            }
            Android.app.photoLab.custom.pressBack();
        }catch (AssertionError e) {
            throw new AssertionError("ContactUs button failed to tap/load");
        }
    }
}
