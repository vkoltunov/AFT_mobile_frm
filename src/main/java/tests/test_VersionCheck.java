package tests;

import api.android.Android;
import api.apps.PhotoLab.PhotoLab;
import core.managers.TestManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

/**
 * Created by User on 6/26/2017.
 */
public class test_VersionCheck extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    @org.testng.annotations.BeforeClass
    public static void beforeClass(){
        photolab.open();
    }

    @BeforeTest
    public void before(){
        testInfo.suite("test_VersionCheck");
    }

    //===== Version check =====

    @org.testng.annotations.Test
    @Parameters({"Version"})
    public void test5(String version){
        testInfo.id("test5").suite("test_VersionCheck").name("version Page check.");
        photolab.main.waitToLoad();
        photolab.menu.open();
        photolab.menu.tapAbout();
        photolab.about.waitToLoad();
        photolab.about.checkVersion(version);
        photolab.menu.open();
        photolab.menu.tapHome();
    }
}
