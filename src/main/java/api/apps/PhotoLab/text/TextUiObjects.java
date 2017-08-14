package api.apps.PhotoLab.text;

import core.UiObject;
import core.UiSelector;

/**
 * Created by User on 5/3/2017.
 */
public class TextUiObjects {

    private static UiObject
            addYourText,
            done,
            next,
            text,
            edit,
            font,
            style,
            color,
            opacity,
            by_index,
            editText;

    public UiObject text(){
        if(text == null) text = new UiSelector().className("android.widget.TextView").textContains("ext").makeUiObject();
        return text;
    }

    public UiObject editText(){
        if(editText == null) editText = new UiSelector().className("android.widget.TextView").textContains("Edit text").makeUiObject();
        return editText;
    }

    public UiObject next(){
        if(next == null) next = new UiSelector().className("android.widget.TextView").resourceId("vsin.t16_funny_photo:id/button_apply").makeUiObject();
        return next;
    }

    public UiObject done(){
        if(done == null) done = new UiSelector().className("android.widget.ImageButton").resourceId("vsin.t16_funny_photo:id/add").description("Apply").makeUiObject();
        return done;
    }

    public UiObject addYourText(){
        if(addYourText == null) addYourText = new UiSelector().className("android.widget.EditText").resourceId("android:id/text1").makeUiObject();
        return addYourText;
    }

    public UiObject edit(){
        if(edit == null) edit = new UiSelector().className("android.widget.ImageButton").resourceId("android:id/edit").makeUiObject();
        return edit;
    }

    public UiObject font(){
        if(font == null) font = new UiSelector().className("android.widget.ImageButton").resourceId("vsin.t16_funny_photo:id/font").makeUiObject();
        return font;
    }

    public UiObject style(){
        if(style == null) style = new UiSelector().className("android.widget.ImageButton").resourceId("vsin.t16_funny_photo:id/style").makeUiObject();
        return style;
    }

    public UiObject color(){
        if(color == null) color = new UiSelector().className("android.widget.ImageButton").resourceId("vsin.t16_funny_photo:id/color").makeUiObject();
        return color;
    }

    public UiObject opacity(){
        if(opacity == null) opacity = new UiSelector().className("android.widget.ImageButton").resourceId("vsin.t16_funny_photo:id/button_opacity").makeUiObject();
        return opacity;
    }

    public UiObject by_index(int index){
        by_index = new UiSelector().xPath("//android.support.v7.widget.RecyclerView//android.widget.FrameLayout[@index='"+index+"']").makeUiObject();
        return by_index;
    }
}
