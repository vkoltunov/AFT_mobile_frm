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
            close,
            video,
            content;

    public UiObject advertisement(){
        if(advertisement == null) advertisement = new UiSelector().className("android.webkit.WebView").packageName("vsin.t16_funny_photo").makeUiObject();
        return advertisement;
    }

    public UiObject unmute(){
        if(unmute == null) unmute = new UiSelector().resourceIdMatches(".*unmute.*").makeUiObject();
        return unmute;
    }

    public UiObject video(){
        if(video == null) video = new UiSelector().classNameMatches(".*Video.*").makeUiObject();
        return video;
    }

    public UiObject content(){
        if(content == null) content = new UiSelector().resourceIdMatches(".*content.*").makeUiObject();
        return content;
    }
}
