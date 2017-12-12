package tests;

import api.android.Android;
import api.apps.PhotoLab.PhotoLab;
import core.managers.TestManager;
import core.utils.Config;
import org.testng.annotations.BeforeTest;

/**
 * Created by User on 6/26/2017.
 */
public class test_AboutThisCombo extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    //@org.testng.annotations.BeforeClass
    //public static void beforeClass(){
    //    photolab.open();
    //}

    @BeforeTest
    public void before(){
        testInfo.suite("test_AboutThisCombo");
    }

    @org.testng.annotations.Test
    public void test31(){
        testInfo.id("test31").suite("test_AboutThisCombo").name("Check About This Combo page.");
        photolab.forceStop();
        photolab.open();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Feed");
        photolab.main.selectItemByIndex(0);
        photolab.pictures.waitToLoad();
        photolab.pictures.aboutThisCombo();
        photolab.aboutCombo.checkPage();
        photolab.aboutCombo.tapClose();
        photolab.pictures.waitToLoad();
        photolab.custom.pressBack();
        photolab.main.waitToLoad();
    }
}
