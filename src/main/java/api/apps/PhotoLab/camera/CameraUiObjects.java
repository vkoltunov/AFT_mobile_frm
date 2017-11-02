package api.apps.PhotoLab.camera;

import api.android.Android;
import core.UiObject;
import core.UiSelector;

/**
 * Created by User on 5/2/2017.
 */
public class CameraUiObjects {

    private static UiObject
            shuter,
            close;

    public UiObject shuter(){
        if(shuter == null) shuter = new UiSelector().className("android.webkit.WebView").packageName(Android.app.photoLab.packageID()).makeUiObject();
        return shuter;
    }

    public UiObject close(){
        if(close == null) close = new UiSelector().className("android.widget.ImageButton").resourceId(Android.app.photoLab.packageID()+":id/camera_fab").makeUiObject();
        return close;
    }
}
