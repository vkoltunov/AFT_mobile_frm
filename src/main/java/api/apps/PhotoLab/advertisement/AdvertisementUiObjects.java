package api.apps.PhotoLab.advertisement;

import api.interfaces.Activity;
import core.UiObject;
import core.UiSelector;

/**
 * Created by User on 6/19/2017.
 */
public class AdvertisementUiObjects {

    private static UiObject
            advertisement,
            unmute,
            close;

    public UiObject advertisement(){
        if(advertisement == null) advertisement = new UiSelector().className("android.webkit.WebView").packageName("vsin.t16_funny_photo").makeUiObject();
        return advertisement;
    }

    public UiObject unmute(){
        if(unmute == null) unmute = new UiSelector().resourceIdMatches(".*unmute.*").makeUiObject();
        return unmute;
    }
}
