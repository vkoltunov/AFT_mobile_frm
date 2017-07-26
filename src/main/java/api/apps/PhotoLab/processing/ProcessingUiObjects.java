package api.apps.PhotoLab.processing;

import core.UiObject;
import core.UiSelector;

/**
 * Created by User on 5/3/2017.
 */
public class ProcessingUiObjects {

    private static UiObject
            progressBar,
            processing,
            back,
            advertisementBlock,
            advertisementPage,
            share,
            goPRO;

    public UiObject progressBar(){
        if(progressBar == null) progressBar = new UiSelector().className("android.widget.ProgressBar").resourceId("vsin.t16_funny_photo:id/progressBar").makeUiObject();
        return progressBar;
    }

    public UiObject processing(){
        if(processing == null) processing = new UiSelector().className("android.widget.TextView").textContains("Processing…").makeUiObject();
        return processing;
    }

    public UiObject advertisementBlock(){
        if(advertisementBlock == null) advertisementBlock = new UiSelector().className("android.webkit.WebView").packageName("vsin.t16_funny_photo").makeUiObject();
        return advertisementBlock;
    }

    public UiObject advertisementPage(){
        if(advertisementPage == null) advertisementPage = new UiSelector().className("android.webkit.WebView").packageName("vsin.t16_funny_photo").makeUiObject();
        return advertisementPage;
    }

    public UiObject back(){
        if(back == null) back = new UiSelector().className("android.widget.ImageButton").resourceId("android:id/button1").makeUiObject();
        return back;
    }

    public UiObject share(){
        if(share == null) share = new UiSelector().className("android.widget.TextView").resourceId("vsin.t16_funny_photo:id/menu_share").makeUiObject();
        return share;
    }

    public UiObject goPRO(){
        if(goPRO == null) goPRO = new UiSelector().className("android.widget.Button").resourceId("android:id/button1").textContains("Go PRO now").makeUiObject();
        return goPRO;
    }
}
