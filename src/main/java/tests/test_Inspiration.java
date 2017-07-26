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
public class test_Inspiration extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    @BeforeClass
    public static void beforeClass(){
        photolab.open();
    }

    @Before
    public void before(){
        testInfo.suite("test_Inspiration");
    }

    //===== Check INSPIRATION blocks. =====

    @Test
    public void test17_3(){
        testInfo.id("test17_3").suite("Functionality").name("Inspiration page from start + check blocks.");
        photolab.main.waitToLoad();
        photolab.main.uiObject.barItem("Inspiration").exists();
        photolab.main.selectBlock("Top");
        photolab.main.selectBlock("Trending");
        photolab.main.selectBlock("Recent");
        photolab.main.tapMenu();
        photolab.menu.tapHome();
    }

    //===== INSPIRATION tests. =====

    @Test
    public void test18_1(){
        testInfo.id("test18_1").suite("Functionality").name("Inspiration full test with FIRST pic from Recent block.");

        photolab.custom.loadPictureToDevice("D:\\Base\\t1.png");
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
        photolab.custom.moveResPictureToPC("D:\\Base_Res");
        //photolab.custom.compareFiles("D:\\Base_Res", "D:\\Base_Res\\NotificationCheck.jpg");
        photolab.custom.clearFolderData("D:\\Base_Res");
    }

    @Test
    public void test18_2(){
        testInfo.id("test18_2").suite("Functionality").name("Inspiration check About This Combo Page.");

        photolab.custom.loadPictureToDevice("D:\\Base\\t1.png");
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Inspiration");
        photolab.main.selectBlock("Recent");
        photolab.main.selectItemByIndex(0);
        photolab.pictures.waitToLoad();
        photolab.pictures.aboutThisCombo();
        photolab.aboutCombo.checkPage();
        photolab.aboutCombo.tapClose();
        photolab.pictures.waitToLoad();
        photolab.custom.pressBack();
        photolab.main.waitToLoad();
    }
}
