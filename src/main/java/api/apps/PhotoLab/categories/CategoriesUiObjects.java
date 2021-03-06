package api.apps.PhotoLab.categories;

import api.android.Android;
import core.MyLogger;
import core.UiObject;
import core.UiSelector;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

import java.util.concurrent.TimeUnit;

import static api.android.Android.driver;

/**
 * Created by User on 4/4/2017.
 */
public class CategoriesUiObjects {

    private static UiObject
            advertisement,
            effectItem,
            effect_items,
            effectItem_by_index,
            effectItem_last,
            category_area,
            page_Title,
            newIcon,
            listItemTitle;

    public UiObject page_Title(String title){
        page_Title = new UiSelector().className("android.widget.TextView").resourceId(Android.app.photoLab.packageID()+":id/toolbar_title").textContains(title).makeUiObject();
        return page_Title;
    }

    public UiObject advertisement(){
        if(advertisement == null) advertisement = new UiSelector().className("android.webkit.WebView").packageName(Android.app.photoLab.packageID()).makeUiObject();
        return advertisement;
    }

    public UiObject effectItem(String effectName){
        effectItem = new UiSelector().className("android.widget.TextView").resourceId("android:id/title").text(effectName).makeUiObject();
        return effectItem;
    }

    public UiObject effect_items(){
        //effect_items = new UiSelector().className("android.widget.FrameLayout").descriptionContains("list").makeUiObject();
        effect_items = new UiSelector().className("android.widget.FrameLayout").descriptionContains("list_item_").makeUiObject();
        return effect_items;
    }

    public UiObject effectItem_by_index(int index){
        effectItem_by_index = new UiSelector().className("android.widget.FrameLayout").descriptionContains("list_item_").index(index).makeUiObject();
        //effectItem_by_index = new UiSelector().xPath("//android.support.v7.widget.RecyclerView//android.widget.TextView[@resource-id='android:id/title']["+index+"]").makeUiObject();
        return effectItem_by_index;
    }

    public UiObject effectItem_last(){
        effectItem_last = new UiSelector().xPath("//android.support.v7.widget.RecyclerView//android.widget.TextView[last()]").makeUiObject();
        return effectItem_last;
    }

    public UiObject category_area(){
        category_area = new UiSelector().className("android.support.v7.widget.RecyclerView").resourceId(Android.app.photoLab.packageID()+":id/recyclerView").makeUiObject();
        return category_area;
    }

    public UiObject newIcon(int effectIndex){
        newIcon = new UiSelector().xPath("//android.support.v7.widget.RecyclerView//android.widget.FrameLayout[@content-desc='list_item_"+effectIndex+"']//android.widget.ImageView[@resource-id='android:id/icon']").makeUiObject();
        return newIcon;
    }

    public UiObject listItemTitle(int effectIndex){
        listItemTitle = new UiSelector().xPath("//android.support.v7.widget.RecyclerView//android.widget.FrameLayout[@content-desc='list_item_"+effectIndex+"']//android.widget.TextView[@resource-id='android:id/title']").makeUiObject();
        return listItemTitle;
    }


}
