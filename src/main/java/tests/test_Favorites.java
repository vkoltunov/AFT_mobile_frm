package tests;

import api.android.Android;
import api.apps.PhotoLab.PhotoLab;
import core.managers.TestManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by User on 6/26/2017.
 */
public class test_Favorites extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    @BeforeClass
    public static void beforeClass(){
        photolab.open();
    }

    @Before
    public void before(){
        testInfo.suite("test_Favorites");
    }

    //===== Favorites =====

    @Test
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

    @Test
    public void test14_2(){
        testInfo.id("test14_2").suite("Functionality").name("Add effects to Favorites and check they.");

        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Smart Filters");
        photolab.categories.selectEffect("Depth Effect");
        photolab.pictures.addToFavorites();
        photolab.pictures.tapBack();
        photolab.categories.waitToLoad();
        photolab.categories.selectEffect("Color On You");
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
        photolab.main.existItem("Color On You", true);
        photolab.main.existItem("Sensation", true);
    }

    @Test
    public void test14_3(){
        testInfo.id("test14_3").suite("Functionality").name("Delete effects from Favorites.");

        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Favorites");
        photolab.main.longClickEffect("Depth Effect");
        photolab.main.tapDelete();
        photolab.main.existItem("Depth Effect", false);
        photolab.main.longClickEffect("Color On You");
        photolab.main.tapDelete();
        photolab.main.existItem("Color On You", false);
        photolab.main.longClickEffect("Sensation");
        photolab.main.tapDelete();
        photolab.main.existItem("Sensation", false);
        photolab.main.uiObject.favoritesText().exists();
    }
}
