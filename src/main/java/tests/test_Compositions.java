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
public class test_Compositions extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    @BeforeClass
    public static void beforeClass(){
        photolab.open();
    }

    @Before
    public void before(){
        testInfo.suite("test_Compositions");
    }

    //===== Compositions (Save to Photo Lab). OK for published. =====

    @Test
    public void test17_1(){
        testInfo.id("test17_1").suite("Functionality").name("Create composition + save to Photo Lab option.");

        photolab.custom.loadPictureToDevice("D:\\Base\\t1.png");
        photolab.main.waitToLoad();
        photolab.forceStop();
        photolab.clearData();
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
        photolab.save.selectTarget("Photo Lab");
        photolab.logIn.tapLogInFB("zqobenkoji_1497287426@tfbnw.net","testbook");
        photolab.dialogs.checkDialogMessage("Your photo effects combo has been published");
        photolab.dialogs.tapOk();
        photolab.menu.open();
        photolab.menu.tapHome();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Inspiration");
        photolab.main.selectBlock("Mine");
    }

    //===== Compositions (Save to Photo Lab). SHOW IN FEED for published. =====

    @Test
    public void test17_2(){
        testInfo.id("test17_2").suite("Functionality").name("Create composition + save to Photo Lab option.");

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
        photolab.logIn.tapLogInFB("zqobenkoji_1497287426@tfbnw.net","testbook");
        photolab.save.selectTarget("Photo Lab");
        photolab.dialogs.checkDialogMessage("Your photo effects combo has been published");
        photolab.dialogs.tapShowInFeed();
        photolab.main.selectBlock("Mine");
    }
}
