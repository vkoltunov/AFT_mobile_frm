import core.Listener;
import core.MyLogger;
import core.framework.Reporter;
import core.framework.base.BaseEntity;
import core.managers.DriverManager;

import static core.utils.Common.readJsonURL;
import static core.utils.Config.Keys.*;


import core.managers.TestManager;
import core.utils.Common;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by User on 3/7/2017.
 */
public class Runner extends BaseEntity {

    //private static final DateFormat sdf = new SimpleDateFormat("yyyy_MM_dd|HH:mm:ss");
    private static String dataFolder = "d:\\aft\\data";
    public static GlobalDict globalConfig = new GlobalDict();

    public static void main(String[] args) throws MalformedURLException, InterruptedException, ParserConfigurationException {


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
        } else {
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
            MyLogger.log.setLevel(Level.INFO);
            MyLogger.log.info("Path for reporter : "+ Config.REPORT_DIR);
            Listener.setReporter(new Reporter(new File(Config.REPORT_DIR+"\\result_"+new Date().getTime()+"\\report.xml")));
            DriverManager.createDriver();

            //JSONObject json = readJsonURL("http://testing.cfg.ws.pho.to/androidphotolab/conf.json");
            //Android.app.fabby.runTest();
            //Android.app.fabby.runToast();
            int res = new Runner().runTests(testSuite);
        } catch (IOException e) {
            e.printStackTrace();
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
