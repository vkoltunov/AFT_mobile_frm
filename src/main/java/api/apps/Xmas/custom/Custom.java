package api.apps.Xmas.custom;

import api.android.Android;
import core.MyLogger;
import core.utils.Common;
import io.appium.java_client.android.AndroidKeyCode;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import static api.android.Android.driver;
import static core.managers.DriverManager.sdCard;

/**
 * Created by User on 5/17/2017.
 */
public class Custom {

    private static String card = sdCard;
    public CustomUiObjects uiObject = new CustomUiObjects();

    public void pressBack (){
        try{
            MyLogger.log.info("Press device BACK button.");
            Android.driver.pressKeyCode(AndroidKeyCode.BACK);
        }catch (AssertionError e) {
            throw new AssertionError("Device BACK button failed to press");
        }
    }

    public void pressHome (){
        try{
            MyLogger.log.info("Press device HOME button.");
            Android.driver.pressKeyCode(AndroidKeyCode.HOME);
        }catch (AssertionError e) {
            throw new AssertionError("Device HOME button failed to press");
        }
    }

    public static void swipeTo (String from, String to) {

        Dimension size;
        size = driver.manage().window().getSize();
        int endx;
        int startx;
        int starty;
        int endy;

        switch (from) {
            case "Top":
            case "top":
                startx = (int) (size.width * 0.2);
                starty = (int) (size.height * 0.5);
                break;
            case "Bottom":
            case "bottom":
                startx = (int) (size.width * 0.85);
                starty = (int) (size.height * 0.5);
                break;
            case "Left":
            case "left":
                startx = (int) (size.width * 0.5);
                starty = (int) (size.height * 0.2);
                break;
            case "Right":
            case "right":
                startx = (int) (size.width * 0.5);
                starty = (int) (size.height * 0.85);
                break;
            default:
                startx = (int) (size.width * 0.2);
                starty = (int) (size.height * 0.5);
                break;
        }
        switch (to) {
            case "Top":
            case "top":
                endx = (int) (size.width * 0.2);
                endy = (int) (size.height * 0.5);
                break;
            case "Bottom":
            case "bottom":
                endx = (int) (size.width * 0.85);
                endy = (int) (size.height * 0.5);
                break;
            case "Left":
            case "left":
                endx = (int) (size.width * 0.5);
                endy = (int) (size.height * 0.2);
                break;
            case "Right":
            case "right":
                endx = (int) (size.width * 0.5);
                endy = (int) (size.height * 0.85);
                break;
            default:
                endx = (int) (size.width * 0.85);
                endy = (int) (size.height * 0.5);
                break;
        }
        driver.swipe(startx, starty, endx, endy, 1700);
    }

    public Boolean checkTitle(String title){
        MyLogger.log.info("Check page title is '"+title+"'.");
        if (uiObject.title().getText().contains(title)) return true;
        else throw new AssertionError("Title Object isn't exists.");
    }

    public void changeLocalization(String lang){
        MyLogger.log.info("Change localization for device to '"+lang+"'.");
        Common.changeDeviceLoc(lang);
    }

    public Boolean checkErrorMessageForLogcat(){
        Boolean flag = false;
        LogEntries logEntries = Android.driver.manage().logs().get("logcat");
        Iterator<LogEntry> iterator = logEntries.iterator();

        while (iterator.hasNext()) {
            LogEntry entry =  iterator.next();
            if (entry.getMessage() != null && entry.getMessage().contains("Permission Denial")) {
                MyLogger.log.error("Permission denial error for logcat was found.");
                new AssertionError("Permission denial error for logcat was found.");
            }
        }
        return true;
    }


}