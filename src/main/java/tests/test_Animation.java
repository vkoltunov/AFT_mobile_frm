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
public class test_Animation extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    @org.testng.annotations.BeforeClass
    public static void beforeClass(){
        photolab.open();
    }

    @BeforeTest
    public void before(){
        testInfo.suite("test_Animation");
    }

    //===== Animation effect (Old Photo) =====

    @org.testng.annotations.Test
    public void test11(){
        testInfo.id("test11").suite("Functionality").name("Full cycle work with Animation photo effect (Category:New Reality; Effect:Old Photo)");

        photolab.custom.loadPicsToDeviceFromFolder(Config.APP_DATA_DIR+"\\photoLab\\source");
        photolab.forceStop();
        photolab.open();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("New Reality");
        photolab.categories.selectEffect("Old Photo Book");
        photolab.pictures.selectTab("All");
        photolab.pictures.selectPicture(1);
        photolab.pictures.selectPicture(2);
        photolab.pictures.selectPicture(3);
        photolab.pictures.selectPicture(4);
        photolab.pictures.tapNext();
        photolab.property.tapDone();
        photolab.result.tapSaveToDevice();
        photolab.save.selectDownload();
        photolab.menu.open();
        photolab.menu.tapHome();
        photolab.custom.moveResPictureToPC(Config.APP_DATA_DIR+"\\photoLab\\result");
        photolab.custom.compareFiles(Config.APP_DATA_DIR+"\\photoLab\\result", Config.APP_DATA_DIR+"\\photoLab\\result\\AnimationEffect_OldPhoto.gif");
        photolab.custom.clearFolderData(Config.APP_DATA_DIR+"\\photoLab\\result");
    }
}
