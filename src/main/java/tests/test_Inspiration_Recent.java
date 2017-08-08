package tests;

import api.android.Android;
import api.apps.PhotoLab.PhotoLab;
import core.managers.TestManager;
import core.utils.Config;
import org.junit.Test;
import org.testng.annotations.BeforeTest;

/**
 * Created by User on 6/26/2017.
 */
public class test_Inspiration_Recent extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    @org.testng.annotations.BeforeClass
    public static void beforeClass(){
        photolab.open();
    }

    @BeforeTest
    public void before(){
        testInfo.suite("test_Inspiration");
    }

    //===== INSPIRATION tests. =====

    @org.testng.annotations.Test
    public void test18_1(){
        testInfo.id("test18_1").suite("Functionality").name("Inspiration full test with FIRST pic from Recent block.");

        photolab.custom.loadPictureToDevice(Config.APP_DATA_DIR+"\\photoLab\\source\\t1.png");
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Inspiration");
        photolab.main.selectBlock("Recent");
        photolab.main.selectItemByIndex(0);
        photolab.pictures.selectTab("All");
        photolab.pictures.selectPicture(1);
        photolab.property.tapDone();
        photolab.result.tapSaveToDevice();
        photolab.save.selectDownload();
        photolab.menu.open();
        photolab.menu.tapHome();
        photolab.custom.moveResPictureToPC(Config.APP_DATA_DIR+"\\photoLab\\result");
        //photolab.custom.compareFiles("D:\\Base_Res", "D:\\Base_Res\\NotificationCheck.jpg");
        photolab.custom.clearFolderData(Config.APP_DATA_DIR+"\\photoLab\\result");
    }
}
