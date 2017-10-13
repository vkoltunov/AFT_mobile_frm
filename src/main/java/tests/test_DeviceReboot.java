package tests;

import api.android.Android;
import api.apps.PhotoLab.PhotoLab;
import core.managers.DriverManager;
import core.managers.TestManager;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;

/**
 * Created by User on 6/27/2017.
 */
public class test_DeviceReboot extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    @BeforeTest
    public void before() {
        testInfo.suite("test_DeviceReboot");
    }

//===== Clear data. Install application build for device.  =====

    //@Test
    //public void insta_test(){
    //    Android.app.instagram.runTest();
    //}

    /**
     * Обработка входящих параметров
     *
     */
    @org.testng.annotations.Test
    public void test1() throws InterruptedException, MalformedURLException {
        testInfo.id("test1").suite("test_DeviceReboot").name("Reboot device + check PhotoLab correctly working.");
        photolab.forceStop();
        photolab.open();
        photolab.main.waitToLoad();
        photolab.main.selectCategoryBar("Categories");
        photolab.main.selectCategory("New Reality");
        Android.adb.rebootDevice();
        Thread.sleep(60000);
        photolab.open();
        photolab.main.waitToLoad();
        photolab.custom.checkErrorMessageForLogcat();
        photolab.main.selectCategoryBar("Categories");
        photolab.custom.checkErrorMessageForLogcat();
    }
}
