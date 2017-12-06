package tests;

import api.android.Android;
import api.apps.PhotoLab.PhotoLab;
import api.apps.Xmas.Xmas;
import core.managers.TestManager;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
//import org.testng.annotations.*;

/**
 * Created by User on 4/5/2017.
 */
public class xmas_Config_EffectsPreview extends TestManager {

    private static Xmas xmas = Android.app.xmas;

//    @org.testng.annotations.BeforeClass
//    public static void beforeClass(){
//        photolab.open();
//    }

    @BeforeTest
    public void before(){
        testInfo.suite("xmas_Config_EffectsPreview");
    }

//===== Categories =====

    @org.testng.annotations.Test
    @Parameters({"Config"})
    public void test35(String configURL) throws ParseException {
        testInfo.id("test1").suite("xmas_Config_EffectsPreview").name("Check Effects Preview URLs for App.");
        xmas.config.checkElementsPreview(configURL, "effects");
    }
}
