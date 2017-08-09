package core;

import core.framework.Reporter;
import core.managers.TestManager;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * Created by User on 8/9/2017.
 */
public class Listener extends TestListenerAdapter {

    private static Reporter reporter;

    @Override
    public void onTestStart(ITestResult tr) {
        MyLogger.log.info("Test Started....");
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        MyLogger.log.info("Test Passed.");
        TestInfo.printResults();
        if (Listener.reporter != null)
            Listener.reporter.update(TestInfo.suite(), TestInfo.name(), "PASS");
    }

    @Override
    public void onTestFailure(ITestResult tr) {

        MyLogger.log.info("Test Failed.");
        TestInfo.printResults();
        if (Listener.reporter != null)
            Listener.reporter.update(TestInfo.suite(), TestInfo.name(), "FAIL");
    }

    public static void setReporter(Reporter reporter){
        Listener.reporter = reporter;
    }

}
