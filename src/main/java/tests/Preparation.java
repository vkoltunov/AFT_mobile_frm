package tests;

import api.android.Android;
import api.apps.PhotoLab.PhotoLab;
import core.MyLogger;
import core.managers.TestManager;
import core.utils.Common;
import core.utils.Config;
import core.utils.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

/**
 * Created by User on 6/27/2017.
 */
public class Preparation extends TestManager {

    private static PhotoLab photolab = Android.app.photoLab;

    @BeforeTest
    public void before() {
        testInfo.suite("Preparation");
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
    @Parameters({"Build_Path", "Build_Type"})
    public void test0(String buildPath, @Optional("true") String buildType){
        testInfo.id("test0").suite("Preparation").name("Clear application data. Uninstall current application build. Install new build.");
        //photolab.custom.clearFolderData(Config.APP_DATA_DIR+"\\photoLab\\result");
        photolab.forceStop();
        photolab.clearData();
        photolab.uninstallApp();
        photolab.installApp(buildPath, buildType);
        photolab.open();
        photolab.main.waitToLoad();
    }
}
