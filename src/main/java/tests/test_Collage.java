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
public class test_Collage extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    //@org.testng.annotations.BeforeClass
    //public static void beforeClass(){
    //    photolab.open();
    //}

    @BeforeTest
    public void before(){
        testInfo.suite("test_Collage");
    }

    //===== Multiple Collages =====

    @org.testng.annotations.Test
    @Parameters({"AppType"})
    public void test13(@Optional String appType){
        testInfo.id("test13").suite("test_Collage").name("Full cycle work with multi pictures effect (Category:Multiple Collages; Effect:Globe) + Delete photo from recent.");
        photolab.setAppType(appType);

        photolab.custom.loadPicsToDeviceFromFolder(Config.APP_DATA_DIR+"\\photoLab\\source");
        photolab.forceStop();
        photolab.open();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Multiple Collages");
        photolab.categories.selectEffect("Globe");
        photolab.pictures.selectTab("All");
        photolab.pictures.selectPicture(1);
        photolab.pictures.selectTab("Recent");
        photolab.pictures.longClickPicture(1, "bottom");
        photolab.pictures.tapDelete();
        photolab.pictures.longClickPicture(1, "top");
        photolab.pictures.tapDelete();
        photolab.pictures.selectTab("All");
        photolab.pictures.selectPicture(1);
        photolab.pictures.selectPicture(2);
        photolab.pictures.selectPicture(3);
        photolab.pictures.selectPicture(4);
        photolab.pictures.selectPicture(5);
        photolab.pictures.selectPicture(6);
        photolab.pictures.selectPicture(7);
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
        photolab.custom.compareFiles(Config.APP_DATA_DIR+"\\photoLab\\result", Config.APP_DATA_DIR+"\\photoLab\\result\\MultipleCollages_Globe_"+Android.app.photoLab.appType+".jpg");
    }

    @AfterTest
    public void after(){
        photolab.custom.clearFolderData(Config.APP_DATA_DIR+"\\photoLab\\result");
    }
}
