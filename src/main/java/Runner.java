import core.MyLogger;
import core.framework.Reporter;
import core.framework.base.BaseEntity;
import core.managers.DriverManager;

import static core.utils.Config.Keys.*;


import core.managers.TestManager;
import core.utils.Config;
import core.utils.GlobalDict;
import core.utils.TestNGParser;
import org.apache.log4j.Level;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;


import static java.lang.Thread.sleep;
//import static junit.framework.TestCase.assertTrue;
//import static sun.plugin2.util.SystemUtil.debug;

import org.testng.TestNG;
import org.testng.xml.XmlSuite;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by User on 3/7/2017.
 */
public class Runner extends BaseEntity {

    //private static final DateFormat sdf = new SimpleDateFormat("yyyy_MM_dd|HH:mm:ss");
    private static String dataFolder = "d:\\aft\\data";
    public static GlobalDict globalConfig = new GlobalDict();


    public static void main(String[] args) throws MalformedURLException, InterruptedException, ParserConfigurationException {


        MyLogger.log.setLevel(Level.INFO);
        Properties prop;
        String testSuite = "data/photoLab/test.xml";

        // file properties
        if (args.length > 0 && args[0].endsWith(".properties")) {
            prop = loadProperties(args);
            globalConfig.set(prop);
            dataFolder = prop.getProperty("test.data_folder");

            if (prop.getProperty(core.utils.Config.TEST_TEST_SUITE) != null) {
                testSuite = prop.getProperty(core.utils.Config.TEST_TEST_SUITE);
            }
        } // inline arguments
        else {
            if (args.length > 0 && !args[0].equals("")) {
                testSuite = args[0].replaceAll("(.*).xml", "$1").concat(".xml");
            }

            if (args.length > 1) {
                dataFolder = args[1];
                if (dataFolder.endsWith("\\")) {
                    dataFolder = dataFolder.substring(0, dataFolder.length() - 1);
                }
            }
        }
        globalConfig.set(DATA_DIR, dataFolder);
        globalConfig.set(APP_DATA_DIR, globalConfig.get(DATA_DIR) + "\\..\\appdata");


        TimeZone.setDefault(TimeZone.getTimeZone("GMT+3"));

        try {
            TestManager.setReporter(new Reporter(new File(Config.REPORT_DIR+"\\result_"+new Date().getTime()+"\\report.xml")));
            DriverManager.createDriver();
            //Android.app.fabby.runTest();
            //Android.app.fabby.runToast();
            int res = new Runner().runTests(testSuite);
        } finally {
            DriverManager.killDriver();
        }
    }

    private static Properties loadProperties(String[] args) {
        Properties totalProperties = new Properties();

        for (String arg : args) {
            Properties prop = new Properties();
            try {
                prop.load(new FileReader(arg));
                totalProperties.putAll(prop);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return totalProperties;
    }

    /**
     * Запускает указанный xml
     *
     * @param pathToSuite Путь к файлу TestNG XML
     *
     * @return Код ошибки
     */
    public int runTests(String pathToSuite) {
        TestNG testNG = new TestNG();
        List<XmlSuite> list;

        XmlSuite suite;
        list = new TestNGParser(pathToSuite).parseToList();

        testNG.setXmlSuites(list);
        testNG.run();

        return testNG.hasFailure() ? -1 : 0;
    }

    @Override
    protected String formatLogMsg(String message) {
        return message;
    }

}
