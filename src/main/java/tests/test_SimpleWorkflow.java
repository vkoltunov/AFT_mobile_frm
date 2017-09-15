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

/**
 * Created by User on 6/26/2017.
 */
public class test_SimpleWorkflow extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    @org.testng.annotations.BeforeClass
    public static void beforeClass(){
        photolab.open();
    }

    @BeforeTest
    public void before(){
        testInfo.suite("test_SimpleWorkflow");
    }

    //===== Simple Workflow + Full Screen + Add Text =====

    @org.testng.annotations.Test
    public void test4(){
        testInfo.id("test4").suite("test_SimpleWorkflow").name("Full cycle work with photo + Add text Effect (Category:New Reality; Effect:Wedding March)");
        photolab.custom.loadPictureToDevice(Config.APP_DATA_DIR+"\\photoLab\\source\\t1.png");
        photolab.forceStop();
        photolab.open();
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
        photolab.save.checkSaveShareTutorial();
        photolab.save.selectDownload();
        photolab.menu.open();
        photolab.menu.tapHome();
        photolab.custom.moveResPictureToPC(Config.APP_DATA_DIR+"\\photoLab\\result");
        photolab.custom.compareFiles(Config.APP_DATA_DIR+"\\photoLab\\result", Config.APP_DATA_DIR+"\\photoLab\\result\\NR_WeddingMarch.jpg");
    }

    @AfterTest
    public void after(){
        photolab.custom.clearFolderData(Config.APP_DATA_DIR+"\\photoLab\\result");
    }
}
