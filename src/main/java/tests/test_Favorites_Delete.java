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
public class test_Favorites_Delete extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    @org.testng.annotations.BeforeClass
    public static void beforeClass(){
        photolab.open();
    }

    @BeforeTest
    public void before(){
        testInfo.suite("test_Favorites_Delete");
    }

    //===== Favorites =====

    @org.testng.annotations.Test
    public void test14_3(){
        testInfo.id("test14_3").suite("test_Favorites_Delete").name("Delete effects from Favorites.");
        photolab.forceStop();
        photolab.open();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Favorites");
        photolab.main.longClickEffect("Depth Effect");
        photolab.main.tapDelete();
        photolab.main.existItem("Depth Effect", false);
        photolab.main.longClickEffect("Color on You");
        photolab.main.tapDelete();
        photolab.main.existItem("Color on You", false);
        photolab.main.longClickEffect("Sensation");
        photolab.main.tapDelete();
        photolab.main.existItem("Sensation", false);
        photolab.main.uiObject.favoritesText().exists();
    }
}
