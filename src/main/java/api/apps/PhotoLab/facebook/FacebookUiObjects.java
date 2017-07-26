package api.apps.PhotoLab.facebook;

import core.UiObject;
import core.UiSelector;

/**
 * Created by User on 6/8/2017.
 */
public class FacebookUiObjects {
    private static UiObject
            logIn_btn,
            logIn,
            password;

    public UiObject logIn_btn(){
        if(logIn_btn == null) logIn_btn = new UiSelector().className("android.widget.Button").textContains("LOG IN").makeUiObject();
        return logIn_btn;
    }

    public UiObject logIn(){
        if(logIn == null) logIn = new UiSelector().className("android.widget.EditText").resourceIdMatches(".*login_username").makeUiObject();
        return logIn;
    }

    public UiObject password(){
        if(password == null) password = new UiSelector().className("android.widget.EditText").resourceIdMatches(".*login_password").makeUiObject();
        return password;
    }
}
