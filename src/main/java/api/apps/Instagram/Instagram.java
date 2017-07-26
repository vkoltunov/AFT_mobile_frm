package api.apps.Instagram;

import api.android.Android;
import core.MyLogger;
import core.managers.DriverManager;
import io.appium.java_client.android.AndroidKeyCode;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.Dimension;

import java.io.File;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static api.android.Android.driver;

/**
 * Created by User on 5/16/2017.
 */
public class Instagram {

    public void runTest () {
        try {
            MyLogger.log.info("Go Insta test.");
            Android.adb.forceStopApp("com.instagram.android");
            Android.driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
            Android.adb.openAppsActivity("com.instagram.android", "com.instagram.android.activity.MainTabActivity");

            Runtime rt = Runtime.getRuntime();

            String sPathToF = "D:\\BaseIG";
            String sFltrToNeed = "";
            String sFileName = "";
            String sDistFolderPath = "";

            File folder = new File(sPathToF);
            File[] listOfFiles = folder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {

                Android.adb.deleteFolderFiles("/storage/sdcard/Pictures/Instagram");
                if (listOfFiles[i].isFile()) {

                    sFileName = listOfFiles[i].getName();
                    //String sstrText = "adb push D:\\BaseIG\\" + listOfFiles[i].getName() + " /storage/sdcard/Pictures/temp/";
                    //System.out.println("TEXT: " + sstrText);
                    Android.adb.pushFile("D:\\BaseIG\\" + listOfFiles[i].getName(), "/storage/sdcard/Pictures/temp/");
                    Android.adb.updateBroadcastMediaMounted();

                    System.out.println("File " + listOfFiles[i].getName());

                    //driver.pressKeyCode(AndroidKeyCode.HOME);
                    //try {
                    //    Thread.sleep(2000);
                    //} catch (InterruptedException e) {
                    //    e.printStackTrace();
                    //}
                    //driver.findElementByAndroidUIAutomator("new UiSelector().description(\"Apps\")").click();
                    //try {
                    //    Thread.sleep(100);
                    //} catch (InterruptedException e) {
                    //    e.printStackTrace();
                    //}
                    //driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Instagram\")").click();


                    for (int j = 0; j < 2; j++) {

                        switch (j) {
                            case 0:
                                sFltrToNeed = "Ginza";
                                break;
                            case 1:
                                sFltrToNeed = "Helena";
                                break;
                            default:
                                sFltrToNeed = "Invalid filter name";
                                break;
                        }

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        driver.findElementByAndroidUIAutomator("new UiSelector().index(2).description(\"Camera\")").click();
                        //try {
                        //    Thread.sleep(50);
                        //} catch (InterruptedException e) {
                        //    e.printStackTrace();
                        //}
                        //driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
                        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"gallery\")").click();
                        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Gallery\")").click();
//                    Thread.sleep(300);
                        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.instagram.android:id/text_view\").text(\"temp\")").click();
//                    Thread.sleep(300);
                        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

                        if (driver.findElementsByAndroidUIAutomator("new UiSelector().resourceId(\"com.instagram.android:id/croptype_toggle_button\").description(\"Toggle square\")").size() > 0) {
                            driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.instagram.android:id/croptype_toggle_button\").description(\"Toggle square\")").click();
                        }
                        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.instagram.android:id/next_button_textview\").text(\"Next\")").click();

                        int index_count = driver.findElementsByClassName("android.widget.RadioButton").size();
                        Boolean bflag = false;
                        String sFltrLast = "Manage";
                        String sCurrFltr = "";
//                    System.out.println("Count =  " + Integer.toString(index_count));
                        int curind = 1;

                        while ((!bflag) || (sCurrFltr == sFltrLast)) {
                            for (int k = 0; k < index_count - 1; k++) {
                                String str = "//*[@class='android.widget.RadioButton' and @index='" + curind + "']";
//                            System.out.println("XPATH =  " + str);
                                sCurrFltr = driver.findElementByXPath(str).getAttribute("name");
//                            System.out.println("Current Desc =  " + sCurrFltr);
                                if (sCurrFltr.equals(sFltrToNeed)) {
                                    driver.findElementByXPath("//*[@class='android.widget.RadioButton' and @index='" + curind + "']").click();
                                    bflag = true;
                                    break;
                                }
                                curind++;
                            }
                            if (!bflag) {
                                Dimension size;
                                size = driver.manage().window().getSize();
//                            System.out.println(size);

                                //Find swipe start and end point from screen's with and height.
                                //Find startx point which is at right side of screen.
                                int startx = (int) (size.width * 0.95);
                                //Find endx point which is at left side of screen.
                                int endx = (int) (size.width * 0.05);
                                //Find vertical point where you wants to swipe. It is in middle of screen height.
                                int starty = (int) (size.height / 1.2);
//                            System.out.println("startx = " + startx + " ,endx = " + endx + " , starty = " + starty);

                                //Swipe from Right to Left.
                                driver.swipe(startx, starty, endx, starty, 2100);
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.instagram.android:id/next_button_textview\").text(\"Next\")").click();
                        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.instagram.android:id/next_button_textview\").text(\"Share\")").click();
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.instagram.android:id/row_pending_media_options_button\").index(4)").click();
                        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.instagram.android:id/row_simple_text_textview\").text(\"Discard Post\")").click();
                        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.instagram.android:id/button_positive\").text(\"Discard\")").click();
                    }
                } else if (listOfFiles[i].isDirectory()) {
                    System.out.println("Directory " + listOfFiles[i].getName());
                }


                String fileNameWithOutExt = FilenameUtils.removeExtension(sFileName);
                sDistFolderPath = "D:\\Base_ResIG\\" + fileNameWithOutExt;
                File theDir = new File(sDistFolderPath);
                File fileStart = new File("D:\\BaseIG\\" + sFileName);
                fileStart.delete();

                // if the directory does not exist, create it
                if (!theDir.exists()) {
                    System.out.println("Creating directory: " + theDir.getName());
                    theDir.mkdir();
                }
                Android.adb.deleteFolderFiles("/storage/sdcard/Pictures/temp");
                Android.adb.pullFile("/storage/sdcard/Pictures/Instagram", sDistFolderPath);

            }
            driver.pressKeyCode(AndroidKeyCode.HOME);
        } catch (Exception e) {
            throw new AssertionError("Element not founded. Try again.");
        }
    }
}
