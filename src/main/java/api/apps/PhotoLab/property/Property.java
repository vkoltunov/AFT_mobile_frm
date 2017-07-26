package api.apps.PhotoLab.property;

import api.android.Android;
import api.apps.PhotoLab.pictures.Pictures;
import api.apps.PhotoLab.processing.Processing;
import api.apps.PhotoLab.result.Result;
import api.interfaces.Activity;
import core.MyLogger;

/**
 * Created by User on 5/2/2017.
 */
public class Property implements Activity {

    public PropertyUiObjects uiObject = new PropertyUiObjects();

    @Override
    public Property waitToLoad(){
        try{
            MyLogger.log.info("Waiting for Picture Property page");
            uiObject.done().waitToAppear(10);
            return Android.app.photoLab.property;
        }catch (AssertionError e) {
            throw new AssertionError("Picture Property page failed to load/open");
        }
    }

    public Object tapDone(){
        try{
            MyLogger.log.info("Tap to Next button.");
            uiObject.done().tap();
            return Android.app.photoLab.processing.waitToLoad();
        }catch (AssertionError e) {
            throw new AssertionError("Next button failed to tap.");
        }
    }

    public void tapCrop1(){
        try{
            MyLogger.log.info("Tap to Crop 1 button.");
            uiObject.crop1().tap();
        }catch (AssertionError e) {
            throw new AssertionError("Crop 1 button failed to tap.");
        }
    }

    public void tapCrop2(){
        try{
            MyLogger.log.info("Tap to Crop 2 button.");
            uiObject.crop2().tap();
        }catch (AssertionError e) {
            throw new AssertionError("Crop 2 button failed to tap.");
        }
    }

    public void tapCrop3(){
        try{
            MyLogger.log.info("Tap to Crop 3 button.");
            uiObject.crop3().tap();
        }catch (AssertionError e) {
            throw new AssertionError("Crop 3 button failed to tap.");
        }
    }

    public void tapRotate(){
        try{
            MyLogger.log.info("Tap to Rotate button.");
            uiObject.rotate().tap();
        }catch (AssertionError e) {
            throw new AssertionError("Rotate button failed to tap.");
        }
    }

    public void onHints(){
        try{
            MyLogger.log.info("Check Effects Hint button on.");
            if (!uiObject.hints().isChecked()) uiObject.hints().tap();
        }catch (AssertionError e) {
            throw new AssertionError("Hints button failed to check on.");
        }
    }

    public void offHints(){
        try{
            MyLogger.log.info("Check Effects Hint button off.");
            if (uiObject.hints().isChecked()) uiObject.hints().tap();
        }catch (AssertionError e) {
            throw new AssertionError("Hints button failed to check off.");
        }
    }

    public Pictures tapBack(){
        try{
            MyLogger.log.info("Tap Back button.");
            uiObject.back().tap();
            return Android.app.photoLab.pictures.waitToLoad();
        }catch (AssertionError e) {
            throw new AssertionError("Back button failed to tap.");
        }
    }

}
