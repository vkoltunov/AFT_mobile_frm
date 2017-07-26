import api.android.Android;
import core.MyLogger;
import core.Reporter;
import core.managers.DriverManager;
import core.managers.TestManager;
import org.apache.log4j.Level;
import org.junit.runner.JUnitCore;

import java.io.File;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Thread.sleep;
import static junit.framework.TestCase.assertTrue;

import tests.TestsConfiguration;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by User on 3/7/2017.
 */
public class Runner {

    //private static final DateFormat sdf = new SimpleDateFormat("yyyy_MM_dd|HH:mm:ss");


    public static void main(String[] args) throws MalformedURLException, InterruptedException, ParserConfigurationException {


        MyLogger.log.setLevel(Level.INFO);

        String reportLocation = "D:\\AFT\\Results";
        TestManager.setReporter(new Reporter(new File(reportLocation+"\\result_"+new Date().getTime()+"\\report.xml")));
        try {
            DriverManager.createDriver();

            //Android.app.fabby.runTest();
            //Android.app.fabby.runToast();

            JUnitCore.runClasses(TestsConfiguration.class);
        } finally {
            DriverManager.killDriver();
        }
    }

}
