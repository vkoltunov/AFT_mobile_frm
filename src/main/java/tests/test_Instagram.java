package tests;

import api.android.Android;
import api.apps.Instagram.Instagram;
import api.apps.PhotoLab.PhotoLab;
import core.managers.TestManager;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

/**
 * Created by User on 6/26/2017.
 */
public class test_Instagram extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    @BeforeTest
    public void before(){
        testInfo.suite("test_Instagram");
    }

    //===== Version check =====

    @org.testng.annotations.Test
    public void test5(){
        testInfo.id("test5").suite("test_Instagram").name("Generate DataPool.");
        Android.app.instagram.runTest();
    }
}
