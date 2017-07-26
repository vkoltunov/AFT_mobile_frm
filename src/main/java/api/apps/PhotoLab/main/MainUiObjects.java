package api.apps.PhotoLab.main;

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
            categoryItem_by_desc,
            delete,
            favoritesText,
            categoryItem_last,
            inspirationBlock,
            category_area,
            inspirationItem;

    public UiObject photoLab(){
        if(photoLab == null) photoLab = new UiSelector().className("android.widget.TextView").resourceId("vsin.t16_funny_photo:id/toolbar_title").text("Photo Lab").makeUiObject();
        return photoLab;
    }

    public UiObject actionBar(){
        if(actionBar == null) actionBar = new UiSelector().className("android.support.v7.app.ActionBar$Tab").packageName("vsin.t16_funny_photo").instance(0).makeUiObject();
        return actionBar;
    }

    public UiObject pro(){
        if(pro == null) pro = new UiSelector().className("android.widget.TextView").resourceId("vsin.t16_funny_photo:id/buy").makeUiObject();
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
        barItem = new UiSelector().className("android.widget.TextView").textContains(barName).makeUiObject();
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
        category_area = new UiSelector().className("android.support.v7.widget.RecyclerView").resourceId("vsin.t16_funny_photo:id/recyclerView").makeUiObject();
        return category_area;
    }

    public UiObject categoryItem_by_index(int index){
        categoryItem_by_index = new UiSelector().className("android.widget.FrameLayout").descriptionContains("list_item_").index(index).makeUiObject();
        //categoryItem_by_index = new UiSelector().xPath("//android.support.v7.widget.RecyclerView//android.widget.TextView[@resource-id='android:id/title'][@index='"+index+"']").makeUiObject();
        return categoryItem_by_index;
    }

    public UiObject categoryItem_last(){
        categoryItem_last = new UiSelector().xPath("//android.support.v7.widget.RecyclerView//android.widget.TextView[last()]").makeUiObject();
        return categoryItem_last;
    }

    public UiObject categoryItem_by_desc(int index){
        categoryItem_by_desc = new UiSelector().className("android.widget.FrameLayout").descriptionContains("list_item_"+index).makeUiObject();
        return categoryItem_by_desc;
    }

    public UiObject delete(){
        if(delete == null) delete = new UiSelector().className("android.widget.ImageButton").resourceId("vsin.t16_funny_photo:id/delete_fab").makeUiObject();
        return delete;
    }

    public UiObject favoritesText(){
        if(favoritesText == null) favoritesText = new UiSelector().className("android.widget.TextView").resourceId("vsin.t16_funny_photo:id/favoriteEmpty").textContains("Your favorite effects \n" +
                "will be stored here.").makeUiObject();
        return favoritesText;
    }

    public UiObject inspirationBlock(String name){
        inspirationBlock = new UiSelector().className("android.widget.CheckedTextView").textContains(name).makeUiObject();
        return inspirationBlock;
    }

    public UiObject inspirationItem(int index){
        inspirationItem = new UiSelector().xPath("//android.support.v7.widget.RecyclerView//android.widget.FrameLayout[@index='"+index+"']//android.widget.ImageView[@resource-id='vsin.t16_funny_photo:id/image_collage']").makeUiObject();
        return inspirationItem;
    }
}
