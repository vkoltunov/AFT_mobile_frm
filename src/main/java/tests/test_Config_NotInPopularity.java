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
public class test_Config_NotInPopularity extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

//    @org.testng.annotations.BeforeClass
//    public static void beforeClass(){
//        photolab.open();
//    }

    @BeforeTest
    public void before(){
        testInfo.suite("test_Config_NotInPopularity");
    }

//===== Categories =====

    @org.testng.annotations.Test
    @Parameters({"Config", "CsvPath"})
    public void test35(String configURL, String csvPath) throws ParseException {
        testInfo.id("test1").suite("test_Config_NotInPopularity").name("Check effects from conf.json not included in Popularity.csv .");
        photolab.config.checkNotInPopularityEffects(configURL, csvPath);
    }
}
