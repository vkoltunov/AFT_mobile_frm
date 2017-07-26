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
public class test_Collage extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    @BeforeClass
    public static void beforeClass(){
        photolab.open();
    }

    @Before
    public void before(){
        testInfo.suite("test_Collage");
    }

    //===== Multiple Collages =====

    @Test
    public void test13(){
        testInfo.id("test13").suite("Functionality").name("Full cycle work with multi pictures effect (Category:Multiple Collages; Effect:Globe) + Delete photo from recent.");

        photolab.custom.loadPicsToDeviceFromFolder("D:\\Base");
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
        photolab.pictures.addToFavorites();
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
        photolab.custom.moveResPictureToPC("D:\\Base_Res");
        photolab.custom.compareFiles("D:\\Base_Res", "D:\\Base_Res\\MultipleCollages_Globe.jpg");
        photolab.custom.clearFolderData("D:\\Base_Res");
    }
}
