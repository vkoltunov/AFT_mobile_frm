package api.apps.PhotoLab.logIn;

import api.android.Android;
import core.UiObject;
import core.UiSelector;

/**
 * Created by User on 6/8/2017.
 */
public class LogInUiObjects {

    private static UiObject
            logInTitle,
            logInWithFB,
            close,
            pageTitle,
            pageMessage,
            fb_logIn_btn;

    public UiObject logInTitle(){
        if(logInTitle == null) logInTitle = new UiSelector().className("android.widget.TextView").resourceId(Android.app.photoLab.packageID()+":id/toolbar_title").text("Log in").makeUiObject();
        return logInTitle;
    }

    public UiObject logInWithFB(){
        if(logInWithFB == null) logInWithFB = new UiSelector().className("android.widget.Button").resourceId(Android.app.photoLab.packageID()+":id/btnLoginWithFb").makeUiObject();
        return logInWithFB;
    }

    public UiObject close(){
        if(close == null) close = new UiSelector().className("android.widget.ImageButton").resourceId("android:id/button1").makeUiObject();
        return close;
    }

    public UiObject pageTitle(){
        if(pageTitle == null) pageTitle = new UiSelector().className("android.widget.TextView").text("Yay! Time to join!").makeUiObject();
        return pageTitle;
    }

    public UiObject pageMessage(){
        if(pageMessage == null) pageMessage = new UiSelector().className("android.widget.TextView").resourceId(Android.app.photoLab.packageID()+":id/login_text_footer").makeUiObject();
        return pageMessage;
    }

    public UiObject fb_logIn_btn(){
        if(fb_logIn_btn == null) fb_logIn_btn = new UiSelector().className("android.widget.Button").text("LOG IN").makeUiObject();
        return fb_logIn_btn;
    }
}
