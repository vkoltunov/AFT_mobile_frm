package tests;

import api.android.Android;
import api.apps.PhotoLab.PhotoLab;
import core.managers.TestManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testng.annotations.BeforeTest;

/**
 * Created by User on 6/26/2017.
 */
public class test_Inspiration extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    @org.testng.annotations.BeforeClass
    public static void beforeClass(){
        photolab.open();
    }

    @BeforeTest
    public void before(){
        testInfo.suite("test_Inspiration");
    }

    //===== Check INSPIRATION blocks. =====

    @org.testng.annotations.Test
    public void test17_3(){
        testInfo.id("test17_3").suite("Functionality").name("Inspiration page from start + check blocks.");
        photolab.forceStop();
        photolab.open();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Inspiration");
        photolab.main.selectBlock("Top");
        photolab.main.selectBlock("Trending");
        photolab.main.selectBlock("Recent");
        photolab.main.tapMenu();
        photolab.menu.tapHome();
    }

}
