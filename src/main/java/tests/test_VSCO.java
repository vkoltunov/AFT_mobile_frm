package tests;

import api.android.Android;
import api.apps.PhotoLab.PhotoLab;
import core.managers.TestManager;
import org.testng.annotations.BeforeTest;

/**
 * Created by User on 6/26/2017.
 */
public class test_VSCO extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    @BeforeTest
    public void before(){
        testInfo.suite("test_VSCO");
    }

    //===== Version check =====

    @org.testng.annotations.Test
    public void test5(){
        testInfo.id("test5").suite("test_VSCO").name("Generate DataPool.");
        Android.app.vsco.runTest();
    }
}
