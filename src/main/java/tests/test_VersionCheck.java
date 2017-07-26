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
public class test_VersionCheck extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    @BeforeClass
    public static void beforeClass(){
        photolab.open();
    }

    @Before
    public void before(){
        testInfo.suite("test_VersionCheck");
    }

    //===== Version check =====

    @Test
    public void test5(){
        testInfo.id("test5").suite("Functionality").name("version Page check.");
        photolab.main.waitToLoad();
        photolab.menu.open();
        photolab.menu.tapAbout();
        photolab.about.waitToLoad();
        photolab.about.checkVersion("2.1.");
        photolab.menu.open();
        photolab.menu.tapHome();
    }
}
