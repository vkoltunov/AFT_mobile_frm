package api.apps.PhotoLab;

import api.android.Android;
import api.apps.PhotoLab.custom.CustomUiObjects;
import api.apps.PhotoLab.pictures.Pictures;
import api.apps.PhotoLab.pictures.PicturesUiObjects;
import core.MyLogger;
import core.UiObject;
import core.UiSelector;
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

    public void loadPictureToDevice(String pathToPicture) {
        MyLogger.log.info("Load Picture '" +pathToPicture+ "' to device." );

        Android.adb.deleteFolderFiles("/storage/"+card+"/Pictures/PhotoLab");
        Android.adb.createFolder("/storage/"+card+"/Pictures/Download");
        Android.adb.pushFile(pathToPicture, "/storage/"+card+"/Pictures/Download/");
        Android.adb.updateBroadcastMediaMounted();
    }

    public void loadPicsToDeviceFromFolder(String pathToFolder) {
        MyLogger.log.info("Load Pictures from folder '" +pathToFolder+ "' to device." );

        Android.adb.deleteFolderFiles("/storage/"+card+"/Pictures/PhotoLab");
        File folder = new File(pathToFolder);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) Android.adb.pushFile(listOfFiles[i].getPath(), "/storage/"+card+"/Pictures/Download/");
        }
        Android.adb.updateBroadcastMediaMounted();
    }

    public void moveResPictureToPC(String pathToFolder) {
        MyLogger.log.info("Move result photo from device to PC folder '" +pathToFolder+ "'." );

        //String fileNameWithOutExt = FilenameUtils.removeExtension(picName);
        //String sDistFolderPath = pathToFolder + "\\" + fileNameWithOutExt;
        File theDir = new File(pathToFolder);
        //File fileStart = new File("D:\\Base\\" + picName);
        //fileStart.delete();
        //File theDir = new File(pathToFolder);

        // if the directory does not exist, create it
        if (!theDir.exists()) {
            MyLogger.log.info("Creating directory: " + theDir.getName());
            theDir.mkdir();
        }

        Android.adb.deleteFolderFiles("/storage/"+card+"/Pictures/Download");
        //Android.adb.pullFile("/storage/sdcard/Pictures/PhotoLab", pathToFolder);
        Android.adb.pullFile("/storage/"+card+"/Pictures/PhotoLab", pathToFolder);
    }

    public void compareFiles (String sourcePath, String etalonPath){

        MyLogger.log.info("Compare res files with source." );

        Boolean bflag = false;
        String sDestFolderPath;

        File fileEtalon = new File(etalonPath);
        sDestFolderPath = sourcePath + "\\PhotoLab";
        File folder = new File(sDestFolderPath);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) bflag = Common.compareImage(fileEtalon, listOfFiles[i]);
        }
        if (bflag) MyLogger.log.info("Compare files result : TRUE." );
        else throw new AssertionError("Compare files result : FALSE.");
    }

    public void clearFolderData (String resultFolder){
        MyLogger.log.info("Delete PhotoLab folder with results.");
        String destFolser = resultFolder + "\\PhotoLab";
        File theDir = new File(destFolser);
        try{
            if (theDir.exists()) FileUtils.deleteDirectory(theDir);
        }catch (IOException e) {
            throw new AssertionError("Delete PhotoLab folder failed.");
        }
    }

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

    public void sendNotification (String action){
        try{
            MyLogger.log.info("Sent Notification to PhotoLab.1");
            PushNotificationTools.sentTestData(Android.driver, action);
            MyLogger.log.info("Sent Notification to PhotoLab.2");
            Android.driver.openNotifications();
            MyLogger.log.info("Sent Notification to PhotoLab.3");
        }catch (AssertionError e ) {
            throw new AssertionError("Send notification to PhotoLab failed.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void workWithSponsoredPage (String seeAdvertismentOption){
        try{
            MyLogger.log.info("Work with Sponsored advertisement.");
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            if (!(Android.app.photoLab.save.uiObject.saveAndShare().size()>0)){
                Android.app.photoLab.dialogs.waitToLoad();
                Android.app.photoLab.dialogs.checkDialogTitle("Only PRO users can remove the watermark.");
                Android.app.photoLab.dialogs.checkDialogMessage("Do you want to remove it just this once by watching a short sponsored video?");
                if (seeAdvertismentOption.contains("Yes") || seeAdvertismentOption.contains("yes")) {
                    Android.app.photoLab.dialogs.tapYes();
                    Android.app.photoLab.advertisement.waitToLoad();
                    Android.app.photoLab.advertisement.tapClose();
                    Android.app.photoLab.save.waitToLoad();
                    Android.app.photoLab.save.selectDownload();

                } else {
                    Android.app.photoLab.dialogs.tapNo();
                    Android.app.photoLab.save.waitToLoad();
                }
            } else MyLogger.log.info("Dialog about sponsored link isn't open.");
        }catch (AssertionError e) {
            throw new AssertionError("Failed to work with Sponsored advertisement");
        }
    }

    public Boolean checkVideoAdLoaded(){
        Boolean flag = false;
        LogEntries logEntries = Android.driver.manage().logs().get("logcat");
        Iterator<LogEntry> iterator = logEntries.iterator();

        while (iterator.hasNext()) {
            LogEntry entry =  iterator.next();
            if (entry.getMessage() != null && entry.getMessage().contains("onRewardedVideoAdLoaded")) {
                flag = true;
            }
        }
        return flag;
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

}