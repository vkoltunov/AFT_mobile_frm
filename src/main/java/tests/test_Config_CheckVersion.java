package tests;

import api.android.Android;
import api.apps.PhotoLab.PhotoLab;
import core.managers.TestManager;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
//import org.testng.annotations.*;

/**
 * Created by User on 4/5/2017.
 */
public class test_Config_CheckVersion extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

//    @org.testng.annotations.BeforeClass
//    public static void beforeClass(){
//        photolab.open();
//    }

    @BeforeTest
    public void before(){
        testInfo.suite("test_Config_CheckVersion");
    }

//===== Categories =====

    @org.testng.annotations.Test
    @Parameters({"Config"})
    public void test31(String configURL) throws ParseException {
        testInfo.id("test1").suite("test_Config_CheckVersion").name("Check Config version in Application.");
        photolab.forceStop();
        photolab.open();
        photolab.main.waitToLoad();
        photolab.menu.open();
        photolab.menu.tapAbout();
        photolab.about.waitToLoad();
        photolab.about.checkConfigVersion(photolab.config.getConfigVersion(configURL));
    }
}
