package api.apps.PhotoLab.dialogs;

import core.UiObject;
import core.UiSelector;

/**
 * Created by User on 6/2/2017.
 */
public class DialogsUiObjects {

    private static UiObject
            dialog,
            dialogTitle,
            skip,
            rate,
            dialogMessage,
            notNow,
            goPRO,
            no,
            yes,
            ok,
            showInFeed;

    public UiObject dialog(){
        if(dialog == null) dialog = new UiSelector().className("android.support.v7.widget.LinearLayoutCompat").resourceId("vsin.t16_funny_photo:id/parentPanel").makeUiObject();
        return dialog;
    }

    public UiObject dialogTitle(){
        if(dialogTitle == null) dialogTitle = new UiSelector().className("android.widget.TextView").resourceId("vsin.t16_funny_photo:id/alertTitle").makeUiObject();
        return dialogTitle;
    }

    public UiObject dialogMessage(){
        if(dialogMessage == null) dialogMessage = new UiSelector().className("android.widget.TextView").resourceId("android:id/message").makeUiObject();
        return dialogMessage;
    }

    public UiObject skip(){
        if(skip == null) skip = new UiSelector().className("android.widget.Button").text("Skip").makeUiObject();
        return skip;
    }

    public UiObject rate(){
        if(rate == null) rate = new UiSelector().className("android.widget.Button").text("Rate").makeUiObject();
        return rate;
    }

    public UiObject notNow(){
        if(notNow == null) notNow = new UiSelector().className("android.widget.Button").text("Not now").makeUiObject();
        return notNow;
    }

    public UiObject goPRO(){
        if(goPRO == null) goPRO = new UiSelector().className("android.widget.Button").text("Go PRO").makeUiObject();
        return goPRO;
    }

    public UiObject no(){
        if(no == null) no = new UiSelector().className("android.widget.Button").text("No").makeUiObject();
        return no;
    }

    public UiObject yes(){
        if(yes == null) yes = new UiSelector().className("android.widget.Button").text("Yes").makeUiObject();
        return yes;
    }

    public UiObject ok(){
        if(ok == null) ok = new UiSelector().className("android.widget.Button").text("OK").makeUiObject();
        return ok;
    }

    public UiObject showInFeed(){
        if(showInFeed == null) showInFeed = new UiSelector().className("android.widget.Button").text("Show in feed").makeUiObject();
        return showInFeed;
    }
}
