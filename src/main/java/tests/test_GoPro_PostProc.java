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
public class test_GoPro_PostProc extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    //@org.testng.annotations.BeforeClass
    //public static void beforeClass(){
    //    photolab.open();
    //}

    @BeforeTest
    public void before(){
        testInfo.suite("test_GoPro_PostProc");
    }

    //===== Pro Button Main Page =====

    @org.testng.annotations.Test
    public void test8(){
        testInfo.id("test8").suite("test_GoPro_PostProc").name("Check Demo tizer for PRO effect. (PostProcessing Go PRO now option)");
        photolab.custom.loadPictureToDevice(Config.APP_DATA_DIR+"\\photoLab\\source\\t1.png");
        photolab.forceStop();
        photolab.open();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Overlays");
        photolab.categories.selectEffect("Light Bokeh");
        photolab.pictures.selectTab("All");
        photolab.pictures.selectPicture(1);
        photolab.property.waitToLoad();
        photolab.property.tapDone();
        photolab.goPRO.tapGoPRO();
        photolab.store.closeStore();
        photolab.goPRO.waitToLoad();
        photolab.custom.pressBack();
        photolab.pictures.waitToLoad();
        photolab.menu.open();
        photolab.menu.tapHome();
    }
}
