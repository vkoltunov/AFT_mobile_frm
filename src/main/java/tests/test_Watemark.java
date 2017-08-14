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
public class test_Watemark extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    @org.testng.annotations.BeforeClass
    public static void beforeClass(){
        photolab.open();
    }

    @BeforeTest
    public void before(){
        testInfo.suite("test_Watemark");
    }

    //===== Watemark (GO PRO) =====

    @org.testng.annotations.Test
    public void test15_1(){
        testInfo.id("test15_1").suite("Functionality").name("Check watemark working. Tap button GO PRO.");

        photolab.custom.loadPictureToDevice(Config.APP_DATA_DIR+"\\photoLab\\source\\t1.png");
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
        photolab.save.tapWatemark();
        photolab.dialogs.checkDialogTitle("Logo removal is PRO users' privilege");
        photolab.dialogs.checkDialogMessage("Upgrade to premium version to get watermark-free results");
        photolab.dialogs.tapGoPRO();
        photolab.store.closeStore();
        photolab.save.waitToLoad();
        photolab.menu.open();
        photolab.menu.tapHome();
    }
}
