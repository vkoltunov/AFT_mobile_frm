package api.apps.PhotoLab.profile;

import api.android.Android;
import api.interfaces.Activity;
import core.MyLogger;

/**
 * Created by User on 8/8/2017.
 */
public class Profile  implements Activity {

    public ProfileUiObjects uiObject = new ProfileUiObjects();

    @Override
    public Profile waitToLoad(){
        try{
            MyLogger.log.info("waiting for profile activity");
            uiObject.list().waitToAppear(10);
            return Android.app.photoLab.profile;
        }catch (AssertionError e) {
            throw new AssertionError("profile activity failed to load/open");
        }
    }


}
