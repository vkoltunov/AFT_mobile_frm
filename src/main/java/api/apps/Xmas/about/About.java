package api.apps.Xmas.about;

import api.android.Android;
import api.interfaces.Activity;
import core.MyLogger;

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
            return Android.app.xmas.about;
        }catch (AssertionError e) {
            throw new AssertionError("Menu bar failed to load/open");
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
        if (appVersion.contains(version)){
            MyLogger.log.info("Versions is equals.");
            return true;
        } else {
            MyLogger.log.error("Config version is '"+version+"' but App config version is '"+appVersion+"'.");
            throw new AssertionError("Versions isn't equals.");
        }
    }

}
