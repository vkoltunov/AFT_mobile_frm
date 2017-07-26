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
public class test_Watemark extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    @BeforeClass
    public static void beforeClass(){
        photolab.open();
    }

    @Before
    public void before(){
        testInfo.suite("test_Watemark");
    }

    //===== Watemark (GO PRO) =====

    @Test
    public void test15_1(){
        testInfo.id("test15_1").suite("Functionality").name("Check watemark working. Tap button GO PRO.");

        photolab.custom.loadPictureToDevice("D:\\Base\\t1.png");
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

    //===== Watemark (NOT NOW + No for ADSponsoredVideo) =====

    @Test
    public void test15_2(){
        testInfo.id("test15_2").suite("Functionality").name("Check watemark working. Tap button NOT NOW. Tap No for sponsored video.");

        photolab.custom.loadPictureToDevice("D:\\Base\\t1.png");
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
        photolab.custom.workWithSponsoredPage("Not");
        photolab.menu.open();
        photolab.menu.tapHome();
    }

    //===== Watemark (NOT NOW + Yes for ADSponsoredVideo) =====

    @Test
    public void test15_3(){
        testInfo.id("test15_3").suite("Functionality").name("Check watemark working. Tap button NOT NOW. Tap Yes for sponsored video.");

        photolab.custom.loadPictureToDevice("D:\\Base\\t1.png");
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
        //photolab.custom.moveResPictureToPC("D:\\Base_Res");
    }
}
