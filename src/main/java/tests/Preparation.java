package tests;

import api.android.Android;
import api.apps.PhotoLab.PhotoLab;
import core.managers.TestManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by User on 6/27/2017.
 */
public class Preparation extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    @BeforeClass
    public static void beforeClass() {
        photolab.open();
    }

    @Before
    public void before() {
        testInfo.suite("Preparation");
    }

//===== Clear data. Install application build for device.  =====

    //@Test
    //public void insta_test(){
    //    Android.app.instagram.runTest();
    //}

    @Test
    public void test0(){
        testInfo.id("test0").suite("Preparation").name("Clear application data. Uninstall current application build. Install new build.");
        photolab.forceStop();
        photolab.clearData();
        photolab.uninstallApp();
        photolab.installApp("D:\\AFT\\Build\\PhotoLab-PlayFree-v2.1.44-1182-debug.apk");
        photolab.open();
        photolab.main.waitToLoad();
    }
}
