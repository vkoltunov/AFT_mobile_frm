package api.apps.Xmas.menu;

import core.UiObject;
import core.UiSelector;

/**
 * Created by User on 4/4/2017.
 */
public class MenuUiObjects {

    private static UiObject
            item,
            home,
            list_item,
            by_index,
            about,
            last_item,
            menu;

    public UiObject item(String categoryName){
        item = new UiSelector().className("android.widget.TextView").textContains(categoryName).makeUiObject();
        return item;
    }

    public UiObject menu(){
        if(menu == null) menu = new UiSelector().className("android.widget.ImageButton").resourceId("android:id/button1").makeUiObject();
        return menu;
    }

    public UiObject home(){
        if(home == null) home = new UiSelector().className("android.widget.TextView").resourceId("android:id/text1").text("Home").makeUiObject();
        return home;
    }

    public UiObject about(){
        if(about == null) about = new UiSelector().className("android.widget.TextView").textContains("About").makeUiObject();
        return about;
    }

    public UiObject list_item(){
        list_item = new UiSelector().xPath("//android.support.v7.widget.RecyclerView//android.widget.LinearLayout//android.widget.TextView[@resource-id='android:id/text1']").makeUiObject();
        return list_item;
    }

    public UiObject last_item(){
        last_item = new UiSelector().xPath("//android.support.v7.widget.RecyclerView//android.widget.LinearLayout[last()]//android.widget.TextView[@resource-id='android:id/text1']").makeUiObject();
        return last_item;
    }

    public UiObject by_index(int index){
        by_index = new UiSelector().xPath("//android.support.v7.widget.RecyclerView//android.widget.LinearLayout[@index='"+index+"']//android.widget.TextView[@resource-id='android:id/text1']").makeUiObject();
        return by_index;
    }

}
