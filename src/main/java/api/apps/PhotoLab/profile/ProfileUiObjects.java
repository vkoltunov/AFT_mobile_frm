package api.apps.PhotoLab.profile;

import core.UiObject;
import core.UiSelector;

/**
 * Created by User on 8/8/2017.
 */
public class ProfileUiObjects {

    private static UiObject
            title,
            list;

    public UiObject title(){
        if(title == null) title = new UiSelector().className("android.widget.TextView").resourceId("vsin.t16_funny_photo:id/toolbar_title").makeUiObject();
        return title;
    }

    public UiObject list(){
        if(list == null) list = new UiSelector().className("android.support.v7.widget.RecyclerView").resourceId("android:id/list").makeUiObject();
        return list;
    }
}
