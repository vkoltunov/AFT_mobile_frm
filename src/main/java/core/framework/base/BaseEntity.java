package core.framework.base;

import api.android.Android;
import core.utils.Config;
import core.utils.GlobalDict;
import core.utils.Logger;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static core.managers.DriverManager.sdCard;

/**
 * BaseEntity
 */
public abstract class BaseEntity {

    protected static final String TESTNG_OUTPUT_HTML = "test-output\\html\\";
    protected static GlobalDict globalConfig = new GlobalDict();    //Общие настройки для всего комплекта

    /**
     * Получает id текущего треда
     *
     * @return Current Thread id
     */
    protected static Long getTherad() {
        return Thread.currentThread().getId();
    }

    /**
     * Получить локаль
     *
     * @param key Ключ
     *
     * @return Значение
     */
    protected static String getLoc(final String key) {
        return Logger.getLoc(key);
    }

    // ==============================================================================================
    // Методы для поддержки логирования
    /**
     * Абстрактный метод
     *
     * @param message Сообщение
     *
     * @return Форматированное сообщение
     */
    protected abstract String formatLogMsg(String message);

    /**
     * Сообщение для отладки
     *
     * @param message Сообщение
     */
    protected final void debug(final Object message) {
        Logger.getInstance().debug(String.format("[%1$s] %2$s", this.getClass().getSimpleName(), formatLogMsg(String.valueOf(message))));
    }

    /**
     * Информативное сообщение
     *
     * @param message Сообщение
     */
    protected void info(Object message) {
        Logger.getInstance().info(formatLogMsg(String.valueOf(message)));
    }

    /**
     * Предупреждение
     *
     * @param message Сообщение
     */
    protected void warn(final String message) {
        Logger.getInstance().warn(formatLogMsg(message));
    }

    /**
     * Сообщение об ошибке (без остановки теста)
     *
     * @param message Сообщение
     */
    protected void error(final String message) {
        Logger.getInstance().error(formatLogMsg(message));
    }

    /**
     * Сообщение об успехе
     *
     * @param message Сообщение
     */
    protected void pass(final String message) {
        Logger.getInstance().pass(formatLogMsg(message));
    }

    /**
     * Сообщение об ошибке и выход из теста
     *
     * @param message Сообщение
     */
    protected void fatal(final String message) {
        //Common.grabScreenShot();                          -StackOverflow Exception here
        Logger.getInstance().fatal(formatLogMsg(message));
    }

    // ==============================================================================================
    /**
     * До создания класса Before Class method
     */
    @BeforeClass
    public void before() {
        System.setProperty("org.uncommons.reportng.escape-output", "false");
        System.setProperty("org.uncommons.reportng.title", "Report NG Results");
        System.setProperty("org.uncommons.reportng.invoke-analyz", "false");
        System.setProperty("org.uncommons.reportng.analyz", "DATA_ERROR,SERVER_ERROR,ELEMENT_ABSENT,UNKNOWN_ERROR");
        System.setProperty("org.uncommons.reportng.excel-report", "false");
        System.setProperty("org.uncommons.reportng.custom-report-dir", "reports");
        System.setProperty("org.uncommons.reportng.everystepscreen", "false");
    }

}