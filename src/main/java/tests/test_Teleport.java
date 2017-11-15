package tests;

import api.android.Android;
import api.apps.PhotoLab.PhotoLab;
import api.apps.Teleport.Teleport;
import core.managers.TestManager;
import org.testng.annotations.BeforeTest;

/**
 * Created by User on 6/26/2017.
 */
public class test_Teleport extends TestManager {

    private static Teleport teleport = Android.app.teleport;

    @BeforeTest
    public void before(){
        testInfo.suite("test_Teleport");
    }

    //===== Version check =====

    @org.testng.annotations.Test
    public void test5(){
        testInfo.id("test5").suite("test_Teleport").name("Generate DataPool.");
        Android.app.teleport.runTest();
    }
}
