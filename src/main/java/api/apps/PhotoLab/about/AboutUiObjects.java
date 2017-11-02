package api.apps.PhotoLab.about;

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
            contactUs,
            follow_instagram,
            follow_twitter,
            follow_facebook;

    public UiObject about(){
        if(about == null) about = new UiSelector().className("android.widget.TextView").text("About").makeUiObject();
        return about;
    }

    public UiObject version(){
        if(version == null) version = new UiSelector().className("android.widget.TextView").resourceId(Android.app.photoLab.packageID()+":id/version").makeUiObject();
        return version;
    }

    public UiObject contactUs(){
        if(contactUs == null) contactUs = new UiSelector().className("android.widget.Button").resourceId(Android.app.photoLab.packageID()+":id/leave_feedback").makeUiObject();
        return contactUs;
    }

    public UiObject follow_instagram(){
        if(follow_instagram == null) follow_instagram = new UiSelector().className("android.widget.ImageButton").resourceId(Android.app.photoLab.packageID()+":id/follow_instagram").makeUiObject();
        return follow_instagram;
    }

    public UiObject follow_twitter(){
        if(follow_twitter == null) follow_twitter = new UiSelector().className("android.widget.ImageButton").resourceId(Android.app.photoLab.packageID()+":id/follow_twitter").makeUiObject();
        return follow_twitter;
    }

    public UiObject follow_facebook(){
        if(follow_facebook == null) follow_facebook = new UiSelector().className("android.widget.ImageButton").resourceId(Android.app.photoLab.packageID()+":id/follow_facebook").makeUiObject();
        return follow_facebook;
    }

}
