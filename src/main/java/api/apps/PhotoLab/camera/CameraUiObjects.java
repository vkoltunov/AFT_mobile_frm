package api.apps.PhotoLab.camera;

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
        if(shuter == null) shuter = new UiSelector().className("android.webkit.WebView").packageName("vsin.t16_funny_photo").makeUiObject();
        return shuter;
    }

    public UiObject close(){
        if(close == null) close = new UiSelector().className("android.widget.ImageButton").resourceId("vsin.t16_funny_photo:id/camera_fab").makeUiObject();
        return close;
    }
}
