package tests;

import api.android.Android;
import api.apps.PhotoLab.PhotoLab;
import core.managers.TestManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
//import org.testng.annotations.*;

/**
 * Created by User on 4/5/2017.
 */
public class test_Navigation_Tabs extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    @org.testng.annotations.BeforeClass
    public static void beforeClass(){
        photolab.open();
    }

    @BeforeTest
    public void before(){
        testInfo.suite("test_Navigation_Tabs");
    }

//===== Categories =====

    @org.testng.annotations.Test
    public void test1() {
        testInfo.id("test1").suite("Functionality").name("Check Categories bar.");
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategoryBar("Inspiration");
        photolab.main.selectCategoryBar("Popular");
        photolab.main.selectCategoryBar("Favorites");
    }
}