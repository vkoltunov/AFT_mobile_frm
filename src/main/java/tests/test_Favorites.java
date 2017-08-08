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
public class test_Favorites extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    @org.testng.annotations.BeforeClass
    public static void beforeClass(){
        photolab.open();
    }

    @BeforeTest
    public void before(){
        testInfo.suite("test_Favorites");
    }

    //===== Favorites =====

    @org.testng.annotations.Test
    public void test14_1(){
        testInfo.id("test14_1").suite("Functionality").name("Add and delete effect from Favorites.");

        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Masterpiece");
        photolab.categories.selectEffect("Pop Art Style");
        photolab.pictures.addToFavorites();
        photolab.menu.open();
        photolab.menu.tapHome();
        photolab.main.selectCategoryBar("Favorites");
        photolab.main.existItem("Pop Art Style", true);
        photolab.main.selectEffect("Pop Art Style");
        photolab.pictures.addToFavorites();
        photolab.pictures.tapBack();
        photolab.main.selectCategoryBar("Favorites");
        photolab.main.existItem("Pop Art Style", false);
    }

}
