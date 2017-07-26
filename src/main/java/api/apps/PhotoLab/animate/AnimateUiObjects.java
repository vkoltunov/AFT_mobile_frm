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
            animate_None;

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

}
