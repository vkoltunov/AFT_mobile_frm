package core.utils;

//import core.framework.base.BaseEntity;
import core.framework.base.BaseEntity;

import java.util.HashMap;

import java.util.Properties;

/**
 * Глобальное хранилище переменных
 *
 * @author vkoltunov
 */
    public class GlobalDict extends BaseEntity {
    //public class GlobalDict {

    private final Properties varMap;
    private static final HashMap<Long, GlobalDict> testGlobals = new HashMap<>(), externalGlobals = new HashMap<>(), exportGlobals = new HashMap<>();

    /**
     * Инстанс глобальных переменных теста
     *
     * @return GlobalDict
     */
    public static GlobalDict getTestInstance() {
        Long id = getTherad();
        if (!testGlobals.containsKey(id)) {
            testGlobals.put(id, new GlobalDict());
        }
        return testGlobals.get(id);
    }

    /**
     * Инстанс глобальных переменных из внешних источников
     *
     * @return GlobalDict
     */
    public static GlobalDict getExternalInstance() {
        Long id = getTherad();
        if (!externalGlobals.containsKey(id)) {
            externalGlobals.put(id, new GlobalDict());
        }
        return externalGlobals.get(id);
    }

    /**
     * Инстанс глобальных переменных для передачи между тестами
     *
     * @return GlobalDict
     */
    public static GlobalDict getExportInstance() {
        Long id = getTherad();
        if (!exportGlobals.containsKey(id)) {
            exportGlobals.put(id, new GlobalDict());
        }
        return exportGlobals.get(id);
    }

    public GlobalDict() {
        varMap = new Properties();
    }

    /**
     * Устанавливает значение переменной
     *
     * @param varName  Название переменной
     * @param varValue Значение переменной
     */
    public void set(Object varName, String varValue) {
        varMap.put(varName, varValue);
    }

    public void set(Properties properties) {
        varMap.putAll(properties);
    }

    /**
     * Удаляет значение переменной
     *
     * @param key Имя переменной
     */
    public void remove(Object key) {
        varMap.remove(key);
    }

    /**
     * Получает значение переменной
     *
     * @param varName Название переменной
     *
     * @return Значение переменной
     */
    public String get(Object varName) {
        return (String) varMap.get(varName);
    }

    public String get(String varName, String defaultValue) {
        if (varMap.getProperty(varName) != null) {
            return varMap.getProperty(varName);
        }
        return defaultValue;
    }

    /**
     * Проверяет наличие переменной в словаре
     *
     * @param varName Имя переменной
     *
     * @return true\false
     */
    public boolean hasVariable(String varName) {
        return varMap.containsKey(varName);
    }

    @Override
    protected String formatLogMsg(String message) {
        return message;
    }

    public Properties getAll() {
        return varMap;
    }

}