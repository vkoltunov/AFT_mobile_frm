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
public class xmas_Config_Tabs extends TestManager {

    private static Xmas xmas = Android.app.xmas;

//    @org.testng.annotations.BeforeClass
//    public static void beforeClass(){
//        photolab.open();
//    }

    @BeforeTest
    public void before(){
        testInfo.suite("xmas_Config_Tabs");
    }

//===== Categories =====

    @org.testng.annotations.Test
    @Parameters({"Config", "Language", "AppType"})
    public void test36(String configURL, String lang, @Optional String appType) throws ParseException {
        testInfo.id("test36").suite("xmas_Config_Tabs").name("Check Tabs list for '"+lang+"' language.");
        xmas.setAppType(appType);

        xmas.custom.changeLocalization(lang);
        xmas.forceStop();
        xmas.open();
        xmas.main.waitToLoad();
        xmas.config.checkTabs(configURL, lang);
        xmas.custom.changeLocalization("en");
    }
}
