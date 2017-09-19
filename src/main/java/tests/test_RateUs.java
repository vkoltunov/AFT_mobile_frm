package tests;

import api.android.Android;
import api.apps.PhotoLab.PhotoLab;
import core.managers.TestManager;
import core.utils.Config;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testng.annotations.BeforeTest;

/**
 * Created by User on 6/26/2017.
 */
public class test_RateUs extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    //@org.testng.annotations.BeforeClass
    //public static void beforeClass(){
    //    photolab.open();
    //}

    @BeforeTest
    public void before(){
        testInfo.suite("test_RateUs");
    }

    //===== Rate US! =====

    @org.testng.annotations.Test
    public void test12(){
        testInfo.id("test12").suite("test_RateUs").name("Check Rate Us Dialog.");

        photolab.custom.loadPictureToDevice(Config.APP_DATA_DIR+"\\photoLab\\source\\t1.png");
        //photolab.main.waitToLoad();
        photolab.forceStop();
        photolab.clearData();
        photolab.open();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Smart Filters");
        photolab.categories.selectEffect("Color on You");
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
