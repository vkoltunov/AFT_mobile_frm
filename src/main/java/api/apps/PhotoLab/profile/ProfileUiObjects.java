package api.apps.PhotoLab.profile;

import core.UiObject;
import core.UiSelector;

/**
 * Created by User on 8/8/2017.
 */
public class ProfileUiObjects {

    private static UiObject
            title,
            moreOptions;

    public UiObject title(){
        if(title == null) title = new UiSelector().className("android.widget.TextView").resourceId("vsin.t16_funny_photo:id/toolbar_title").makeUiObject();
        return title;
    }

    public UiObject moreOptions(){
        if(moreOptions == null) moreOptions = new UiSelector().className("android.widget.TextView").resourceId("vsin.t16_funny_photo:id/more").makeUiObject();
        return moreOptions;
    }
}
