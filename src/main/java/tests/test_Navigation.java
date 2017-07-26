package tests;

import api.android.Android;
import api.apps.PhotoLab.PhotoLab;
import core.managers.TestManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testng.annotations.*;

/**
 * Created by User on 4/5/2017.
 */
public class test_Navigation extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    @BeforeClass
    public static void beforeClass(){
        photolab.open();
    }

    @Before
    public void before(){
        testInfo.suite("test_Navigation");
    }

//===== Categories =====

    @Test
    public void test1() {
        testInfo.id("test1").suite("Functionality").name("Check Categories bar.");
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategoryBar("Inspiration");
        //photolab.main.selectCategoryBar("Popular");
        photolab.main.selectCategoryBar("Favorites");
    }

    @Test
    public void test2() {
        testInfo.id("test2").suite("Functionality").name("Check Categories.");
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("New Reality");
        photolab.custom.pressBack();
        photolab.main.waitToLoad();
        photolab.main.selectCategory("Masterpiece");
        photolab.custom.pressBack();
        photolab.main.waitToLoad();
        photolab.main.selectCategory("GIF Animations");
        photolab.custom.pressBack();
        photolab.main.waitToLoad();
        photolab.main.selectCategory("Face Montages");
        photolab.custom.pressBack();
        photolab.main.waitToLoad();
        photolab.main.selectCategory("Smart Filters");
        photolab.custom.pressBack();
        photolab.main.waitToLoad();
        photolab.main.selectCategory("Art Backgrounds");
        photolab.custom.pressBack();
        photolab.main.waitToLoad();
        photolab.main.selectCategory("Double Exposures");
        photolab.custom.pressBack();
        photolab.main.waitToLoad();
        photolab.main.selectCategory("Half-Human Half-Animal");
        photolab.custom.pressBack();
        photolab.main.waitToLoad();
        photolab.main.selectCategory("Drawing vs Photography");
        photolab.custom.pressBack();
        photolab.main.waitToLoad();
        photolab.main.selectCategory("Featured Sketches & Paintings");
        photolab.custom.pressBack();
        photolab.main.waitToLoad();
        photolab.main.selectCategory("Face Photo Props");
        photolab.custom.pressBack();
        photolab.main.waitToLoad();
        photolab.main.selectCategory("Sketches & Paintings");
        photolab.custom.pressBack();
        photolab.main.waitToLoad();
        photolab.main.selectCategory("Amazing Frames");
        photolab.custom.pressBack();
        photolab.main.waitToLoad();
        photolab.main.selectCategory("Overlays");
        photolab.custom.pressBack();
        photolab.main.waitToLoad();
        photolab.main.selectCategory("Magazine Covers");
        photolab.custom.pressBack();
        photolab.main.waitToLoad();
        photolab.main.selectCategory("Stylized Effects");
        photolab.custom.pressBack();
        photolab.main.waitToLoad();
        photolab.main.selectCategory("Flags");
        photolab.custom.pressBack();
        photolab.main.waitToLoad();
        photolab.main.selectCategory("Celebrity Collages");
        photolab.custom.pressBack();
        photolab.main.waitToLoad();
        photolab.main.selectCategory("Color & Lighting Filters");
        photolab.custom.pressBack();
        photolab.main.waitToLoad();
        photolab.main.selectCategory("Human-to-Animal");
        photolab.custom.pressBack();
        photolab.main.waitToLoad();
        photolab.main.selectCategory("Headwear");
        photolab.custom.pressBack();
        photolab.main.waitToLoad();
        photolab.main.selectCategory("Simple Frames");
        photolab.custom.pressBack();
        photolab.main.waitToLoad();
        photolab.main.selectCategory("Borders");
        photolab.custom.pressBack();
        photolab.main.waitToLoad();
        photolab.main.selectCategory("Multiple Collages");
        photolab.custom.pressBack();
        photolab.main.waitToLoad();
        photolab.main.selectCategory("Shape Collages");
        photolab.custom.pressBack();
        photolab.main.waitToLoad();
        photolab.main.selectCategory("Money Templates");
        photolab.custom.pressBack();
        photolab.main.waitToLoad();
        photolab.main.selectCategory("Fancy Filters");
        photolab.custom.pressBack();
        photolab.main.waitToLoad();
        photolab.main.selectCategory("Monsters");
        photolab.custom.pressBack();
        photolab.main.waitToLoad();
        photolab.main.selectCategory("Zodiac Signs");
        photolab.custom.pressBack();
        photolab.main.waitToLoad();
        photolab.main.selectCategory("Photo to Caricature");
        photolab.custom.pressBack();
        photolab.main.waitToLoad();
        photolab.main.tapMenu();
        photolab.menu.tapHome();
    }
}
