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
public class test_Animation extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    @BeforeClass
    public static void beforeClass(){
        photolab.open();
    }

    @Before
    public void before(){
        testInfo.suite("test_Animation");
    }

    //===== Animation effect (Old Photo) =====

    @Test
    public void test11(){
        testInfo.id("test11").suite("Functionality").name("Full cycle work with Animation photo effect (Category:New Reality; Effect:Old Photo)");

        photolab.custom.loadPicsToDeviceFromFolder("D:\\Base");
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
        photolab.result.tapAddText();
        photolab.text.typeText("Test");
        photolab.text.tapNext();
        photolab.text.tapDone();
        photolab.result.tapSaveToDevice();
        photolab.save.selectDownload();
        photolab.menu.open();
        photolab.menu.tapHome();
        photolab.custom.moveResPictureToPC("D:\\Base_Res");
        photolab.custom.compareFiles("D:\\Base_Res", "D:\\Base_Res\\AnimationEffect_OldPhoto.gif");
        photolab.custom.clearFolderData("D:\\Base_Res");
    }
}
