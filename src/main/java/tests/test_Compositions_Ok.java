package tests;

import api.android.Android;
import api.apps.PhotoLab.PhotoLab;
import core.managers.TestManager;
import core.utils.Config;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

/**
 * Created by User on 6/26/2017.
 */
public class test_Compositions_Ok extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    @org.testng.annotations.BeforeClass
    public static void beforeClass(){
        photolab.open();
    }

    @BeforeTest
    public void before(){
        testInfo.suite("test_Compositions_Ok");
    }

    //===== Compositions (Save to Photo Lab). OK for published. =====

    @org.testng.annotations.Test
    @Parameters({"Fb_Login", "Fb_Password"})
    public void test17_1(@Optional("true") String fb_Login, @Optional("true") String fb_Password){
        testInfo.id("test17_1").suite("test_Compositions_Ok").name("Create composition + save to Photo Lab option.");

        photolab.custom.loadPictureToDevice(Config.APP_DATA_DIR+"\\photoLab\\source\\t1.png");
        photolab.main.waitToLoad();
        photolab.forceStop();
        photolab.clearData();
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
        photolab.save.selectTarget("Photo Lab");
        photolab.logIn.tapLogInFB(fb_Login,fb_Password);
        photolab.dialogs.checkDialogMessage("Your photo effects combo has been published");
        photolab.dialogs.tapOk();
        photolab.menu.open();
        photolab.menu.tapHome();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Inspiration");
        photolab.main.openProfile();
        photolab.custom.pressBack();
    }

}
