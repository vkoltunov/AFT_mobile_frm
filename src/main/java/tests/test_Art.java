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
public class test_Art extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    @BeforeClass
    public static void beforeClass(){
        photolab.open();
    }

    @Before
    public void before(){
        testInfo.suite("test_Art");
    }

    //===== Full + Art =====

    @Test
    public void test10(){
        testInfo.id("test10").suite("Functionality").name("Full cycle work with photo + Add art Effect (Category:Smart Filters; Effect:Color On You)");

        photolab.custom.loadPictureToDevice("D:\\Base\\t1.png");
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Smart Filters");
        photolab.categories.selectEffect("Color On You");
        photolab.pictures.selectTab("All");
        photolab.pictures.selectPicture(1);
        photolab.property.waitToLoad();
        photolab.property.tapCrop3();
        photolab.property.tapDone();
        photolab.result.tapAddEffects();
        photolab.result.tapArt();
        photolab.art.selectArt(1);
        photolab.animate.tapDone();
        photolab.result.tapSaveToDevice();
        photolab.save.selectDownload();
        photolab.menu.open();
        photolab.menu.tapHome();
        photolab.custom.moveResPictureToPC("D:\\Base_Res");
        //photolab.custom.compareFiles("D:\\Base_Res", "D:\\Base_Res\\ColorOnYou.jpg");
        photolab.custom.clearFolderData("D:\\Base_Res");
    }
}
