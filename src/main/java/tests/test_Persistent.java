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
public class test_Persistent extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    @org.testng.annotations.BeforeClass
    public static void beforeClass(){
        photolab.open();
    }

    @BeforeTest
    public void before(){
        testInfo.suite("test_Persistent");
    }

    //===== Persistent =====

    @org.testng.annotations.Test
    public void test6(){
        testInfo.id("test6").suite("Functionality").name("OFF/ON Persistent + Full cycle work with photo + Add art Effect Kandinsky(Category:Double Exposures; Effect:Star Dust)");

        photolab.custom.loadPictureToDevice(Config.APP_DATA_DIR+"\\photoLab\\source\\t1.png");
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Double Exposures");
        photolab.categories.selectEffect("Star Dust");

        photolab.pictures.selectTab("All");
        photolab.pictures.selectPicture(1);
        photolab.property.waitToLoad();
        photolab.property.offHints();
        photolab.custom.pressBack();
        photolab.pictures.selectTab("Recent");
        photolab.pictures.waitToLoad();
        photolab.pictures.checkPersistent(1, false);

        photolab.pictures.selectPicture(1);
        photolab.property.waitToLoad();
        photolab.property.onHints();
        photolab.custom.pressBack();
        photolab.pictures.waitToLoad();
        photolab.pictures.checkPersistent(1, true);


        photolab.pictures.selectPicture(1);
        photolab.property.waitToLoad();
        photolab.property.tapDone();
        photolab.result.tapAddEffects();
        photolab.result.tapArt();
        photolab.art.selectArt(1);
        photolab.animate.tapDone();
        photolab.result.tapSaveToDevice();
        photolab.save.selectDownload();
        photolab.menu.open();
        photolab.menu.tapHome();
        photolab.custom.moveResPictureToPC(Config.APP_DATA_DIR+"\\photoLab\\result");
        //photolab.custom.compareFiles("D:\\Base_Res", "D:\\Base_Res\\StarDust_Kandinsky.jpg");
        photolab.custom.clearFolderData(Config.APP_DATA_DIR+"\\photoLab\\result");
    }
}
