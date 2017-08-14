package tests;

import api.android.Android;
import api.apps.PhotoLab.PhotoLab;
import core.managers.TestManager;
import core.utils.Config;
import org.testng.annotations.BeforeTest;

/**
 * Created by User on 6/26/2017.
 */
public class test_Watemark_YesSponsored extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    @org.testng.annotations.BeforeClass
    public static void beforeClass(){
        photolab.open();
    }

    @BeforeTest
    public void before(){
        testInfo.suite("test_Watemark_YesSponsored");
    }

    //===== Watemark (NOT NOW + Yes for ADSponsoredVideo) =====

    @org.testng.annotations.Test
    public void test15_3(){
        testInfo.id("test15_3").suite("Functionality").name("Check watemark working. Tap button NOT NOW. Tap Yes for sponsored video.");

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
        photolab.dialogs.tapNotNow();
        photolab.custom.workWithSponsoredPage("Yes");
        photolab.menu.open();
        photolab.menu.tapHome();
        photolab.custom.moveResPictureToPC(Config.APP_DATA_DIR+"\\photoLab\\result");
        photolab.custom.compareFiles(Config.APP_DATA_DIR+"\\photoLab\\result", Config.APP_DATA_DIR+"\\photoLab\\result\\NoWatemark.jpg");
        photolab.custom.clearFolderData(Config.APP_DATA_DIR+"\\photoLab\\result");
    }
}
