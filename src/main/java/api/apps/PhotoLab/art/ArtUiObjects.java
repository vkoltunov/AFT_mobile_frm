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
            art_None,
            toolbar_Title,
            artItem_by_index,
            art_items,
            art_Result;

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
        art_None = new UiSelector().className("android.widget.TextView").resourceId("android:id/text1").textContains("None").makeUiObject();
        return art_None;
    }

    public UiObject art_List(){
        art_None = new UiSelector().className("android.support.v7.widget.RecyclerView").resourceId("android:id/list").makeUiObject();
        return art_None;
    }

    public UiObject toolbar_Title(){
        toolbar_Title = new UiSelector().className("android.widget.TextView").resourceId("vsin.t16_funny_photo:id/toolbar_title").makeUiObject();
        return toolbar_Title;
    }

    public UiObject art_items(){
        art_items = new UiSelector().className("android.widget.FrameLayout").descriptionContains("list_item_").makeUiObject();
        return art_items;
    }

    public UiObject artItem_by_index(int index){
        artItem_by_index = new UiSelector().className("android.widget.FrameLayout").descriptionContains("list_item_").index(index).makeUiObject();
        return artItem_by_index;
    }

    public UiObject art_Result(){
        art_Result = new UiSelector().className("android.view.View").resourceId("vsin.t16_funny_photo:id/result_content").makeUiObject();
        return art_Result;
    }

}
