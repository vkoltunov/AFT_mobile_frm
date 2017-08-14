package tests;

import api.android.Android;
import api.apps.PhotoLab.PhotoLab;
import core.managers.TestManager;
import core.utils.Config;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testng.annotations.BeforeTest;

/**
 * Created by User on 6/26/2017.
 */
public class test_Notifications extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    @org.testng.annotations.BeforeClass
    public static void beforeClass(){
        photolab.open();
    }

    @BeforeTest
    public void before(){
        testInfo.suite("test_Notifications");
    }

    //===== Notification work =====

    @org.testng.annotations.Test
    public void test16(){
        testInfo.id("test16").suite("Functionality").name("Check notification work.");
        photolab.custom.loadPictureToDevice(Config.APP_DATA_DIR+"\\photoLab\\source\\t1.png");
        photolab.forceStop();
        photolab.open();
        photolab.main.waitToLoad();
        photolab.custom.sendNotification();
        photolab.notifications.waitToLoad();
        photolab.notifications.tapNotification();
        photolab.pictures.waitToLoad();
        photolab.pictures.selectTab("All");
        photolab.pictures.selectPicture(1);
        photolab.property.tapDone();
        photolab.result.tapSaveToDevice();
        photolab.save.selectDownload();
        photolab.menu.open();
        photolab.menu.tapHome();
        photolab.custom.moveResPictureToPC(Config.APP_DATA_DIR+"\\photoLab\\result");
        photolab.custom.compareFiles(Config.APP_DATA_DIR+"\\photoLab\\result", Config.APP_DATA_DIR+"\\photoLab\\result\\NotificationCheck.jpg");
        photolab.custom.clearFolderData(Config.APP_DATA_DIR+"\\photoLab\\result");
    }
}
