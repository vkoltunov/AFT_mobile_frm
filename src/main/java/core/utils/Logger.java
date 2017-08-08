package core.utils;


import java.io.File;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.Reporter;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;
//import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.EnhancedPatternLayout;

/**
 * Отвечает за логгирование всех событий
 *
 * @author vkoltunov
 */
public class Logger {

    private static final Locale DEF_LOCALE = Locale.RU;
    private static final String CURRENT_LOCALE = "awn.locale";
    private static final String reportFolderKey = "reportDir";
    private static final String reportFolder = "d:\\aft\\Report";
    private static final String CONFIG_PATH = "resources\\log4j.properties";
    private static String DEBUG_LOG;
    private final org.apache.log4j.Logger logger;
    private static final EnhancedPatternLayout LOG_PATTERN = new EnhancedPatternLayout("%d{yyyy-MM-dd HH:mm:ss,SSS}{GMT+3} [%t][%-5p]: %m%n");
    private static final String PATH = String.format("resources/localization/loc_%1$s.properties", System.getProperty(CURRENT_LOCALE,
            DEF_LOCALE.toString()).toLowerCase());
    public static final String LOG_DELIMITER = " :: ";
    public static final String LOG_BUG_MSG = "|||";
    private static final HashMap<String, Logger> instances = new HashMap<>();
    private static Properties localManager = new Properties();

    /**
     * Constructor
     */
    private Logger() {
        logger = org.apache.log4j.Logger.getLogger(getThread());
        try {
            Config.REPORT_DIR = System.getProperty(reportFolderKey, reportFolder);
            PropertyConfigurator.configure(new FileInputStream(CONFIG_PATH));
            localManager.load(new FileInputStream(PATH));
            DEBUG_LOG = Config.REPORT_DIR + File.separator + "debug_[" + getThread() + "]_" + Common.getCurrentDateTimeStamp() + ".log";
            FileAppender fa = new FileAppender(LOG_PATTERN, DEBUG_LOG);
            fa.setAppend(false);
            fa.setThreshold(Level.DEBUG);
            fa.setImmediateFlush(true);
            fa.activateOptions();
            logger.addAppender(fa);
        } catch (Exception ex) {
        }
    }

    /**
     * Implementation of the Singleton pattern
     *
     * @return Logger instance
     */
    public static Logger getInstance() {
        String name = getThread();
        if (!instances.containsKey(name)) {
            instances.put(name, new Logger());
        }
        return instances.get(name);
    }

    /**
     * Устанавливает новую локаль
     *
     * @param newLoc new Local manager
     */
    public static void setNewLocalManager(final Properties newLoc) {
        localManager = newLoc;
    }

    /**
     * Возвращает путь к логу отладки
     *
     * @return Путь к файлу
     */
    public String getDebugLogPath() {
        return DEBUG_LOG;
    }

    /**
     * Получает локаль
     *
     * @param key Ключ
     *
     * @return Locale
     */
    public static String getLoc(final String key) {
        return localManager.getProperty(key, key);
    }

    /**
     * Локализованное информационное сообщение
     *
     * @param msg Текст сообщения
     */
    public void infoLoc(final String msg) {
        String message = localManager.getProperty(msg, msg);
        logger.info(message);
        Reporter.log(message + "<br>");
    }

    /**
     * Используется для форматирования сообщений
     *
     * @param msg Текст сообщения
     */
    private void logDelimMsg(final String msg) {
        info(String.format("--------==[ %1$s ]==--------", msg));
    }

    /**
     * Выводит информацию о названии теста
     *
     * @param testName Название теста
     */
    public void logTestName(final String testName) {
        String formattedName = String.format("=====================  %1$s: '%2$s' =====================", getLoc("loc.logger.test.case"), testName);
        String delims = "";
        int nChars = formattedName.length();
        for (int i = 0; i < nChars; i++) {
            delims += "-";
        }
        info(delims);
        info(formattedName);
        info(delims);
        logDelimMsg(getLoc("loc.logger.test.preconditions"));
    }

    /**
     * Выводит в лог номер шага
     *
     * @param step Номер шага
     */
    public void step(final int step) {
        logDelimMsg(getLoc("loc.logger.step") + String.valueOf(step));
    }

    /**
     * Выводит в лог заданное кол-во точек
     *
     * @param count Количество точек
     */
    public void printDots(final int count) {
        String delims = "";
        for (int i = 0; i < count; i++) {
            delims += ".";
        }
        info(delims);
    }

