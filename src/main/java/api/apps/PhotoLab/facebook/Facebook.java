package api.apps.PhotoLab.facebook;

import api.android.Android;
import api.apps.PhotoLab.logIn.LogIn;
import api.interfaces.Activity;
import core.MyLogger;

import java.util.concurrent.TimeUnit;

import static api.android.Android.driver;

/**
 * Created by User on 6/8/2017.
 */
public class Facebook implements Activity {

    public FacebookUiObjects uiObject = new FacebookUiObjects();

    @Override
    public Facebook waitToLoad(){
        try{
            MyLogger.log.info("Waiting for Facebook login page.");
            uiObject.logIn_btn().waitToAppear(15);
            return Android.app.photoLab.facebook;
        }catch (AssertionError e) {
            throw new AssertionError("Facebook login page failed to load/open");
        }
    }

    public void liginFB(String user, String password){
        try{
            MyLogger.log.info("LogIn in Facebook. User: '"+user+"', Password: '"+password+"'");
            waitToLoad();
            uiObject.logIn().clearText();
            uiObject.logIn().typeText(user);
            uiObject.password().typeText(password);
            uiObject.logIn_btn().tap();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            if ((uiObject.logIn_btn().size()>0)) MyLogger.log.error("Facebook LogIn failed. Check your user credentials.");
        }catch (AssertionError e) {
            throw new AssertionError("Facebook LogIn failed.");
        }
    }
}
