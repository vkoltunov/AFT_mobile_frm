package api.apps.PhotoLab.art;

import core.UiObject;
import core.UiSelector;

/**
 * Created by User on 5/3/2017.
 */
public class ArtUiObjects {

    private static UiObject
            done,
            addArtStyle,
            artItem,
            artCounts,
            art_byIndex,
            art_None;

    public UiObject addArtStyle(){
        if(addArtStyle == null) addArtStyle = new UiSelector().className("android.widget.TextView").textContains("Art").makeUiObject();
        return addArtStyle;
    }

    public UiObject done(){
        if(done == null) done = new UiSelector().className("android.widget.ImageButton").resourceId("vsin.t16_funny_photo:id/fab").makeUiObject();
        return done;
    }

    public UiObject artItem(String animateName){
        artItem = new UiSelector().className("android.widget.TextView").textContains(animateName).makeUiObject();
        return artItem;
    }

    public UiObject artCounts(){
        artCounts = new UiSelector().xPath("//android.support.v7.widget.RecyclerView//android.widget.ImageView[@resource-id='android:id/icon']").makeUiObject();
        return artCounts;
    }

    public UiObject art_byIndex(int index){
        art_byIndex = new UiSelector().xPath("//android.support.v7.widget.RecyclerView//android.widget.FrameLayout[@index='"+index+"']//android.widget.ImageView[@resource-id='android:id/icon']").makeUiObject();
        return art_byIndex;
    }

    public UiObject art_None(){
        art_None = new UiSelector().className("android.widget.TextView").resourceId("android:id/title").textContains("None").makeUiObject();
        return art_None;
    }

}
