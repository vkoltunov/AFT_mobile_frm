package api.apps.PhotoLab.main;

import api.android.Android;
import api.apps.PhotoLab.PhotoLab;
import core.UiObject;
import core.UiSelector;

/**
 * Created by User on 4/29/2017.
 */
public class MainUiObjects {

    private static UiObject
            pro,
            actionBar,
            install,
            menu,
            photoLab,
            barItem,
            bar_items,
            barItem_by_index,
            firsInstanceBarItem,
            categoryItem,
            category_items,
            categoryItem_by_index,
            delete,
            favoritesText,
            inspirationBlock,
            category_area,
            inspirationItem,
            inspirationProfile,
            navigationContainer,
            bottomNavigation,
            searchButton;


    public UiObject photoLab(){

        if(photoLab == null) photoLab = new UiSelector().className("android.widget.TextView").resourceId(Android.app.photoLab.packageID()+":id/toolbar_title").text("Photo Lab").makeUiObject();
        return photoLab;
    }

    public UiObject navigationContainer(){
        if(navigationContainer == null) navigationContainer = new UiSelector().className("android.widget.LinearLayout").resourceId(Android.app.photoLab.packageID()+":id/bottom_navigation_container").makeUiObject();
        return navigationContainer;
    }

    public UiObject bottomNavigation(){
        if(bottomNavigation == null) bottomNavigation = new UiSelector().className("android.widget.LinearLayout").resourceId(Android.app.photoLab.packageID()+":id/bottom_navigation").makeUiObject();
        return bottomNavigation;
    }

    public UiObject searchButton(){
        if(searchButton == null) searchButton = new UiSelector().className("android.widget.TextView").resourceId(Android.app.photoLab.packageID()+":id/search").makeUiObject();
        return searchButton;
    }

    public UiObject actionBar(){
        if(actionBar == null) actionBar = new UiSelector().className("android.support.v7.app.ActionBar$Tab").instance(0).makeUiObject();
        return actionBar;
    }

    public UiObject pro(){
        if(pro == null) pro = new UiSelector().className("android.widget.TextView").resourceId(Android.app.photoLab.packageID()+":id/buy").makeUiObject();
        return pro;
    }

    public UiObject install(){
        if(install == null) install = new UiSelector().className("android.widget.Button").text("Install").makeUiObject();
        return install;
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

    public UiObject bar_items(){
        bar_items = new UiSelector().className("android.support.v7.app.ActionBar$Tab").makeUiObject();
        return bar_items;
    }

    public UiObject barItem_by_index(int index){
        barItem_by_index = new UiSelector().className("android.support.v7.app.ActionBar$Tab").index(index).makeUiObject();
        return barItem_by_index;
    }

    public UiObject firsInstanceBarItem(){
        firsInstanceBarItem = new UiSelector().className("android.support.v7.app.ActionBar$Tab").instance(0).makeUiObject();
        return firsInstanceBarItem;
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
        category_area = new UiSelector().className("android.support.v7.widget.RecyclerView").resourceId(Android.app.photoLab.packageID()+":id/recyclerView").makeUiObject();
        return category_area;
    }

    public UiObject categoryItem_by_index(int index){
        categoryItem_by_index = new UiSelector().className("android.widget.FrameLayout").descriptionContains("list_item_").index(index).makeUiObject();
        //categoryItem_by_index = new UiSelector().xPath("//android.support.v7.widget.RecyclerView//android.widget.TextView[@resource-id='android:id/title'][@index='"+index+"']").makeUiObject();
        return categoryItem_by_index;
    }

    public UiObject delete(){
        if(delete == null) delete = new UiSelector().className("android.widget.ImageButton").resourceId(Android.app.photoLab.packageID()+":id/delete_fab").makeUiObject();
        return delete;
    }

    public UiObject favoritesText(){
        if(favoritesText == null) favoritesText = new UiSelector().className("android.widget.TextView").resourceId(Android.app.photoLab.packageID()+":id/favoriteEmpty").textContains("Your favorite effects \n" +
                "will be stored here.").makeUiObject();
        return favoritesText;
    }

    public UiObject inspirationBlock(String name){
        inspirationBlock = new UiSelector().className("android.widget.TextView").text(name).makeUiObject();
        return inspirationBlock;
    }

    public UiObject inspirationItem(int index){
        inspirationItem = new UiSelector().xPath("//android.support.v7.widget.RecyclerView//android.widget.FrameLayout[@index='"+index+"']//android.widget.ImageView[@resource-id='"+Android.app.photoLab.packageID()+":id/image_collage']").makeUiObject();
        return inspirationItem;
    }

    public UiObject inspirationProfile(){
        if(inspirationProfile == null) inspirationProfile = new UiSelector().className("android.widget.TextView").resourceId(Android.app.photoLab.packageID()+":id/profile").makeUiObject();
        return inspirationProfile;
    }
}
