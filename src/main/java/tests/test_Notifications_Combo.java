package tests;

import api.android.Android;
import api.apps.PhotoLab.PhotoLab;
import core.managers.TestManager;
import org.testng.annotations.BeforeTest;

/**
 * Created by User on 6/26/2017.
 */
public class test_Notifications_Combo extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    @org.testng.annotations.BeforeClass
    public static void beforeClass(){
        photolab.open();
    }

    @BeforeTest
    public void before(){
        testInfo.suite("test_Notifications_Combo");
    }

    //===== Notification work =====

    @org.testng.annotations.Test
    public void test16() {
        testInfo.id("test16").suite("test_Notifications_Combo").name("Check notification work.");
        photolab.forceStop();
        photolab.open();
        photolab.main.waitToLoad();
        photolab.custom.sendNotification("navigate=combo&id=611408");
        photolab.notifications.waitToLoad();
        photolab.notifications.tapNotification();
        photolab.pictures.waitToLoad();
        //photolab.custom.checkTitle("Combo by");
        photolab.custom.pressBack();
    }
}
