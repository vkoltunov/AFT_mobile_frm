package api.apps.Xmas.about;

import api.android.Android;
import core.UiObject;
import core.UiSelector;

/**
 * Created by User on 4/28/2017.
 */
public class AboutUiObjects {

    private static UiObject
            about,
            version,
            contactUs;

    public UiObject about(){
        if(about == null) about = new UiSelector().className("android.widget.TextView").text("About").makeUiObject();
        return about;
    }

    public UiObject version(){
        if(version == null) version = new UiSelector().className("android.widget.TextView").resourceId(Android.app.xmas.packageID()+":id/version").makeUiObject();
        return version;
    }

}
