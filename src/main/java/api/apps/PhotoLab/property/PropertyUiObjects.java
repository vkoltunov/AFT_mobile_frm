package api.apps.PhotoLab.property;

import core.UiObject;
import core.UiSelector;

/**
 * Created by User on 5/2/2017.
 */
public class PropertyUiObjects {

    private static UiObject
            done,
            back,
            hints,
            crop1,
            crop2,
            crop3,
            rotate;

    public UiObject done(){
        if(done == null) done = new UiSelector().className("android.widget.ImageButton").resourceId("vsin.t16_funny_photo:id/next_btn").makeUiObject();
        return done;
    }

    public UiObject back(){
        if(back == null) back = new UiSelector().className("android.widget.ImageButton").resourceId("android:id/button1").makeUiObject();
        return back;
    }

    public UiObject hints(){
        if(hints == null) hints = new UiSelector().className("android.widget.CheckBox").resourceId("vsin.t16_funny_photo:id/email_notifications_checkbox").makeUiObject();
        return hints;
    }

    public UiObject crop1(){
        if(crop1 == null) crop1 = new UiSelector().className("android.widget.RadioButton").resourceId("vsin.t16_funny_photo:id/asp1").makeUiObject();
        return crop1;
    }

    public UiObject crop2(){
        if(crop2 == null) crop2 = new UiSelector().className("android.widget.RadioButton").resourceId("vsin.t16_funny_photo:id/asp2").makeUiObject();
        return crop2;
    }

    public UiObject crop3(){
        if(crop3 == null) crop3 = new UiSelector().className("android.widget.RadioButton").resourceId("vsin.t16_funny_photo:id/asp3").makeUiObject();
        return crop3;
    }

    public UiObject rotate(){
        if(rotate == null) rotate = new UiSelector().className("android.widget.ImageButton").resourceId("vsin.t16_funny_photo:id/rotateRight").makeUiObject();
        return rotate;
    }
}
