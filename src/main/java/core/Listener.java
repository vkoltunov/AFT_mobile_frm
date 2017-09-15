package core;

import api.android.Android;
import core.framework.Reporter;
import core.managers.TestManager;
import org.junit.runner.Runner;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import static core.managers.DriverManager.sdCard;

/**
 * Created by User on 8/9/2017.
 */
public class Listener extends TestListenerAdapter {

    private static Reporter reporter;
    public Object logcatPid;
    private String logName;

    @Override
    public void onTestStart(ITestResult tr) {
        MyLogger.log.info("Test Started....");

        logcatPid = Android.adb.startLogcat("aft_log",null);
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        MyLogger.log.info("Test Passed.");
        TestInfo.printResults();
        Android.adb.stopLogcat(logcatPid);
        Android.adb.deleteFile("/storage/"+sdCard+"/aft_log.txt");
        if (Listener.reporter != null)
            Listener.reporter.update(TestInfo.suite(), TestInfo.name(), "PASS");
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        if (tr.getMethod().getRetryAnalyzer() != null) {
            Retry retryAnalyzer = (Retry)tr.getMethod().getRetryAnalyzer();
            Android.adb.stopLogcat(logcatPid);
            Android.adb.deleteFile("/storage/"+sdCard+"/aft_log.txt");
            if(retryAnalyzer.isRetryAvailable()) {
                MyLogger.log.info("Test '" +TestInfo.name()+ "' failed and will be run again.");
                //tr.setStatus(ITestResult.SKIP);
            } else {
                tr.setStatus(ITestResult.FAILURE);
                MyLogger.log.info("Test Failed.");
                TestInfo.printResults();
                if (Listener.reporter != null)
                    Listener.reporter.update(TestInfo.suite(), TestInfo.name(), "FAIL");
            }
        }
    }

    public static void setReporter(Reporter reporter){
        Listener.reporter = reporter;
    }

}
