package api.apps.Fabby;

import api.android.Android;
import common.Toasts;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static api.android.Android.driver;

/**
 * Created by User on 5/16/2017.
 */
public class Fabby {

    public void runToast () throws InterruptedException {
        Toasts toast = new Toasts();
        if(toast.getToastMessage().toString().contains("Image saved"))
        {
            System.out.println("Тост показался");
        }

        else
        {
            System.out.println("Тост не показался");
        }

    }

    public void runTest () {

        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

        System.out.println("TIMESTAMP START: " + new Date().getTime());


        Runtime rt = Runtime.getRuntime();

        String sPathToF = "D:\\Base";
        String sFileName = "";
        String sDistFolderPath = "";

        File folder = new File(sPathToF);
        File[] listOfFiles = folder.listFiles();


        for (int i = 0; i < listOfFiles.length; i++) {

            Android.adb.deleteFolderFiles("/storage/sdcard0/Pictures/Fabby");
            if (listOfFiles[i].isFile()) {

                sFileName = listOfFiles[i].getName();
                String sstrText = "adb push D:\\Base\\" + listOfFiles[i].getName() + " /storage/sdcard0/Pictures/temp/";
                System.out.println("TEXT: " + sstrText);
                Android.adb.pushFile("D:\\Base\\" + listOfFiles[i].getName(), "/storage/sdcard0/Pictures/temp/");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Android.adb.updateBroadcastMediaMounted();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Android.adb.openAppsActivity("com.fabby.android", "com.aimatter.apps.fabby.ui.MainActivity");
                while (!(driver.findElementsByAndroidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.fabby.android:id/galleryImageView\")").size() > 0)) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                driver.findElementByAndroidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.fabby.android:id/galleryImageView\")").click();

                driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

                if (driver.findElementsByAndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").text(\"Изображения\")").size() > 0) {
                    driver.findElementByAndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").text(\"Изображения\")").click();
                }

                driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

                if (driver.findElementsByAndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").text(\"Выбор изображения\")").size() > 0) {
                    Dimension size;
                    size = driver.manage().window().getSize();
                    int x = (int) (size.width * 0.25);
                    int y = (int) (size.height * 0.33);
                    driver.tap(1, x, y, 400);
                }

                driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                while (driver.findElementsByAndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").text(\"ДЕЛАЕМ ПОТРЯСАЮЩИЕ ВЕЩИ\")").size() > 0) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

                if (scrollTo(driver)) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    driver.findElementByAndroidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.fabby.android:id/nextImageView\")").click();
                }

                driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                while (driver.findElementsByAndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").text(\"ДЕЛАЕМ ПОТРЯСАЮЩИЕ ВЕЩИ\")").size() > 0) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

                if (driver.findElementsByAndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").text(\"Сохранить\")").size() > 0) {
                    driver.findElementByAndroidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.fabby.android:id/saveButton\")").click();
                }

                driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                if (driver.findElementsByAndroidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.fabby.android:id/cancelButton\")").size() > 0) {
                    driver.findElementByAndroidUIAutomator("new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.fabby.android:id/cancelButton\")").click();
                }

                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                driver.pressKeyCode(AndroidKeyCode.BACK);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                driver.pressKeyCode(AndroidKeyCode.BACK);

                String fileNameWithOutExt = FilenameUtils.removeExtension(sFileName);
                sDistFolderPath = "D:\\Base_Res_Fabby\\" + fileNameWithOutExt;
                File theDir = new File(sDistFolderPath);
                File fileStart = new File("D:\\Base\\" + sFileName);
                fileStart.delete();

                // if the directory does not exist, create it
                if (!theDir.exists()) {
                    System.out.println("Creating directory: " + theDir.getName());
                    theDir.mkdir();
                }
                Android.adb.deleteFolderFiles("/storage/sdcard0/Pictures/temp");
                Android.adb.pullFile("/storage/sdcard0/Pictures/Fabby", sDistFolderPath);

                driver.pressKeyCode(AndroidKeyCode.HOME);

            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }
        System.out.println("TIMESTAMP END: " + new Date().getTime());
        driver.quit();
    }

    public static Boolean scrollTo(AndroidDriver driver) {

        Point location;
        Dimension size, size2;
        Boolean bflag = false;
        Boolean beofflag = false;
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        size = driver.manage().window().getSize();
        int startx = (int) (size.width * 0.9);
        int endx = (int) (size.width * 0.1);

        location = driver.findElementByAndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.fabby.android:id/tab\")").getLocation();
        size2 = driver.findElementByAndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.fabby.android:id/tab\")").getSize();
        int ypos = (int) (location.getY() + size2.getHeight() / 2);
        while (!(driver.findElementsByAndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").text(\"МОЯ ТЕМА\")").size() > 0)) {
            driver.swipe(startx, ypos, endx, ypos, 500);
        }
        driver.findElementByAndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").text(\"МОЯ ТЕМА\")").click();
        return true;
    }
}

