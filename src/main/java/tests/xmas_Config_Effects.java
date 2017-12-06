package tests;

import api.android.Android;
import api.apps.PhotoLab.PhotoLab;
import api.apps.Xmas.Xmas;
import core.managers.TestManager;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
//import org.testng.annotations.*;

/**
 * Created by User on 4/5/2017.
 */
public class xmas_Config_Effects extends TestManager {

    private static Xmas xmas = Android.app.xmas;

//    @org.testng.annotations.BeforeClass
//    public static void beforeClass(){
//        photolab.open();
//    }

    @BeforeTest
    public void before(){
        testInfo.suite("xmas_Config_Effects");
    }

//===== Categories =====

    @org.testng.annotations.Test
    @Parameters({"Config", "Language", "AppType"})
    public void test33(String configURL, String lang, @Optional String appType) throws ParseException {
        testInfo.id("test1").suite("xmas_Config_Effects").name("Check Effects for Category.");
        xmas.setAppType(appType);

        xmas.forceStop();
        xmas.open();
        xmas.main.waitToLoad();
        xmas.config.checkCategoryEffects(configURL, lang, "");
    }
}
