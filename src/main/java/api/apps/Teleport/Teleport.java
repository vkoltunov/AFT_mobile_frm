package api.apps.Teleport;

import api.android.Android;
import core.MyLogger;
import core.utils.Common;
import io.appium.java_client.android.AndroidKeyCode;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static api.android.Android.driver;

/**
 * Created by User on 10/9/2017.
 */
public class Teleport {

    public TeleportUiObjects uiObject = new TeleportUiObjects();

    public void runTest () {
        try {

            MyLogger.log.info("Go Teleport test.");
            Android.adb.forceStopApp("com.teleportfuturetechnologies.teleport");
            Android.driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
            Android.adb.openAppsActivity("com.teleportfuturetechnologies.teleport", "com.teleportfuturetechnologies.teleport.camera.CameraActivity");
            Android.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

            Android.adb.deleteFolderFiles("/storage/sdcard0/Pictures/temp");

            Runtime rt = Runtime.getRuntime();

            String sPathToF = "D:\\Datapool\\Teleport\\source";
            String sFltrToNeed = "";
            String sFileName = "";
            String sDistFolderPath = "";

            File folder = new File(sPathToF);
            File[] listOfFiles = folder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {

                //String batLvl = Common.getBatteryLvl();
                //int lvl = Integer.parseInt(batLvl);

                //if (lvl < 7){
                    //Android.adb.forceStopApp("com.teleportfuturetechnologies.teleport");
                    //MyLogger.log.info("Battery LVL is bad. Waiting...");
                    //try {
                    //    Thread.sleep(7200000);
                    //} catch (InterruptedException e) {
                    //    e.printStackTrace();
                    //}
                    //Android.adb.openAppsActivity("com.teleportfuturetechnologies.teleport", "com.teleportfuturetechnologies.teleport.camera.CameraActivity");
                //} else MyLogger.log.info("Battery LVL = "+lvl);

                Android.adb.deleteFolderFiles("/storage/sdcard0/Pictures/Teleport");
                if (listOfFiles[i].isFile()) {

                    sFileName = listOfFiles[i].getName();
                    //String sstrText = "adb push D:\\BaseIG\\" + listOfFiles[i].getName() + " /storage/sdcard/Pictures/temp/";
                    //System.out.println("TEXT: " + sstrText);
                    Android.adb.pushFile(sPathToF + "\\" + listOfFiles[i].getName(), "/storage/sdcard0/Pictures/temp/");
                    Android.adb.updateBroadcastMediaMounted();
                    try {
                        Thread.sleep(7000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("File " + listOfFiles[i].getName());

                    for (int j = 0; j < 1; j++) {

                        switch (j) {
                            case 0:
                                sFltrToNeed = "Green";
                                break;
                            default:
                                sFltrToNeed = "Invalid filter name";
                                break;
                        }

                        try {
                            Thread.sleep(250);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        Boolean flag = false;

                        Android.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
                        while ((uiObject.gallery().size() > 0) && (!flag)) {
                            uiObject.gallery().tap();
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            Android.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                            if (uiObject.openFrom().size()>0){
                                uiObject.recent().tap();
                            }
                            Android.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
                            uiObject.pic().tap();
                            if (uiObject.editPhoto().size() > 0) flag = true;
                            //else Android.adb.updateBroadcastMediaMounted();
                        }

                        Android.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                        //if (uiObject.editPhoto().size() > 0){
                        //    Dimension size = uiObject.image().getSize();
                        //    int xpos = (int) (size.width * 0.5);

                        //    Point location = uiObject.image().getLocation();
                        //    int startY = (int) (location.getY() + size.getHeight() * 0.15);
                        //    int endY = (int) (location.getY() + size.getHeight() * 0.85);
                        //    driver.swipe(xpos, startY, xpos, endY, 800);
                        //}
                        uiObject.done().tap();

                        int counter = 0;
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        while((!(uiObject.filtersTopScroll().size() > 0)) && (counter < 5)){
                            counter = counter + 1;
                            MyLogger.log.info("Counter : "+counter);
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        //uiObject.filterNameTop("Background").tap();
                        //try {
                        //    Thread.sleep(200);
                        //} catch (InterruptedException e) {
                        //    e.printStackTrace();
                        //}
                        //uiObject.filterNameTop("Skin Color").tap();

                        Boolean bflag = false;
                        Android.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                        while ((!bflag)) {
                            if (uiObject.filterNameBottom(sFltrToNeed).size() > 0){
                                uiObject.filterNameBottom(sFltrToNeed).tap();
                                bflag = true;
                            } else {
                                //sFltrLast = sCurrFltr;
                                Dimension size = driver.manage().window().getSize();
                                int startx = (int) (size.width * 0.9);
                                int endx = (int) (size.width * 0.1);

                                Point location = uiObject.filtersBottomScroll().getLocation();
                                Dimension size2 = uiObject.filtersBottomScroll().getSize();
                                int ypos = (int) (location.getY() + size2.getHeight() / 2);
                                driver.swipe(startx, ypos, endx, ypos, 1000);
                            }
                        }
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                        uiObject.openMenu().tap();
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        uiObject.save().tap();
                        try {
                            Thread.sleep(400);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        pressBack();
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    String fileNameWithOutExt = FilenameUtils.removeExtension(sFileName);
                    sDistFolderPath = "D:\\Datapool\\Teleport\\result\\" + fileNameWithOutExt;
                    File theDir = new File(sDistFolderPath);
                    File fileStart = new File("D:\\Datapool\\Teleport\\source\\" + sFileName);
                    fileStart.delete();

                    // if the directory does not exist, create it
                    if (!theDir.exists()) {
                        System.out.println("Creating directory: " + theDir.getName());
                        theDir.mkdir();
                    }
                    Android.adb.deleteFolderFiles("/storage/sdcard0/Pictures/temp");
                    Android.adb.pullFile("/storage/sdcard0/Pictures/Teleport", sDistFolderPath);

                } else if (listOfFiles[i].isDirectory()) {
                    System.out.println("Directory " + listOfFiles[i].getName());
                }


            }
            driver.pressKeyCode(AndroidKeyCode.HOME);

        } catch (Exception e) {
            throw new AssertionError("Element not founded. Try again.");
        }
    }

    public void pressBack (){
        try{
            Android.driver.pressKeyCode(AndroidKeyCode.BACK);
        }catch (AssertionError e) {
            throw new AssertionError("Device BACK button failed to press");
        }
    }

}
