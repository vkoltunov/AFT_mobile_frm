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
public class test_SimpleWorkflow extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    @BeforeClass
    public static void beforeClass(){
        photolab.open();
    }

    @Before
    public void before(){
        testInfo.suite("test_SimpleWorkflow");
    }

    //===== Simple Workflow + Full Screen + Add Text =====

    @Test
    public void test4(){
        testInfo.id("test4").suite("Functionality").name("Full cycle work with photo + Add text Effect (Category:New Reality; Effect:Wedding March)");

        photolab.custom.loadPictureToDevice("D:\\Base\\t1.png");
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("New Reality");
        photolab.categories.selectEffect("Wedding March");
        photolab.pictures.selectTab("All");
        photolab.pictures.selectPicture(1);
        photolab.property.waitToLoad();
        photolab.property.tapCrop1();
        photolab.property.tapDone();
        photolab.result.tapAddEffects();
        photolab.result.tapText();
        photolab.text.typeText("Test");
        photolab.text.tapNext();
        photolab.text.tapFont();
        photolab.text.selectOption(1);
        photolab.text.tapStyle();
        photolab.text.selectOption(2);
        photolab.text.tapColor();
        photolab.text.selectOption(4);
        photolab.text.tapDone();
        photolab.result.tapSaveToDevice();
        photolab.save.selectDownload();
        photolab.menu.open();
        photolab.menu.tapHome();
        photolab.custom.moveResPictureToPC("D:\\Base_Res");
        photolab.custom.compareFiles("D:\\Base_Res", "D:\\Base_Res\\NR_WeddingMarch.jpg");
        photolab.custom.clearFolderData("D:\\Base_Res");
    }
}
