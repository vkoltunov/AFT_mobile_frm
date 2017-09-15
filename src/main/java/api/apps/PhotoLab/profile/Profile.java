package api.apps.PhotoLab.profile;

import api.android.Android;
import api.interfaces.Activity;
import core.MyLogger;

import java.util.concurrent.TimeUnit;

import static api.android.Android.driver;

/**
 * Created by User on 8/8/2017.
 */
public class Profile  implements Activity {

    public ProfileUiObjects uiObject = new ProfileUiObjects();

    @Override
    public Profile waitToLoad(){
        MyLogger.log.info("waiting for profile activity");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        if ((uiObject.list().size() > 0) || (uiObject.title().size()>0)) return Android.app.photoLab.profile;
        else throw new AssertionError("profile activity failed to load/open");
    }

}
