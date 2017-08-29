package api.apps.PhotoLab.custom;

import core.UiObject;
import core.UiSelector;

/**
 * Created by User on 8/21/2017.
 */
public class CustomUiObjects {

    private static UiObject title;

    public UiObject title(){
        title = new UiSelector().className("android.widget.TextView").resourceId("vsin.t16_funny_photo:id/toolbar_title").makeUiObject();
        return title;
    }
}
