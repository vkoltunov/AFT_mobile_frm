package tests;

import api.android.Android;
import api.apps.Fabby.Fabby;
import api.apps.PhotoLab.PhotoLab;
import core.managers.TestManager;
import org.testng.annotations.BeforeTest;

/**
 * Created by User on 6/26/2017.
 */
public class test_Fabby extends TestManager {

    private static Fabby fabby = Android.app.fabby;

    @BeforeTest
    public void before(){
        testInfo.suite("test_Fabby");
    }

    //===== Version check =====

    @org.testng.annotations.Test
    public void test5(){
        testInfo.id("test5").suite("test_Fabby").name("Generate DataPool.");
        Android.app.fabby.runTest();
    }
}
