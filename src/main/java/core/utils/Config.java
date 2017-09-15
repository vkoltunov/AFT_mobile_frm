package core.utils;

import java.util.ArrayList;

/**
 * Created by User on 8/2/2017.
 */
public class Config {

    //consts
    public static int IMPLICITLY_TIMEOUT = 15;   // 15 sec
    public static int PAGELOAD_TIMEOUT = 30;   // 30 sec
    public static final String TEST_TEST_SUITE = "test.test_suite";
    public static final String MANUAL_VARDATA_FILE_PATH = "..\\data\\VarsManualRun.xml";
    public static String REPORT_DIR = "D:\\AFT\\reports";
    public static final String APP_DATA_DIR = "D:\\AFT\\appdata";

    public enum Keys {

        DATA_DIR, APP_DATA_DIR, DATA_FILE, GRAB_SCRENSHOOT, REPORT_DIR
    }
}
