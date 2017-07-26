package api.apps.PhotoLab.store;

import core.UiObject;
import core.UiSelector;

/**
 * Created by User on 5/29/2017.
 */
public class StoreUIObjects {

    private static UiObject
            appUrl,
            photoPROTitle,
            photoFREETitle;

    public UiObject appUrl(){
        if(appUrl == null) appUrl = new UiSelector().className("android.widget.EditText").resourceId("com.android.browser:id/url").textContains("com.vicman.photolabpro").makeUiObject();
        return appUrl;
    }

    public UiObject photoPROTitle(){
        if(photoPROTitle == null) photoPROTitle = new UiSelector().className("android.widget.TextView").textContains("Photo Lab PRO").makeUiObject();
        return photoPROTitle;
    }

    public UiObject photoFREETitle(){
        if(photoFREETitle == null) photoFREETitle = new UiSelector().className("android.widget.TextView").textContains("Photo Lab Picture").makeUiObject();
        return photoFREETitle;
    }
}
