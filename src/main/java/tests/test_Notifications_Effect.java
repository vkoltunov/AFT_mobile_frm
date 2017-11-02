package tests;

import api.android.Android;
import api.apps.PhotoLab.PhotoLab;
import core.managers.TestManager;
import core.utils.Config;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

/**
 * Created by User on 6/26/2017.
 */
public class test_Notifications_Effect extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    //@org.testng.annotations.BeforeClass
    //public static void beforeClass(){
    //    photolab.open();
    //}

    @BeforeTest
    public void before(){
        testInfo.suite("test_Notifications_Effect");
    }

    //===== Notification work =====

    @org.testng.annotations.Test
    @Parameters({"AppType"})
    public void test16(@Optional String appType){
        testInfo.id("test16").suite("test_Notifications_Effect").name("Check notification work.");
        photolab.setAppType(appType);

        photolab.custom.loadPictureToDevice(Config.APP_DATA_DIR+"\\photoLab\\source\\t1.png");
        photolab.forceStop();
        photolab.open();
        Android.adb.clearLogBuffer();
        photolab.main.waitToLoad();
        photolab.custom.sendNotification("navigate=fx&id=1048");
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
        photolab.custom.compareFiles(Config.APP_DATA_DIR+"\\photoLab\\result", Config.APP_DATA_DIR+"\\photoLab\\result\\NotificationCheck_"+Android.app.photoLab.appType+".jpg");
    }

    @AfterTest
    public void after(){
        photolab.custom.clearFolderData(Config.APP_DATA_DIR+"\\photoLab\\result");
    }
}
