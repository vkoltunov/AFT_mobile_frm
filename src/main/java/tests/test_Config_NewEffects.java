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
public class test_Config_NewEffects extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

//    @org.testng.annotations.BeforeClass
//    public static void beforeClass(){
//        photolab.open();
//    }

    @BeforeTest
    public void before(){
        testInfo.suite("test_Config_NewEffects");
    }

//===== Categories =====

    @org.testng.annotations.Test
    @Parameters({"Config", "AppType"})
    public void test35(String configURL, @Optional String appType) throws ParseException {
        testInfo.id("test1").suite("test_Config_NewEffects").name("Check New Effects for App.");
        photolab.setAppType(appType);
        photolab.forceStop();
        photolab.open();
        photolab.main.waitToLoad();
        photolab.config.checkIsNewEffects(configURL);
    }
}
