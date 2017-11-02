package api.apps.PhotoLab.pictures;

import api.android.Android;
import core.UiObject;
import core.UiSelector;

/**
 * Created by User on 4/21/2017.
 */
public class PicturesUiObjects {

    private static UiObject
            tab,
            previewPic,
            picture,
            topPicture,
            firstPic,
            tabAll,
            tabRecent,
            tabSamples,
            favorites,
            advertisement,
            camera,
            next,
            delete,
            back,
            selectedPicCount,
            folder,
            persistent,
            moreOptions,
            option;

    public UiObject previewPic(){
        if(previewPic == null) previewPic = new UiSelector().className("android.widget.LinearLayout").resourceId(Android.app.photoLab.packageID()+":id/collapsing_top_badges_container").makeUiObject();
        return previewPic;
    }

    public UiObject firstPic(){
        firstPic = new UiSelector().xPath("//android.support.v4.view.ViewPager//android.support.v7.widget.RecyclerView//android.widget.FrameLayout[@index='1']//android.widget.ImageView[@resource-id='android:id/icon']").makeUiObject();
        return firstPic;
    }

    public UiObject picture(int ind){
        picture = new UiSelector().xPath("//android.support.v4.view.ViewPager//android.support.v7.widget.RecyclerView//android.widget.FrameLayout[@index='"+ind+"']//android.widget.ImageView[@resource-id='android:id/icon']").makeUiObject();
        return picture;
    }

    public UiObject topPicture(int ind){
        topPicture = new UiSelector().xPath("//android.support.v7.widget.RecyclerView[@resource-id='"+Android.app.photoLab.packageID()+":id/multi_select_list']//android.widget.FrameLayout[@index='"+ind+"']//android.widget.ImageView[@resource-id='android:id/icon']").makeUiObject();
        return topPicture;
    }

    public UiObject tab(String tabName){
        tab = new UiSelector().className("android.widget.TextView").text(tabName).makeUiObject();
        return tab;
    }

    public UiObject tabAll(){
        if(tabAll == null) tabAll = new UiSelector().className("android.widget.TextView").text("All").makeUiObject();
        return tabAll;
    }

    public UiObject tabRecent(){
        if(tabRecent == null) tabRecent = new UiSelector().className("android.widget.TextView").text("Recent").makeUiObject();
        return tabRecent;
    }

    public UiObject tabSamples(){
        if(tabSamples == null) tabSamples = new UiSelector().className("android.widget.TextView").text("Samples").makeUiObject();
        return tabSamples;
    }

    public UiObject favorites(){
        if(favorites == null) favorites = new UiSelector().className("android.widget.TextView").resourceId(Android.app.photoLab.packageID()+":id/favorite").makeUiObject();
        return favorites;
    }

    public UiObject advertisement(){
        if(advertisement == null) advertisement = new UiSelector().className("android.webkit.WebView").packageName(Android.app.photoLab.packageID()).makeUiObject();
        return advertisement;
    }

    public UiObject camera(){
        if(camera == null) camera = new UiSelector().className("android.widget.FrameLayout").description("list_item_0").makeUiObject();
        return camera;
    }

    public UiObject next(){
        if(next == null) next = new UiSelector().className("android.widget.ImageButton").resourceId(Android.app.photoLab.packageID()+":id/next_fab").makeUiObject();
        return next;
    }

    public UiObject delete(){
        if(delete == null) delete = new UiSelector().className("android.widget.TextView").resourceId(Android.app.photoLab.packageID()+":id/delete").makeUiObject();
        return delete;
    }

    public UiObject back(){
        if(back == null) back = new UiSelector().className("android.widget.ImageButton").resourceId("android:id/button1").makeUiObject();
        return back;
    }

    public UiObject selectedPicCount(){
        selectedPicCount = new UiSelector().className("android.widget.TextView").resourceId(Android.app.photoLab.packageID()+":id/toolbar_title").makeUiObject();
        return selectedPicCount;
    }

    public UiObject folder(String folderName){
        if (folderName.contains("first") || folderName.contains("First")) folder = new UiSelector().className("android.support.v7.app.ActionBar$Tab").index(0).makeUiObject();
        else folder = new UiSelector().className("android.widget.TextView").text(folderName).makeUiObject();
        return folder;
    }

    public UiObject persistent(int ind){
        persistent = new UiSelector().xPath("//android.support.v7.widget.RecyclerView//android.widget.FrameLayout[@index='"+ind+"']//android.widget.FrameLayout[@resource-id='"+Android.app.photoLab.packageID()+":id/email_notifications']").makeUiObject();
        return persistent;
    }

    public UiObject moreOptions(){
        if(moreOptions == null) moreOptions = new UiSelector().className("android.widget.TextView").resourceId(Android.app.photoLab.packageID()+":id/more").makeUiObject();
        return moreOptions;
    }

    public UiObject option(String name){
        option = new UiSelector().className("android.widget.TextView").resourceId(Android.app.photoLab.packageID()+":id/title").textContains(name).makeUiObject();
        return option;
    }
}

