package api.apps.PhotoLab.animate;

import core.UiObject;
import core.UiSelector;

/**
 * Created by User on 5/3/2017.
 */
public class AnimateUiObjects {

    private static UiObject
            done,
            animate,
            animateItem,
            animateCounts,
            animate_byIndex,
            animate_None,
            animate_List,
            toolbar_Title,
            animate_items,
            animateItem_by_index,
            animate_Result;

    public UiObject animate(){
        if(animate == null) animate = new UiSelector().className("android.widget.TextView").text("Animate").makeUiObject();
        return animate;
    }

    public UiObject done(){
        if(done == null) done = new UiSelector().className("android.widget.ImageButton").resourceId("vsin.t16_funny_photo:id/fab").makeUiObject();
        return done;
    }

    public UiObject animateItem(String animateName){
        animateItem = new UiSelector().className("android.widget.TextView").textContains(animateName).makeUiObject();
        return animateItem;
    }

    public UiObject animateCounts(){
        animateCounts = new UiSelector().xPath("//android.support.v7.widget.RecyclerView//android.widget.ImageView[@resource-id='android:id/icon']").makeUiObject();
        return animateCounts;
    }

    public UiObject animate_byIndex(int index){
        animate_byIndex = new UiSelector().xPath("//android.support.v7.widget.RecyclerView//android.widget.FrameLayout[@index='"+index+"']//android.widget.ImageView[@resource-id='android:id/icon']").makeUiObject();
        return animate_byIndex;
    }

    public UiObject animate_None(){
        animate_None = new UiSelector().className("android.widget.TextView").resourceId("android:id/title").textContains("None").makeUiObject();
        return animate_None;
    }

    public UiObject animate_List(){
        animate_List = new UiSelector().className("android.support.v7.widget.RecyclerView").resourceId("android:id/list").makeUiObject();
        return animate_List;
    }

    public UiObject toolbar_Title(){
        toolbar_Title = new UiSelector().className("android.widget.TextView").resourceId("vsin.t16_funny_photo:id/toolbar_title").makeUiObject();
        return toolbar_Title;
    }

    public UiObject animate_items(){
        animate_items = new UiSelector().className("android.widget.FrameLayout").descriptionContains("list_item_").makeUiObject();
        return animate_items;
    }

    public UiObject animateItem_by_index(int index){
        animateItem_by_index = new UiSelector().className("android.widget.FrameLayout").descriptionContains("list_item_").index(index).makeUiObject();
        return animateItem_by_index;
    }

    public UiObject animate_Result(){
        animate_Result = new UiSelector().className("android.view.View").resourceId("vsin.t16_funny_photo:id/result_content").makeUiObject();
        return animate_Result;
    }


}
