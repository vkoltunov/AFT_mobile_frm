package tests;

import api.android.Android;
import api.apps.PhotoLab.PhotoLab;
import core.managers.TestManager;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
//import org.testng.annotations.*;

/**
 * Created by User on 4/5/2017.
 */
public class test_Navigation_Categories extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    @org.testng.annotations.BeforeClass
    public static void beforeClass(){
        photolab.open();
    }

    @BeforeTest
    public void before(){
        testInfo.suite("test_Navigation_Categories");
    }

//===== Categories =====

    @org.testng.annotations.Test
    public void test2() {
        testInfo.id("test2").suite("test_Navigation_Categories").name("Check Categories.");
        photolab.forceStop();
        photolab.open();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("New Reality");
        photolab.custom.pressBack();
        photolab.menu.tapMenu();
        photolab.menu.tapHome();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Masterpiece");
        photolab.custom.pressBack();
        photolab.menu.tapMenu();
        photolab.menu.tapHome();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("GIF Animations");
        photolab.custom.pressBack();
        photolab.menu.tapMenu();
        photolab.menu.tapHome();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Face Montages");
        photolab.custom.pressBack();
        photolab.menu.tapMenu();
        photolab.menu.tapHome();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Smart Filters");
        photolab.custom.pressBack();
        photolab.menu.tapMenu();
        photolab.menu.tapHome();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Art Backgrounds");
        photolab.custom.pressBack();
        photolab.menu.tapMenu();
        photolab.menu.tapHome();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Double Exposures");
        photolab.custom.pressBack();
        photolab.menu.tapMenu();
        photolab.menu.tapHome();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Half-Human Half-Animal");
        photolab.custom.pressBack();
        photolab.menu.tapMenu();
        photolab.menu.tapHome();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Emotion Changer");
        photolab.custom.pressBack();
        photolab.menu.tapMenu();
        photolab.menu.tapHome();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Drawing vs Photography");
        photolab.custom.pressBack();
        photolab.menu.tapMenu();
        photolab.menu.tapHome();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Featured Sketches & Paintings");
        photolab.custom.pressBack();
        photolab.menu.tapMenu();
        photolab.menu.tapHome();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Face Photo Props");
        photolab.custom.pressBack();
        photolab.menu.tapMenu();
        photolab.menu.tapHome();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Sketches & Paintings");
        photolab.custom.pressBack();
        photolab.menu.tapMenu();
        photolab.menu.tapHome();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Amazing Frames");
        photolab.custom.pressBack();
        photolab.menu.tapMenu();
        photolab.menu.tapHome();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Overlays");
        photolab.custom.pressBack();
        photolab.menu.tapMenu();
        photolab.menu.tapHome();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Magazine Covers");
        photolab.custom.pressBack();
        photolab.menu.tapMenu();
        photolab.menu.tapHome();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Stylized Effects");
        photolab.custom.pressBack();
        photolab.menu.tapMenu();
        photolab.menu.tapHome();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Flags");
        photolab.custom.pressBack();
        photolab.menu.tapMenu();
        photolab.menu.tapHome();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Celebrity Collages");
        photolab.custom.pressBack();
        photolab.menu.tapMenu();
        photolab.menu.tapHome();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Color & Lighting Filters");
        photolab.custom.pressBack();
        photolab.menu.tapMenu();
        photolab.menu.tapHome();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Human-to-Animal");
        photolab.custom.pressBack();
        photolab.menu.tapMenu();
        photolab.menu.tapHome();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Headwear");
        photolab.custom.pressBack();
        photolab.menu.tapMenu();
        photolab.menu.tapHome();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Simple Frames");
        photolab.custom.pressBack();
        photolab.menu.tapMenu();
        photolab.menu.tapHome();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Borders");
        photolab.custom.pressBack();
        photolab.menu.tapMenu();
        photolab.menu.tapHome();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Multiple Collages");
        photolab.custom.pressBack();
        photolab.menu.tapMenu();
        photolab.menu.tapHome();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Shape Collages");
        photolab.custom.pressBack();
        photolab.menu.tapMenu();
        photolab.menu.tapHome();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Money Templates");
        photolab.custom.pressBack();
        photolab.menu.tapMenu();
        photolab.menu.tapHome();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Fancy Filters");
        photolab.custom.pressBack();
        photolab.menu.tapMenu();
        photolab.menu.tapHome();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Monsters");
        photolab.custom.pressBack();
        photolab.menu.tapMenu();
        photolab.menu.tapHome();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Zodiac Signs");
        photolab.custom.pressBack();
        photolab.menu.tapMenu();
        photolab.menu.tapHome();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Photo to Caricature");
        photolab.custom.pressBack();
        photolab.menu.tapMenu();
        photolab.menu.tapHome();
        photolab.main.waitToLoad();
    }
}
