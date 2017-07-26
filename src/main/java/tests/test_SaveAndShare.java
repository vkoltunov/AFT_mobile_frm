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
public class test_SaveAndShare extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    @BeforeClass
    public static void beforeClass(){
        photolab.open();
    }

    @Before
    public void before(){
        testInfo.suite("test_SaveAndShare");
    }

    //===== Save and Share Tutorial =====

    @Test
    public void test3(){
        testInfo.id("test3").suite("Functionality").name("First Start. Check Save and Share tutorial.");

        photolab.custom.loadPictureToDevice("D:\\Base\\t1.png");
        photolab.forceStop();
        photolab.open();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Masterpiece");
        photolab.main.selectEffect("Pop Art Style");
        photolab.pictures.selectTab("All");
        photolab.pictures.selectPicture(1);
        photolab.property.waitToLoad();
        photolab.property.tapDone();
        photolab.result.tapSaveToDevice();
        photolab.save.checkSaveShareTutorial();
        photolab.menu.open();
        photolab.menu.tapHome();
    }
}
