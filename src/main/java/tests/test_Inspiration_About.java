package tests;

import api.android.Android;
import api.apps.PhotoLab.PhotoLab;
import core.managers.TestManager;
import core.utils.Config;
import org.junit.Test;
import org.testng.annotations.BeforeTest;

/**
 * Created by User on 6/26/2017.
 */
public class test_Inspiration_About extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    //@org.testng.annotations.BeforeClass
    //public static void beforeClass(){
    //    photolab.open();
    //}

    @BeforeTest
    public void before(){
        testInfo.suite("test_Inspiration");
    }

    @org.testng.annotations.Test
    public void test18_2(){
        testInfo.id("test18_2").suite("test_Inspiration").name("Inspiration check About This Combo Page.");

        photolab.custom.loadPictureToDevice(Config.APP_DATA_DIR+"\\photoLab\\source\\t1.png");
        photolab.forceStop();
        photolab.open();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Inspiration");
        photolab.main.selectBlock("Recent");
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
