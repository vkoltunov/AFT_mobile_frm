package tests;

import api.android.Android;
import api.apps.PhotoLab.PhotoLab;
import core.managers.TestManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by User on 6/26/2017.
 */
public class test_Notifications extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    @BeforeClass
    public static void beforeClass(){
        photolab.open();
    }

    @Before
    public void before(){
        testInfo.suite("test_Notifications");
    }

    //===== Notification work =====

    @Test
    public void test16(){
        testInfo.id("test16").suite("Functionality").name("Check notification work.");
        photolab.custom.loadPictureToDevice("D:\\Base\\t1.png");
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
        photolab.custom.moveResPictureToPC("D:\\Base_Res");
        photolab.custom.compareFiles("D:\\Base_Res", "D:\\Base_Res\\NotificationCheck.jpg");
        photolab.custom.clearFolderData("D:\\Base_Res");
    }
}
