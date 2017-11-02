package tests;

import api.android.Android;
import api.apps.PhotoLab.PhotoLab;
import core.managers.TestManager;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
//import org.testng.annotations.*;

/**
 * Created by User on 4/5/2017.
 */
public class test_Config_Categories extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

//    @org.testng.annotations.BeforeClass
//    public static void beforeClass(){
//        photolab.open();
//    }

    @BeforeTest
    public void before(){
        testInfo.suite("test_Config_Categories");
    }

//===== Categories =====

    @org.testng.annotations.Test
    @Parameters({"Config", "Language", "AppType"})
    public void test32(String configURL, String lang, @Optional String appType) throws ParseException {
        testInfo.id("test2").suite("test_Config_Categories").name("Check Categories list for '"+lang+"' language.");
        photolab.setAppType(appType);

        photolab.custom.changeLocalization(lang);
        photolab.forceStop();
        photolab.open();
        photolab.main.waitToLoad();
        photolab.config.checkTabContent(configURL, lang, "categories");
        photolab.custom.changeLocalization("en");
    }
}
