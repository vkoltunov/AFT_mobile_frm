package api.apps.PhotoLab.logIn;

import api.android.Android;
import api.apps.PhotoLab.facebook.Facebook;
import api.apps.PhotoLab.save.Save;
import api.interfaces.Activity;
import core.MyLogger;
import core.UiObject;

import java.util.concurrent.TimeUnit;

import static api.android.Android.driver;

/**
 * Created by User on 6/8/2017.
 */
public class LogIn implements Activity {

    public LogInUiObjects uiObject = new LogInUiObjects();

    @Override
    public LogIn waitToLoad(){
        try{
            MyLogger.log.info("Waiting for Log In page.");
            uiObject.logInTitle().waitToAppear(10);
            return Android.app.photoLab.logIn;
        }catch (AssertionError e) {
            throw new AssertionError("Log In page failed to load/open");
        }
    }

    public void tapLogInFB(String user, String password){
        try{
            MyLogger.log.info("Tap to Log In FB button.");
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            if (uiObject.logInWithFB().size() > 0){
                uiObject.logInWithFB().tap();
                if (uiObject.fb_logIn_btn().size()>0) Android.app.photoLab.facebook.liginFB(user, password);
            }
        }catch (AssertionError e) {
            throw new AssertionError("Log In FB button failed to tap/load");
        }
    }

    public Save tapClose(){
        try{
            MyLogger.log.info("Close Log In page.");
            uiObject.close().tap();
            return Android.app.photoLab.save.waitToLoad();
        }catch (AssertionError e) {
            throw new AssertionError("Log In failed to close");
        }
    }

}
