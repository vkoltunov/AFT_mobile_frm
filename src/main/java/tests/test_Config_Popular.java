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
public class test_Config_Popular extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

//    @org.testng.annotations.BeforeClass
//    public static void beforeClass(){
//        photolab.open();
//    }

    @BeforeTest
    public void before(){
        testInfo.suite("test_Config_Popular");
    }

//===== Categories =====

    @org.testng.annotations.Test
    @Parameters({"Config", "Language"})
    public void test34(String configURL, String lang) throws ParseException {
        testInfo.id("test2").suite("test_Config_Popular").name("Check Popular effects list.");
        photolab.forceStop();
        photolab.open();
        photolab.main.waitToLoad();
        photolab.config.checkTabContent(configURL, lang, "popular");
    }
}
