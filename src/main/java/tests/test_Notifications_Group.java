package tests;

import api.android.Android;
import api.apps.PhotoLab.PhotoLab;
import core.managers.TestManager;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

/**
 * Created by User on 6/26/2017.
 */
public class test_Notifications_Group extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    //@org.testng.annotations.BeforeClass
    //public static void beforeClass(){
    //    photolab.open();
    //}

    @BeforeTest
    public void before(){
        testInfo.suite("test_Notifications_Group");
    }

    //===== Notification work =====

    @org.testng.annotations.Test
    @Parameters({"AppType"})
    public void test16(@Optional String appType) {
        testInfo.id("test16").suite("test_Notifications_Group").name("Check notification work.");
        photolab.setAppType(appType);

        photolab.forceStop();
        photolab.open();
        photolab.main.waitToLoad();
        photolab.custom.sendNotification("navigate=group&id=22");
        photolab.notifications.waitToLoad();
        photolab.notifications.tapNotification();
        photolab.categories.categoryTitle_check("New Reality");
        photolab.custom.pressBack();
        photolab.main.waitToLoad();
    }
}
