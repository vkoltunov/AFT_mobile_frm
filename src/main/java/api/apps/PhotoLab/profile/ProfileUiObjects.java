package api.apps.PhotoLab.profile;

import api.android.Android;
import core.UiObject;
import core.UiSelector;

/**
 * Created by User on 8/8/2017.
 */
public class ProfileUiObjects {

    private static UiObject
            title,
            list,
            share;

    public UiObject title(){
        if(title == null) title = new UiSelector().className("android.widget.TextView").resourceId(Android.app.photoLab.packageID()+":id/toolbar_title").text("My profile").makeUiObject();
        return title;
    }

    public UiObject share(){
        if(share == null) share = new UiSelector().className("android.widget.TextView").resourceId(Android.app.photoLab.packageID()+":id/menu_share").makeUiObject();
        return share;
    }

    public UiObject list(){
        if(list == null) list = new UiSelector().className("android.support.v7.widget.RecyclerView").resourceId("android:id/list").makeUiObject();
        return list;
    }

}
