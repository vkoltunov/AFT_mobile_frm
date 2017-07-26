package api.apps.PhotoLab.pictures;

import api.android.Android;
import api.apps.PhotoLab.aboutCombo.AboutCombo;
import api.apps.PhotoLab.camera.Camera;
import api.apps.PhotoLab.main.Main;
import api.apps.PhotoLab.property.Property;
import api.interfaces.Activity;
import core.MyLogger;

import java.util.concurrent.TimeUnit;

import static api.android.Android.driver;

/**
 * Created by User on 4/21/2017.
 */
public class Pictures implements Activity {

    public PicturesUiObjects uiObject = new PicturesUiObjects();

    @Override
    public Pictures waitToLoad(){
        try{
            MyLogger.log.info("waiting for pictures activity");
            uiObject.previewPic().waitToAppear(10);
            return Android.app.photoLab.pictures;
        }catch (AssertionError e) {
            throw new AssertionError("pictures activity failed to load/open");
        }
    }

    public void selectPicture(int ind){
        try{
            MyLogger.log.info("Select Picture number "+ind);
            uiObject.picture(ind).waitToAppear(10).tap();
        }catch (AssertionError e) {
            throw new AssertionError("Picture select failed. Maybe picture doesn't exist");
        }
    }

    public void longClickPicture(int ind, String position){
        try{
            MyLogger.log.info("Long Click to Picture number "+ind);
            if (position.contains("top")) uiObject.topPicture(ind-1).waitToAppear(10).longClick();
            else uiObject.picture(ind).waitToAppear(10).longClick();
        }catch (AssertionError e) {
            throw new AssertionError("Long Click to Picture failed. Maybe picture doesn't exist");
        }
    }

    public Pictures selectTab(String tabName){
        try{
            MyLogger.log.info("Select Picture Tab "+tabName);
            uiObject.tab(tabName).tap();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            if (uiObject.folder("Download").size() > 0) {
                selectFolder("Download");
                return Android.app.photoLab.pictures;
            } else return Android.app.photoLab.pictures.waitToLoad();
        }catch (AssertionError e) {
            throw new AssertionError("Picture Tab select failed. Maybe tab with description "+tabName+" doesn't exist");
        }
    }

    public void selectAll(String tabName){
        try{
            MyLogger.log.info("Select All picture tab");
            uiObject.tabAll().tap();
        }catch (AssertionError e) {
            throw new AssertionError("Picture tab All select failed");
        }
    }

    public void selectRecent(String tabName){
        try{
            MyLogger.log.info("Select Recent picture tab");
            uiObject.tabRecent().tap();
        }catch (AssertionError e) {
            throw new AssertionError("Picture tab Recent select failed");
        }
    }

    public void selectSamples(){
        try{
            MyLogger.log.info("Select Samples picture tab");
            uiObject.tabSamples().tap();
        }catch (AssertionError e) {
            throw new AssertionError("Picture tab Samples select failed");
        }
    }

    public Pictures addToFavorites(){
        try{
            MyLogger.log.info("Add to Favorites");
            uiObject.favorites().tap();
            return Android.app.photoLab.pictures.waitToLoad();
        }catch (AssertionError e) {
            throw new AssertionError("Add to favorites failed");
        }
    }

    public Camera tapCamera(){
        try{
            MyLogger.log.info("Tap to Camera Roll.");
            uiObject.camera().tap();
            return Android.app.photoLab.camera.waitToLoad();
        }catch (AssertionError e) {
            throw new AssertionError("Camera roll failed to load/open.");
        }
    }

    public Property tapNext(){
        try{
            MyLogger.log.info("Tap to Next button.");
            uiObject.next().tap();
            return Android.app.photoLab.property.waitToLoad();
        }catch (AssertionError e) {
            throw new AssertionError("Next button failed to tap.");
        }
    }

    public void tapBack(){
        try{
            MyLogger.log.info("Tap to Back button.");
            uiObject.back().tap();
        }catch (AssertionError e) {
            throw new AssertionError("Back button failed to tap.");
        }
    }

    public Pictures tapDelete(){
        try{
            MyLogger.log.info("Tap to Delete button for selected pictures.");
            uiObject.delete().tap();
            return Android.app.photoLab.pictures.waitToLoad();
        }catch (AssertionError e) {
            throw new AssertionError("Delete button failed to tap.");
        }
    }

    public Pictures selectFolder(String folderName){
        try{
            MyLogger.log.info("Select Picture Folder "+folderName);
            uiObject.folder(folderName).tap();
            return Android.app.photoLab.pictures.waitToLoad();
        }catch (AssertionError e) {
            throw new AssertionError("Picture Folder select failed. Maybe folder with description "+folderName+" doesn't exist");
        }
    }

    public Pictures checkPersistent(int indexPhoto, Boolean state){
        MyLogger.log.info("Check persistent label for Photo "+indexPhoto);
        Boolean persistant = false;
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        if (uiObject.persistent(indexPhoto).size() > 0) persistant = true;
        if (persistant == state) {
            MyLogger.log.info("Item category or effect '"+indexPhoto+"' found result: "+ state);
            return Android.app.photoLab.pictures.waitToLoad();
        }
        else throw new AssertionError("Check persistent label for Photo '"+indexPhoto+"' found result: "+state);
    }

    public AboutCombo aboutThisCombo(){
        try{
            MyLogger.log.info("Open About This Combo page.");
            uiObject.moreOptions().tap();
            uiObject.option("About this combo").waitToAppear(5).tap();
            return Android.app.photoLab.aboutCombo.waitToLoad();
        }catch (AssertionError e) {
            throw new AssertionError("About This Combo page failed to open.");
        }
    }

    public Pictures reportAbuse(){
        try{
            MyLogger.log.info("Open Report abuse page.");
            uiObject.moreOptions().tap();
            uiObject.option("Report abuse").waitToAppear(5).tap();
            return Android.app.photoLab.pictures.waitToLoad();
        }catch (AssertionError e) {
            throw new AssertionError("Report abuse page failed to open.");
        }
    }

}
