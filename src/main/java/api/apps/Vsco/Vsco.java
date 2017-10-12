package api.apps.Vsco;

import api.android.Android;
import core.MyLogger;
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
public class Vsco {

    public VscoUiObjects uiObject = new VscoUiObjects();

    public void runTest () {
        try {

            MyLogger.log.info("Go VSCO test.");
            Android.adb.forceStopApp("com.vsco.cam");
            Android.driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
            Android.adb.openAppsActivity("com.vsco.cam", "com.vsco.cam.gallery.ImageGridActivity");

            uiObject.library().tap();

            Android.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (uiObject.photo().size() > 0){
                uiObject.photo().tap();
                uiObject.menuMore().tap();
                uiObject.remove().tap();
                uiObject.accept().tap();
            }
            Android.adb.deleteFolderFiles("/storage/sdcard/Pictures/temp");

            Runtime rt = Runtime.getRuntime();

            String sPathToF = "D:\\BaseIG";
            String sFltrToNeed = "";
            String sFileName = "";
            String sDistFolderPath = "";

            File folder = new File(sPathToF);
            File[] listOfFiles = folder.listFiles();

            for (int i = 0; i < listOfFiles.length; i++) {

                Android.adb.deleteFolderFiles("/storage/sdcard/Pictures/VSCO");
                if (listOfFiles[i].isFile()) {

                    sFileName = listOfFiles[i].getName();
                    //String sstrText = "adb push D:\\BaseIG\\" + listOfFiles[i].getName() + " /storage/sdcard/Pictures/temp/";
                    //System.out.println("TEXT: " + sstrText);
                    Android.adb.pushFile("D:\\BaseIG\\" + listOfFiles[i].getName(), "/storage/sdcard/Pictures/temp/");
                    Android.adb.updateBroadcastMediaMounted();

                    System.out.println("File " + listOfFiles[i].getName());

                    for (int j = 0; j < 1; j++) {

                        switch (j) {
                            case 0:
                                sFltrToNeed = "HB1";
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

                        uiObject.plus().tap();
                        uiObject.all().tap();
                        uiObject.firsPhoto().tap();
                        uiObject.right().tap();

                        Boolean flag = false;
                        Integer counter = 0;

                        Android.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                        while ((!flag) && (counter < 6)){

                            counter = counter+1;
                            if ((uiObject.dialogText().size() > 0)){
                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            } else flag = true;
                        }
                        Android.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        //MyLogger.log.info("Count of Image : "+uiObject.photo().size());

                        uiObject.photo().tap();
                        uiObject.filters().tap();

                        //driver.findElementByXPath("//android.support.v7.widget.RecyclerView//android.widget.FrameLayout[@index='2']/android.widget.ImageView").click();

                        //int index_count = driver.findElementsByAndroidUIAutomator("new UiSelector().class(\"android.widget.TextView\").resourceId(\"com.vsco.cam:id/preset_text\")").size();
                        Boolean bflag = false;
                        //String sFltrLast = "X1";
                        //String sCurrFltr = "";
//                    System.out.println("Count =  " + Integer.toString(index_count));
                        int curind = 1;
                        Android.driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
                        while ((!bflag)) {
                            if (uiObject.filterName(sFltrToNeed).size() > 0){
                                uiObject.filterName(sFltrToNeed).tap();
                                bflag = true;
                            } else {
                                //sFltrLast = sCurrFltr;
                                Dimension size = driver.manage().window().getSize();
                                int startx = (int) (size.width * 0.9);
                                int endx = (int) (size.width * 0.1);

                                Point location = uiObject.firstFilter().getLocation();
                                Dimension size2 = uiObject.firstFilter().getSize();
                                int ypos = (int) (location.getY() + size2.getHeight() / 2);
                                driver.swipe(startx, ypos, endx, ypos, 1500);
                                //sCurrFltr = uiObject.firstFilter().getText();
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                        uiObject.show().tap();
                        uiObject.done().tap();
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        uiObject.menuMore().tap();
                        uiObject.saveGalery().tap();

                        flag = false;
                        counter = 0;
                        Android.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                        while ((!flag) && (counter < 6)){

                            counter = counter+1;
                            if ((uiObject.dialogText().size() > 0)){
                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            } else flag = true;
                        }
                        Android.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        uiObject.photo().tap();
                        uiObject.menuMore().tap();
                        uiObject.remove().tap();
                        uiObject.accept().tap();
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
                Android.adb.pullFile("/storage/sdcard/Pictures/VSCO", sDistFolderPath);

            }
            driver.pressKeyCode(AndroidKeyCode.HOME);
        } catch (Exception e) {
            throw new AssertionError("Element not founded. Try again.");
        }
    }
}
