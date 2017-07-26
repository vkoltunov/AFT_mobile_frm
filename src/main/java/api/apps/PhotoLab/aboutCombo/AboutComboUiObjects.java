package api.apps.PhotoLab.aboutCombo;

import core.UiObject;
import core.UiSelector;

/**
 * Created by User on 6/26/2017.
 */
public class AboutComboUiObjects {

    private static UiObject
            title,
            comboBy,
            close;

    public UiObject title(){
        if(title == null) title = new UiSelector().className("android.widget.TextView").resourceId("vsin.t16_funny_photo:id/toolbar_title").textContains("About this combo").makeUiObject();
        return title;
    }

    public UiObject comboBy(){
        if(comboBy == null) comboBy = new UiSelector().className("android.widget.TextView").resourceId("vsin.t16_funny_photo:id/text_user_name").textContains("Combo by").makeUiObject();
        return comboBy;
    }

    public UiObject close(){
        if(close == null) close = new UiSelector().className("android.widget.ImageButton").resourceId("android:id/button1").makeUiObject();
        return close;
    }
}
