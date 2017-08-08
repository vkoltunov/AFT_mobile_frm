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
public class test_Favorites_Add extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    @org.testng.annotations.BeforeClass
    public static void beforeClass(){
        photolab.open();
    }

    @BeforeTest
    public void before(){
        testInfo.suite("test_Favorites_Add");
    }

    //===== Favorites =====

    @org.testng.annotations.Test
    public void test14_2(){
        testInfo.id("test14_2").suite("Functionality").name("Add effects to Favorites and check they.");

        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Smart Filters");
        photolab.categories.selectEffect("Depth Effect");
        photolab.pictures.addToFavorites();
        photolab.pictures.tapBack();
        photolab.categories.waitToLoad();
        photolab.categories.selectEffect("Color on You");
        photolab.pictures.addToFavorites();
        photolab.pictures.tapBack();
        photolab.categories.waitToLoad();
        photolab.categories.selectEffect("Sensation");
        photolab.pictures.addToFavorites();
        photolab.pictures.tapBack();
        photolab.categories.waitToLoad();
        photolab.pictures.tapBack();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Favorites");
        photolab.main.existItem("Depth Effect", true);
        photolab.main.existItem("Color on You", true);
        photolab.main.existItem("Sensation", true);
    }
}
