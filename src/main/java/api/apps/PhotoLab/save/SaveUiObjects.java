package api.apps.PhotoLab.save;

import api.android.Android;
import core.UiObject;
import core.UiSelector;

/**
 * Created by User on 5/4/2017.
 */
public class SaveUiObjects {

    private static UiObject
            saveAndShare,
            menu,
            saveToDevice,
            download,
            advertisement,
            save_target,
            collage,
            tutorialLogo,
            tutorialShare,
            share,
            tagEdit;

    public UiObject saveAndShare(){
        if(saveAndShare == null) saveAndShare = new UiSelector().className("android.widget.TextView").text("Save & Share").makeUiObject();
        return saveAndShare;
    }

    public UiObject advertisement(){
        if(advertisement == null) advertisement = new UiSelector().className("android.webkit.WebView").packageName(Android.app.photoLab.packageID()).makeUiObject();
        return advertisement;
    }

    public UiObject menu(){
        if(menu == null) menu = new UiSelector().className("android.widget.ImageView").description("More options").makeUiObject();
        return menu;
    }

    public UiObject saveToDevice(){
        if(saveToDevice == null) saveToDevice = new UiSelector().className("android.widget.TextView").resourceId(Android.app.photoLab.packageID()+":id/title").text("Save to device").makeUiObject();
        return saveToDevice;
    }

    public UiObject download(){
        if(download == null) download = new UiSelector().className("android.widget.TextView").text("Download").makeUiObject();
        return download;
    }

    public UiObject save_target(String target){
        save_target = new UiSelector().className("android.widget.TextView").text(target).makeUiObject();
        return save_target;
    }

    public UiObject collage(){
        if(collage == null) collage = new UiSelector().className("android.view.View").resourceId(Android.app.photoLab.packageID()+":id/collageView").makeUiObject();
        return collage;
    }

    public UiObject tutorialLogo(){
        if(tutorialLogo == null) tutorialLogo = new UiSelector().className("android.widget.TextView").textContains("Tap the logo to remove it").makeUiObject();
        return tutorialLogo;
    }

    public UiObject tutorialShare(){
        if(tutorialShare == null) tutorialShare = new UiSelector().className("android.widget.TextView").textContains("Share your masterpiece").makeUiObject();
        return tutorialShare;
    }

    public UiObject share(){
        if(share == null) share = new UiSelector().className("android.widget.Button").resourceId("android:id/button1").text("Share").makeUiObject();
        return share;
    }

    public UiObject tagEdit(){
        if(tagEdit == null) tagEdit = new UiSelector().className("android.widget.EditText").resourceId("android:id/edit").makeUiObject();
        return tagEdit;
    }
}
