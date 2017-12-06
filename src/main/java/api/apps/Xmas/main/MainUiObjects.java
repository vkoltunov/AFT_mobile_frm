package api.apps.Xmas.main;

import api.android.Android;
import core.UiObject;
import core.UiSelector;

/**
 * Created by User on 4/29/2017.
 */
public class MainUiObjects {

    private static UiObject
            menu,
            photoLab,
            barItem,
            categoryItem,
            category_items,
            categoryItem_by_index,
            category_area,
            navigationContainer;


    public UiObject photoLab(){
        if(photoLab == null) photoLab = new UiSelector().className("android.widget.TextView").resourceId(Android.app.xmas.packageID()+":id/toolbar_title").text("Photo Lab").makeUiObject();
        return photoLab;
    }

    public UiObject navigationContainer(){
        if(navigationContainer == null) navigationContainer = new UiSelector().className("android.widget.LinearLayout").resourceId(Android.app.xmas.packageID()+":id/bottom_navigation_container").makeUiObject();
        return navigationContainer;
    }

    public UiObject menu(){
        if(menu == null) menu = new UiSelector().className("android.widget.ImageButton").resourceId("android:id/button1").makeUiObject();
        return menu;
    }

    public UiObject barItem(String barName){
        //barItem = new UiSelector().className("android.widget.TextView").textContains(barName).makeUiObject();
        barItem = new UiSelector().className("android.widget.CheckedTextView").textContains(barName).makeUiObject();
        return barItem;
    }

    public UiObject categoryItem(String categoryName){
        categoryItem = new UiSelector().className("android.widget.TextView").text(categoryName).makeUiObject();
        return categoryItem;
    }

    public UiObject category_items(){
        category_items = new UiSelector().className("android.widget.FrameLayout").descriptionContains("list_item_").makeUiObject();
        return category_items;
    }

    public UiObject category_area(){
        category_area = new UiSelector().className("android.support.v4.view.ViewPager").resourceId(Android.app.xmas.packageID()+":id/non_swipe_view_pager").makeUiObject();
        return category_area;
    }

    public UiObject categoryItem_by_index(int index){
        categoryItem_by_index = new UiSelector().className("android.widget.FrameLayout").descriptionContains("list_item_").index(index).makeUiObject();
        //categoryItem_by_index = new UiSelector().xPath("//android.support.v7.widget.RecyclerView//android.widget.TextView[@resource-id='android:id/title'][@index='"+index+"']").makeUiObject();
        return categoryItem_by_index;
    }

}
