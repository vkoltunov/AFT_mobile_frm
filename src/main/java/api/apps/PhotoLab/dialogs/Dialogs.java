package api.apps.PhotoLab.dialogs;

import api.android.Android;
import api.apps.PhotoLab.advertisement.Advertisement;
import api.apps.PhotoLab.main.Main;
import api.apps.PhotoLab.main.MainUiObjects;
import api.apps.PhotoLab.profile.Profile;
import api.apps.PhotoLab.save.Save;
import api.apps.PhotoLab.store.Store;
import api.interfaces.Activity;
import core.MyLogger;

/**
 * Created by User on 6/2/2017.
 */
public class Dialogs implements Activity {

    public DialogsUiObjects uiObject = new DialogsUiObjects();

    @Override
    public Dialogs waitToLoad(){
        try{
            MyLogger.log.info("Waiting for dialog alert.");
            uiObject.dialog().waitToAppear(10);
            return Android.app.photoLab.dialogs;
        }catch (AssertionError e) {
            throw new AssertionError("Dialog alert failed to load/open");
        }
    }

    public void tapSkip(){
        try{
            MyLogger.log.info("Tap to Skip button for dialog.");
            uiObject.skip().waitToAppear(10).tap();
        }catch (AssertionError e) {
            throw new AssertionError("Skip button failed to tap/load for dialog");
        }
    }

    public Store tapRate(){
        try{
            MyLogger.log.info("Tap to Rate button for dialog.");
            uiObject.rate().waitToAppear(10).tap();
            return Android.app.photoLab.store.waitToLoad();
        }catch (AssertionError e) {
            throw new AssertionError("Rate button failed to tap/load for dialog");
        }
    }

    public Store tapGoPRO(){
        try{
            MyLogger.log.info("Tap to GO PRO button for dialog.");
            uiObject.goPRO().waitToAppear(10).tap();
            return Android.app.photoLab.store.waitToLoad();
        }catch (AssertionError e) {
            throw new AssertionError("GO PRO button failed to tap/load for dialog");
        }
    }

    public void tapNotNow(){
        try{
            MyLogger.log.info("Tap to Not Now button for dialog.");
            int counter = 0;
            while (!Android.app.photoLab.custom.checkVideoAdLoaded() && counter < 20) {
                Thread.sleep(1000);
                counter++;
            }
            uiObject.notNow().waitToAppear(10).tap();
        }catch (AssertionError e) {
            throw new AssertionError("Not Now button failed to tap/load for dialog");
        }catch (InterruptedException e) {
            throw new AssertionError("Video Advertisement failed to wait.");
        }
    }

    public Advertisement tapYes(){
        try{
            MyLogger.log.info("Tap to Yes button for dialog.");
            uiObject.yes().waitToAppear(10).tap();
            return Android.app.photoLab.advertisement.waitToLoad();
        }catch (AssertionError e) {
            throw new AssertionError("No button failed to tap/load for dialog");
        }
    }

    public Save tapNo(){
        try{
            MyLogger.log.info("Tap to No button for dialog.");
            uiObject.no().waitToAppear(10).tap();
            return Android.app.photoLab.save.waitToLoad();
        }catch (AssertionError e) {
            throw new AssertionError("Yes button failed to tap/load for dialog");
        }
    }

    public Save tapOk(){
        try{
            MyLogger.log.info("Tap to OK button for dialog.");
            uiObject.ok().waitToAppear(10).tap();
            return Android.app.photoLab.save.waitToLoad();
        }catch (AssertionError e) {
            throw new AssertionError("OK button failed to tap/load for dialog");
        }
    }

    public Profile tapShowInFeed(){
        try{
            MyLogger.log.info("Tap to Show In Feed button for dialog.");
            uiObject.showInFeed().waitToAppear(10).tap();
            return Android.app.photoLab.profile.waitToLoad();
        }catch (AssertionError e) {
            throw new AssertionError("Show In Feed button failed to tap/load for dialog");
        }
    }

    public Boolean checkDialogMessage (String message){
        MyLogger.log.info("Check dialog message contains text '"+message+"'");
        waitToLoad();
        if (uiObject.dialogMessage().getText().contains(message)) {
            MyLogger.log.info("Dialog message text contains TRUE");
            return true;
        }
        else {
            MyLogger.log.info("Dialog message text contains FALSE");
            return false;
        }
    }

    public Boolean checkDialogTitle (String title){
        MyLogger.log.info("Check dialog title contains text '"+title+"'");
        waitToLoad();
        if (uiObject.dialogTitle().getText().contains(title)) {
            MyLogger.log.info("Dialog title text contains TRUE");
            return true;
        }
        else {
            MyLogger.log.info("Dialog title text contains FALSE");
            return false;
        }
    }

}
