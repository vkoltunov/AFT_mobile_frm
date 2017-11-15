package api.apps.Teleport;

import api.android.Android;
import core.UiObject;
import core.UiSelector;

/**
 * Created by User on 10/9/2017.
 */
public class TeleportUiObjects {

    private static UiObject
            gallery,
            recent,
            openFrom,
            pic,
            editPhoto,
            done,
            image,
            filtersTopScroll,
            filtersBottomScroll,
            filterNameTop,
            filterNameBottom,
            menu,
            save;

    public UiObject gallery(){
        if(gallery == null) gallery = new UiSelector().className("android.widget.TextView").resourceId("com.teleportfuturetechnologies.teleport:id/gallery").text("Gallery").makeUiObject();
        return gallery;
    }

    public UiObject recent(){
        if(recent == null) recent = new UiSelector().className("android.widget.TextView").resourceId("android:id/title").text("Recent").makeUiObject();
        return recent;
    }

    public UiObject openFrom(){
        if(openFrom == null) openFrom = new UiSelector().className("android.widget.TextView").text("Open from").makeUiObject();
        return openFrom;
    }

    public UiObject pic(){
        if(pic == null) pic = new UiSelector().className("android.widget.FrameLayout").index(0).clickable(true).makeUiObject();
        return pic;
    }

    public UiObject editPhoto(){
        if(editPhoto == null) editPhoto = new UiSelector().className("android.widget.TextView").resourceId("com.teleportfuturetechnologies.teleport:id/toolbar_title").text("Edit Photo").makeUiObject();
        return editPhoto;
    }

    public UiObject done(){
        if(done == null) done = new UiSelector().className("android.widget.TextView").resourceId("com.teleportfuturetechnologies.teleport:id/menu_crop").description("Crop").makeUiObject();
        return done;
    }

    public UiObject image(){
        if(image == null) image = new UiSelector().className("android.widget.ImageView").resourceId("com.teleportfuturetechnologies.teleport:id/image_view_crop").makeUiObject();
        return image;
    }

    public UiObject filtersTopScroll(){
        if(filtersTopScroll == null) filtersTopScroll = new UiSelector().className("android.support.v7.widget.RecyclerView").resourceId("com.teleportfuturetechnologies.teleport:id/pagerTabStrip").makeUiObject();
        return filtersTopScroll;
    }

    public UiObject filtersBottomScroll(){
        if(filtersBottomScroll == null) filtersBottomScroll = new UiSelector().className("android.support.v7.widget.RecyclerView").resourceId("com.teleportfuturetechnologies.teleport:id/recyclerView").makeUiObject();
        return filtersBottomScroll;
    }

    public UiObject filterNameTop (String name){
        filterNameTop = new UiSelector().className("android.widget.TextView").text(name).makeUiObject();
        return filterNameTop;
    }

    public UiObject filterNameBottom (String name){
        filterNameBottom = new UiSelector().className("android.widget.TextView").resourceId("com.teleportfuturetechnologies.teleport:id/text").text(name).makeUiObject();
        return filterNameBottom;
    }

    public UiObject menu(){
        if(menu == null) menu = new UiSelector().className("android.view.View").resourceId("com.teleportfuturetechnologies.teleport:id/menu").makeUiObject();
        return menu;
    }

    public UiObject openMenu(){
        save = new UiSelector().xPath("//android.widget.RelativeLayout[@resource-id='com.teleportfuturetechnologies.teleport:id/image_layout']//android.view.View[@resource-id='com.teleportfuturetechnologies.teleport:id/menu']//android.widget.ImageButton[@index='0']").makeUiObject();
        return save;
    }

    public UiObject save(){
        save = new UiSelector().xPath("//android.widget.RelativeLayout[@resource-id='com.teleportfuturetechnologies.teleport:id/image_layout']//android.view.View[@resource-id='com.teleportfuturetechnologies.teleport:id/menu']//android.widget.ImageButton[@index='2']").makeUiObject();
        return save;
    }

}