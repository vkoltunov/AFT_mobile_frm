package core.managers;


import core.MyLogger;
import core.Retry;
import core.TestInfo;
import core.framework.Reporter;
import core.framework.base.BaseEntity;
import core.utils.Common;
import core.utils.GlobalDict;
import core.utils.Logger;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static core.utils.Config.Keys.GRAB_SCRENSHOOT;

/**
 * Created by User on 3/28/2017.
 */
public class TestManager extends BaseEntity{

    public static TestInfo testInfo = new TestInfo();
    private static Reporter reporter;

    protected String xmlTest;

    //Глобальные переменные
    protected GlobalDict testGlobals;         //Переменные теста
    protected GlobalDict oDictExternal;       //Переменные окружения
    protected String xmlParams;               //Переменные, заданные через TestNG

    private Common commonFunc;
    private List<String> xmlParamKeys;

    //const
    protected static final String EMPTY = "empty";
    private static final String PARAM_GLOBAL_DELIM = "\\|\\|";
    private static final String PARAM_DELIM = "\\|";
    private static final int TIMEOUT = 10000;           // 10 sec

    /**
     * Получает список входящих параметров
     *
     * @param paramStr Строка параметров
     *
     * @return HashMap<String String> параметров
     */
    protected HashMap<String, String> parseParameters(String paramStr) {
        HashMap<String, String> params = null;
        if (!paramStr.equals("empty")) {
            params = new HashMap<>();
            String[] vars = paramStr.split("::");
            String[] values = vars[1].split(";");
            int i = 0;
            for (String name : vars[0].split(";")) {
                params.put(name, values[i++]);
            }
        }
        return params;
    }

    @Rule
    public Retry retry = new Retry(1);

    @BeforeTest
    protected void before(final ITestContext itc) {
        Thread.currentThread().setName("Thread-" + getTherad());
        testInfo.reset();
    }

    @Rule
    public TestRule listen = new TestWatcher() {
        @Override
        public void failed(Throwable t, Description description){
            MyLogger.log.info("Test Failed.");
            TestInfo.printResults();
            if(TestManager.reporter != null)
                TestManager.reporter.update(TestInfo.suite(), TestInfo.name(), "FAIL");
        }

        @Override
        public void succeeded(Description description){
            MyLogger.log.info("Test Passed.");
            TestInfo.printResults();
            if(TestManager.reporter != null)
                TestManager.reporter.update(TestInfo.suite(), TestInfo.name(), "PASS");
        }
    };

    /**
     * Подготовка основных параметров теста
     */
    protected void setupTest() {
        testGlobals = GlobalDict.getTestInstance();
        oDictExternal = GlobalDict.getExternalInstance();

        //Заполняем Dictionary переменными из Environment (CI)
        for (Map.Entry<String, String> entrySet : System.getenv().entrySet()) {
            String key = entrySet.getKey().toLowerCase();
            String value = entrySet.getValue();
        }
        //Заполняем Dictionary переменными из TestNG xml
        if (!xmlParams.equals(EMPTY)) {
            xmlParamKeys = new ArrayList<>();
            String[] paramsInfo = xmlParams.split(PARAM_GLOBAL_DELIM);
            String[] paramNames = paramsInfo[0].split(PARAM_DELIM);
            String[] paramValues = paramsInfo[1].split(PARAM_DELIM);
            for (int i = 0; i < paramNames.length; i++) {
                oDictExternal.set(paramNames[i].toLowerCase(), paramValues[i]);
                xmlParamKeys.add(paramNames[i].toLowerCase());
            }
        }
        commonFunc = new Common();
    }

    @AfterTest
    protected void afterTest() {
        try {
            //Удаляем из списка глобальных переменных те, которые были импортированы через testNG xml
            for (String xmlParamKey : xmlParamKeys) {
                oDictExternal.remove(xmlParamKey);
            }
        } catch (NullPointerException ex) {
        }
    }

    @Override
    protected String formatLogMsg(String message) {
        return message;
    }

    public static void setReporter(Reporter reporter){
        TestManager.reporter = reporter;
    }

}
