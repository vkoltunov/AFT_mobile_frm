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
public class test_GoPro extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    @BeforeClass
    public static void beforeClass(){
        photolab.open();
    }

    @Before
    public void before(){
        testInfo.suite("test_GoPro");
    }

    //===== Pro Button Main Page =====

    @Test
    public void test7(){
        testInfo.id("test7").suite("Functionality").name("Check Pro button (from Main page).");
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.tapPro();
        photolab.store.closeStore();
    }

    //===== Go Pro Button PostProcessing =====

    @Test
    public void test8(){
        testInfo.id("test8").suite("Functionality").name("Check Demo tizer for PRO effect. (PostProcessing Go PRO now option)");
        photolab.custom.loadPictureToDevice("D:\\Base\\t1.png");
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("Overlays");
        photolab.categories.selectEffect("Light Bokeh");
        photolab.pictures.selectTab("All");
        photolab.pictures.selectPicture(1);
        photolab.property.waitToLoad();
        photolab.property.tapDone();
        photolab.goPRO.tapGoPRO();
        photolab.store.closeStore();
        photolab.goPRO.waitToLoad();
        photolab.custom.pressBack();
        photolab.pictures.waitToLoad();
        photolab.menu.open();
        photolab.menu.tapHome();
    }
}
