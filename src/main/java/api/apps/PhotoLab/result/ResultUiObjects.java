package api.apps.PhotoLab.result;

import api.android.Android;
import core.UiObject;
import core.UiSelector;

/**
 * Created by User on 5/3/2017.
 */
public class ResultUiObjects {

    private static UiObject
            menu,
            share,
            back,
            advertisement,
            addEffects,
            saveToDevice,
            addToFavorites,
            animate,
            addArtStyle,
            addYourText,
            closeEffects,
            destination;

    public UiObject menu(){
        if(menu == null) menu = new UiSelector().className("android.widget.ImageView").description("More options").makeUiObject();
        return menu;
    }

    public UiObject share(){
        if(share == null) share = new UiSelector().className("android.widget.TextView").resourceId(Android.app.photoLab.packageID()+":id/menu_share").makeUiObject();
        return share;
    }

    public UiObject back(){
        if(back == null) back = new UiSelector().className("android.widget.ImageButton").resourceId("android:id/button1").makeUiObject();
        return back;
    }

    public UiObject advertisement(){
        if(advertisement == null) advertisement = new UiSelector().className("android.webkit.WebView").packageName(Android.app.photoLab.packageID()).description("Web View").makeUiObject();
        return advertisement;
    }

    public UiObject addEffects(){
        if(addEffects == null) addEffects = new UiSelector().className("android.widget.ImageButton").resourceId(Android.app.photoLab.packageID()+":id/add").makeUiObject();
        return addEffects;
    }

    public UiObject saveToDevice(){
        if(saveToDevice == null) saveToDevice = new UiSelector().className("android.widget.TextView").text("Save to device").makeUiObject();
        return saveToDevice;
    }

    public UiObject addToFavorites(){
        if(addToFavorites == null) addToFavorites = new UiSelector().className("android.widget.TextView").text("Add to Favorites").makeUiObject();
        return addToFavorites;
    }

    public UiObject animate(){
        if(animate == null) animate = new UiSelector().className("android.widget.ImageButton").resourceId(Android.app.photoLab.packageID()+":id/add_action3").makeUiObject();
        return animate;
    }

    public UiObject addArtStyle(){
        if(addArtStyle == null) addArtStyle = new UiSelector().className("android.widget.ImageButton").resourceId(Android.app.photoLab.packageID()+":id/add_action2").makeUiObject();
        return addArtStyle;
    }

    public UiObject addYourText(){
        if(addYourText == null) addYourText = new UiSelector().className("android.widget.ImageButton").resourceId(Android.app.photoLab.packageID()+":id/add_action1").makeUiObject();
        return addYourText;
    }

    public UiObject closeEffects(){
        if(closeEffects == null) closeEffects = new UiSelector().className("android.widget.ImageButton").resourceId(Android.app.photoLab.packageID()+":id/add").makeUiObject();
        return closeEffects;
    }

    public UiObject destination(){
        if(destination == null) destination = new UiSelector().className("android.widget.ImageButton").resourceId(Android.app.photoLab.packageID()+":id/add").makeUiObject();
        return destination;
    }
}
