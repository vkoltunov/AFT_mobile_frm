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
public class test_RateUs extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    @BeforeClass
    public static void beforeClass(){
        photolab.open();
    }

    @Before
    public void before(){
        testInfo.suite("test_RateUs");
    }

    //===== Rate US! =====

    @Test
    public void test12(){
        testInfo.id("test12").suite("Functionality").name("Check Rate Us Dialog.");

        photolab.custom.loadPictureToDevice("D:\\Base\\t1.png");
        photolab.main.waitToLoad();
        photolab.forceStop();
        photolab.clearData();
        photolab.open();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Smart Filters");
        photolab.categories.selectEffect("Color On You");
        photolab.pictures.selectTab("All");
        photolab.pictures.selectPicture(1);
        photolab.property.waitToLoad();
        photolab.property.tapDone();
        photolab.custom.pressBack();

        photolab.pictures.selectPicture(1);
        photolab.property.waitToLoad();
        photolab.property.tapDone();
        photolab.custom.pressBack();

        photolab.pictures.selectPicture(1);
        photolab.property.waitToLoad();
        photolab.property.tapDone();
        photolab.custom.pressBack();

        photolab.pictures.selectPicture(1);
        photolab.property.waitToLoad();
        photolab.property.tapDone();
        photolab.custom.pressBack();

        photolab.pictures.selectPicture(1);
        photolab.property.waitToLoad();
        photolab.property.tapDone();
        photolab.custom.pressBack();

        photolab.dialogs.waitToLoad();
        photolab.dialogs.tapRate();

        photolab.store.closeStore();

        photolab.pictures.waitToLoad();
        photolab.menu.open();
        photolab.menu.tapHome();
    }
}
