package tests;

import api.android.Android;
import api.apps.PhotoLab.PhotoLab;
import core.managers.TestManager;
import core.utils.Config;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

/**
 * Created by User on 6/26/2017.
 */
public class test_Art extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    //@org.testng.annotations.BeforeClass
    //public static void beforeClass(){
    //    photolab.open();
    //}

    @BeforeTest
    public void before(){
        testInfo.suite("test_Art");
    }

    //===== Full + Art =====

    @org.testng.annotations.Test
    @Parameters({"AppType"})
    public void test10(@Optional String appType){
        testInfo.id("test10").suite("test_Art").name("Full cycle work with photo + Add art Effect (Category:Smart Filters; Effect:Color On You)");
        photolab.setAppType(appType);

        photolab.custom.loadPictureToDevice(Config.APP_DATA_DIR+"\\photoLab\\source\\t1.png");
        photolab.forceStop();
        photolab.open();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Face Photo Props");
        photolab.categories.selectEffect("Red Lips Sticker");
        photolab.pictures.selectTab("All");
        photolab.pictures.selectPicture(1);
        photolab.property.waitToLoad();
        photolab.property.tapDone();
        photolab.result.tapAddEffects();
        photolab.result.tapArt();
        photolab.art.selectArt("Red Boost");
        photolab.animate.tapDone();
        photolab.result.tapSaveToDevice();
        photolab.save.selectDownload();
        photolab.menu.open();
        photolab.menu.tapHome();
        photolab.custom.moveResPictureToPC(Config.APP_DATA_DIR+"\\photoLab\\result");
        photolab.custom.compareFiles(Config.APP_DATA_DIR+"\\photoLab\\result", Config.APP_DATA_DIR+"\\photoLab\\result\\RedLipsSticker_"+Android.app.photoLab.appType+".jpg");
    }

    @AfterTest
    public void after(){
        photolab.custom.clearFolderData(Config.APP_DATA_DIR+"\\photoLab\\result");
    }

}
