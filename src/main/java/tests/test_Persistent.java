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
public class test_Persistent extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    @BeforeClass
    public static void beforeClass(){
        photolab.open();
    }

    @Before
    public void before(){
        testInfo.suite("test_Persistent");
    }

    //===== Persistent =====

    @Test
    public void test6(){
        testInfo.id("test6").suite("Functionality").name("OFF/ON Persistent + Full cycle work with photo + Add art Effect Kandinsky(Category:Double Exposures; Effect:Star Dust)");

        photolab.custom.loadPictureToDevice("D:\\Base\\t1.png");
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
        photolab.custom.moveResPictureToPC("D:\\Base_Res");
        //photolab.custom.compareFiles("D:\\Base_Res", "D:\\Base_Res\\StarDust_Kandinsky.jpg");
        photolab.custom.clearFolderData("D:\\Base_Res");
    }
}
