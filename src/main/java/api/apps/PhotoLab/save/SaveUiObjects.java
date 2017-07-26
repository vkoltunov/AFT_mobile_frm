package api.apps.PhotoLab.save;

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
            tutorialShare;

    public UiObject saveAndShare(){
        if(saveAndShare == null) saveAndShare = new UiSelector().className("android.widget.TextView").text("Save & Share").makeUiObject();
        return saveAndShare;
    }

    public UiObject advertisement(){
        if(advertisement == null) advertisement = new UiSelector().className("android.webkit.WebView").packageName("vsin.t16_funny_photo").makeUiObject();
        return advertisement;
    }

    public UiObject menu(){
        if(menu == null) menu = new UiSelector().className("android.widget.ImageView").description("More options").makeUiObject();
        return menu;
    }

    public UiObject saveToDevice(){
        if(saveToDevice == null) saveToDevice = new UiSelector().className("android.widget.TextView").resourceId("vsin.t16_funny_photo:id/title").text("Save to device").makeUiObject();
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
        if(collage == null) collage = new UiSelector().className("android.view.View").resourceId("vsin.t16_funny_photo:id/collageView").makeUiObject();
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

}
