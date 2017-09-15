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
public class test_Config_Effects extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

//    @org.testng.annotations.BeforeClass
//    public static void beforeClass(){
//        photolab.open();
//    }

    @BeforeTest
    public void before(){
        testInfo.suite("test_Config_Effects");
    }

//===== Categories =====

    @org.testng.annotations.Test
    @Parameters({"Config", "Language"})
    public void test33(String configURL, String lang) throws ParseException {
        testInfo.id("test1").suite("test_Config_Effects").name("Check Effects for Category.");
        photolab.forceStop();
        photolab.open();
        photolab.main.waitToLoad();
        photolab.config.checkCategoryEffects(configURL, lang, "");
    }
}
