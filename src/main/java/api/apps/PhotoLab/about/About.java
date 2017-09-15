package api.apps.PhotoLab.about;

import api.android.Android;
import api.interfaces.Activity;
import core.MyLogger;
import core.UiSelector;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static api.android.Android.driver;

/**
 * Created by User on 4/28/2017.
 */
public class About implements Activity{

    public AboutUiObjects uiObject = new AboutUiObjects();

    @Override
    public About waitToLoad(){
        try{
            MyLogger.log.info("Waiting for Menu bar");
            uiObject.about().waitToAppear(10);
            return Android.app.photoLab.about;
        }catch (AssertionError e) {
            throw new AssertionError("Menu bar failed to load/open");
        }
    }

    public void tapContactUs(){
        try{
            MyLogger.log.info("Tap to ContactUs button.");
            uiObject.contactUs().tap();
        }catch (AssertionError e) {
            throw new AssertionError("ContactUs button failed to tap/load");
        }
    }

    public void tapFacebook(){
        try{
            MyLogger.log.info("Tap to Facebook icon.");
            uiObject.follow_facebook().tap();
        }catch (AssertionError e) {
            throw new AssertionError("Facebook icon failed to tap/load");
        }
    }

    public void tapInstagram(){
        try{
            MyLogger.log.info("Tap to Instagram icon.");
            uiObject.follow_instagram().tap();
        }catch (AssertionError e) {
            throw new AssertionError("Instagram icon failed to tap/load");
        }
    }

    public void tapTwitter(){
        try{
            MyLogger.log.info("Tap to Twitter icon.");
            uiObject.follow_twitter().tap();
        }catch (AssertionError e) {
            throw new AssertionError("Twitter icon failed to tap/load");
        }
    }

    public String getVersion(){
        try{
            MyLogger.log.info("Get application version.");
            return uiObject.version().getText().replace("Version: ", "");
        }catch (AssertionError e) {
            throw new AssertionError("Application version failed to get.");
        }
    }

    public Boolean checkVersion(String version){
        try{
            MyLogger.log.info("Check version app with etalon '"+version+"'");
            return getVersion().contains(version);
        }catch (AssertionError e) {
            throw new AssertionError("Application version failed to get.");
        }
    }

    public Boolean checkConfigVersion(String version){

        MyLogger.log.info("Check Config version '"+version+"' with App config version.");
        String appVersion = uiObject.version().getText().replace("Config version: ", "");
        MyLogger.log.info("Versions is equal : "+appVersion);
        if (appVersion.equals(version)){
            MyLogger.log.info("Versions is equals.");
            return true;
        } else {
            MyLogger.log.error("Config version is '"+version+"' but App config version is '"+appVersion+"'.");
            throw new AssertionError("Versions isn't equals.");
        }
    }

}