    /**
     * Выводит информацию об успешном завершении теста
     *
     * @param testName Название теста
     */
    public void logTestPassed(final String testName) {
        info("");
        String formattedEnd = String.format("***** %1$s: '%2$s' %3$s! *****", getLoc("loc.logger.test.case"), testName, getLoc("loc.logger.test.passed"));
        String stars = "";
        int nChars = formattedEnd.length();
        for (int i = 0; i < nChars; i++) {
            stars += "*";
        }
        info(stars);
        info(formattedEnd);
        info(stars);
        info("");
    }

    /**
     * Парсит строку и возвращает форматированное сообщение для ReportNG
     *
     * @param msg         Исходное сообщение
     * @param defClass    CSS класс по умолчанию
     * @param targetClass CSS класс в случае наличия доп сообщения
     *
     * @return форматированное сообщение для ReportNG
     */
    private String parseMsg(Object msg, String defClass, String targetClass) {
        String colorClass = defClass;
        String message = msg.toString().replaceAll(Common.escapeRegexpChars(LOG_BUG_MSG) + ".*", "");
        String bugMsg = msg.toString().replaceAll(".*?" + Common.escapeRegexpChars(LOG_BUG_MSG), "");
        if (!bugMsg.equals("") && !bugMsg.equals(msg)) {
            //message = ecapeHTML(message) + "<br/>----------<br/>" + ecapeHTML(bugMsg) + "<br/><br/>";
            colorClass = targetClass;
        }
        return "<div class=\"" + colorClass + "\">" + message + "</div>";
    }

    /**
     * Вырезает при наличии дополнительное сообщение
     *
     * @param msg Исходное сообщение
     *
     * @return Измененное сообщение
     */
    private String getPureMsg(Object msg) {
        return msg.toString().replaceAll(Common.escapeRegexpChars(LOG_BUG_MSG) + ".*", "");
    }

//====================================================================================================================================================
//=================================================== Методы для управления уровнями логгирования ====================================================
//====================================================================================================================================================
    /**
     * Debug log
     *
     * @param message Текст сообщения
     */
    public void debug(final Object message) {
        if (Thread.currentThread().getName().startsWith("forwarding")) {
            return;
        }
        logger.debug(message);
        String msg = "<div class=\"skipped\">" + message + "</div>"; // yellow color from reportng css
        //Reporter.log(ecapeHTML(msg) + "<br>", logger.getEffectiveLevel().toInt());
    }

    /**
     * Info log
     *
     * @param message Текст сообщения
     */
    public void info(final Object message) {
        logger.info(message);
        //Reporter.log(ecapeHTML(message) + "<br>");
    }

    /**
     * Warning log
     *
     * @param message Текст сообщения
     */
    public void warn(final Object message) {
        logger.warn(getPureMsg(message));
        //String msg = "<div class=\"skipped\">" + ecapeHTML(message) + "</div>"; // orange color from reportng css
        Reporter.log(parseMsg(message, "skipped", "purple"));
    }

    /**
     * Red Warning log
     *
     * @param message Текст сообщения
     */
    public void pass(final Object message) {
        logger.info(getPureMsg(message));
        //String msg = "<div class=\"green\">" + ecapeHTML(message) + "</div>"; // green color from reportng css
        Reporter.log(parseMsg(message, "green", "blue"));
    }

    /**
     * Error log
     *
     * @param message Текст сообщения
     */
    public void error(final String message) {
        //String msg = "<div class=\"red\">" + ecapeHTML(message) + "</div>"; // red color from reportng css
        Reporter.log(parseMsg(message, "red", "purple"));
        logger.error(getPureMsg(message));
    }

    /**
     * Fatal log
     *
     * @param message Текст сообщения
     */
    public void fatal(final String message) {
        //String msg = "<div class=\"failedConfig\">" + ecapeHTML(message) + "</div>"; // red color from reportng css
        Reporter.log(parseMsg(message, "failedConfig", "purple"));
        logger.fatal(getPureMsg(message));
        Assert.assertTrue(false);
    }

    /**
     * Экранирование html символов
     *
     * @param msg Сообщение
     *
     * @return Экранированное сообщение
     */
 //   private String ecapeHTML(Object msg) {
 //       return StringEscapeUtils.escapeHtml(msg + "");
 //   }

    /**
     * Получает Имя текущего потока
     *
     * @return Имя текущего потока
     */
    private static String getThread() {
        return Thread.currentThread().getName();
    }

    //================================================================================================================================================
    /**
     * Локаль
     */
    public enum Locale {

        EN, RU
    }
}
