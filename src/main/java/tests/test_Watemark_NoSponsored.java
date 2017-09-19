package tests;

import api.android.Android;
import api.apps.PhotoLab.PhotoLab;
import core.managers.TestManager;
import core.utils.Config;
import org.testng.annotations.BeforeTest;

/**
 * Created by User on 6/26/2017.
 */
public class test_Watemark_NoSponsored extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    //@org.testng.annotations.BeforeClass
    //public static void beforeClass(){
    //    photolab.open();
    //}

    @BeforeTest
    public void before(){
        testInfo.suite("test_Watemark_NoSponsored");
    }

    //===== Watemark (NOT NOW + No for ADSponsoredVideo) =====

    @org.testng.annotations.Test
    public void test15_2(){
        testInfo.id("test15_2").suite("test_Watemark_NoSponsored").name("Check watemark working. Tap button NOT NOW. Tap No for sponsored video.");

        photolab.custom.loadPictureToDevice(Config.APP_DATA_DIR+"\\photoLab\\source\\t1.png");
        photolab.forceStop();
        photolab.open();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Masterpiece");
        photolab.categories.selectEffect("Pop Art Style");
        photolab.pictures.selectTab("All");
        photolab.pictures.selectPicture(1);
        photolab.property.waitToLoad();
        photolab.property.tapDone();
        photolab.result.tapSaveToDevice();
        photolab.save.tapWatemark();
        photolab.dialogs.checkDialogTitle("Logo removal is PRO users' privilege");
        photolab.dialogs.checkDialogMessage("Upgrade to premium version to get watermark-free results");
        photolab.dialogs.tapNotNow();
        photolab.custom.workWithSponsoredPage("Not");
        photolab.menu.open();
        photolab.menu.tapHome();
    }
}
