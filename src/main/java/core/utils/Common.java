package core.utils;

import api.android.Android;

import com.opencsv.CSVReader;
import core.MyLogger;
import core.framework.base.BaseEntity;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.json.JSONException;
import org.json.simple.JSONObject;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static core.managers.DriverManager.sdCard;
import static core.utils.Config.Keys.*;

import java.util.regex.Matcher;

/**
 * Содержит общие функции для работы с файловой системой и утилитами
 *
 * @author vkoltunov
 */
public class Common extends BaseEntity {

    private final Random RND;
    private final GlobalDict testGlobals = GlobalDict.getTestInstance();
    private static final Pattern SPECIAL_REGEXP_CHARS = Pattern.compile("[{}()\\[\\].+*?^$\\\\|]");
    private static final String ABSOLUTE_FILE_PATH_PATTERN = "^\\w:\\\\.*|^\\\\\\\\\\d+\\.\\d+\\.\\d+\\.\\d+\\\\.*";
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    private static String aclPackage = "net.sanapeli.adbchangelanguage";
    public static final String aclPath = System.getProperty("user.dir")+"/src/main/resources/ACL.apk";

    public Common() {
        RND = new Random();
        //oReporter = Reporter.getInstance();
    }

    /**
     * Возвращает имя файла, в который будут экспортированы переменные. Имя XML
     * файла, в который записываются значения переменных, зависит от режима
     * запуска из Jazz, автономно, из запускалки планов (командной строки или
     * web)
     *
     * @return имя файла экпорта/импорта
     */
    public String getExportVarsFilePath() {
        String sRunMode = "";

        String filePath = globalConfig.get(DATA_DIR) + File.separator + Config.MANUAL_VARDATA_FILE_PATH;
        //создаем папки, если их нет
        new File(filePath).getParentFile().mkdirs();
        MyLogger.log.info("Имя файла переменных: " + filePath);
        return filePath;
    }

    /**
     * Задержка в заданное кол-во секунд
     *
     * @param seconds Кол-во секунд
     */
    public void doTimeout(String seconds) {
        try {
            doTimeout((int) (Double.parseDouble(seconds) * 1000));
        } catch (NumberFormatException e) {
            MyLogger.log.error("Неверный формат параметра 'delay': " + seconds + ". " + e.getMessage());
        }

    }

    /**
     * Задержка в заданное кол-во в миллисекундах
     *
     * @param ms Кол-во секунд
     */
    public void doTimeout(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            error(ex.getClass() + ": " + ex.getMessage());
        }
    }

    /**
     * Получает матч по регекспу, по задданному паттерну и номеру группы
     *
     * @param string Строка для поиска
     * @param regexp Паттерн
     * @param group Номер группы из матча
     *
     * @return Искомая строка
     */
    public static String getMatch(String string, String regexp, int group) {
        Matcher m = Pattern.compile(regexp, Pattern.DOTALL).matcher(string);
        if (m.find() && m.groupCount() <= group) {
            return m.group(group);
        }
        return "";
    }

    /**
     * Получает матч по регекспу, по задданному паттерну
     *
     * @param string Строка для поиска
     * @param regexp Паттерн
     *
     * @return Искомая строка
     */
    public static String getMatch(String string, String regexp) {
        Matcher m = Pattern.compile(regexp, Pattern.DOTALL).matcher(string);
        if (m.find()) {
            return m.group(0);
        }
        return "";
    }


    //======================================================= Работа с URL =============================================================

    public static int getResponseCode(String urlString) throws MalformedURLException, IOException {
        URL u = new URL(urlString);
        HttpURLConnection huc =  (HttpURLConnection)  u.openConnection();
        huc.setRequestMethod("GET");
        huc.connect();
        return huc.getResponseCode();
    }


    public static Boolean urlValidation(String url) {
        try {
            int responseCode = getResponseCode(url);
            if (200 <= responseCode && responseCode <= 399){
                //MyLogger.log.info("Response code is "+responseCode+". URL is work correctly.");
                return true;
            } else {
                //MyLogger.log.error("Response code is "+responseCode+". URL is not work correctly.");
                return false;
            }
        } catch (IOException exception) {
            MyLogger.log.error("URL " +url+ " is not valid.");
            return false;
        }
    }

    //======================================================= Работа с локализациями =============================================================

    public static void changeDeviceLoc(String lang) {

        ArrayList apps = Android.adb.getInstalledPackages();
        if(!apps.contains(aclPackage)){
            Android.adb.installApp(aclPath);
        }
        else MyLogger.log.info("Device has "+aclPackage+" installed.");
        Android.adb.changeDeviceLanguage(lang);
    }


    //======================================================= Работа с Файловой системой =============================================================

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("json", jsonText);

            JSONObject json = new JSONObject(hashMap);
            return json;
        } finally {
            is.close();
        }
    }

    public static JSONObject readJsonURL(String url) throws IOException, JSONException {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("json", IOUtils.toString(new URL(url), Charset.forName("UTF-8")));
        JSONObject json = new JSONObject(hashMap);
        return json;
    }

    /**
     * Загружаем файл CSV и отдаём ридер.
     *
     */
    public static CSVReader readCSV(String url) throws IOException {

        CSVReader reader;

        if (url.contains("http")){
            URL stockURL = new URL(url);
            BufferedReader in = new BufferedReader(new InputStreamReader(stockURL.openStream()));
            reader = new CSVReader(in);
        } else {
            reader = new CSVReader(new FileReader(url));
        }
        return reader;
    }


    /**
     * Скчиваем файл по указанному URL.
     *
     */
    public static boolean downloadUsingStream(String urlStr, String file){
        try {
            URL url = new URL(urlStr);
            BufferedInputStream bis = new BufferedInputStream(url.openStream());
            FileOutputStream fis = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = bis.read(buffer, 0, 1024)) != -1) {
                fis.write(buffer, 0, count);
            }
            fis.close();
            bis.close();
            return true;
        }catch (IOException e) {
            MyLogger.log.info("Failed to download file ...");
            return  false;
        }
    }

    /**
     * Сравнивает два файла изображения на идентичность.
     *
     * @return true/false как результат сравнения
     */
    public static boolean compareImage(File fileA, File fileB) {
        try {
            // take buffer data from botm image files //
            BufferedImage biA = ImageIO.read(fileA);
            DataBuffer dbA = biA.getData().getDataBuffer();
            int sizeA = dbA.getSize();
            BufferedImage biB = ImageIO.read(fileB);
            DataBuffer dbB = biB.getData().getDataBuffer();
            int sizeB = dbB.getSize();
            // compare data-buffer objects //
            if(sizeA == sizeB) {
                for(int i=0; i<sizeA; i++) {
                    if(dbA.getElem(i) != dbB.getElem(i)) {
                        return false;
                    }
                }
                return true;
            }
            else {
                return false;
            }
        }
        catch (Exception e) {
            MyLogger.log.info("Failed to compare files ...");
            return  false;
        }
    }

    /**
     * Читает текст из файла
     *
     * @param filePath Полный путь к файлу, например d:\test.txt
     * @param encoding Кодировка, например UTF-8
     *
     * @return текст из файла
     */
    public String readFile(String filePath, String encoding) {
        if (!filePath.matches(ABSOLUTE_FILE_PATH_PATTERN)) {
            filePath = globalConfig.get(APP_DATA_DIR) + File.separator + filePath;
        }
        String text = "";
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath)), encoding))) {
            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }
            br.close();
            text = sb.toString().substring(0, sb.length() - 1);
        } catch (UnsupportedEncodingException e) {
            MyLogger.log.error("Ошибка чтения файла в кодировке: " + encoding + " : " + e.getMessage());
        } catch (IOException e) {
            MyLogger.log.error("Текст из файла '" + filePath + "' в кодировке '" + encoding + "' не прочитан: " + e.getMessage());
        }
        MyLogger.log.debug("Прочитан текст из файла '" + filePath + "': " + text);
        return text;
    }

    /**
     * Пишет текст в файл
     *
     * @param textToWrite Текст для записи в файл
     * @param filePath Полный путь к файлу, например d:\test.txt
     * @param encoding Кодировка, например UTF-8
     *
     * @return true\false
     */

    public boolean writeFile(String textToWrite, String filePath, String encoding) {
        if (!filePath.matches(ABSOLUTE_FILE_PATH_PATTERN)) {
            filePath = globalConfig.get(APP_DATA_DIR) + File.separator + filePath;
        }
        try {
            FileOutputStream fos = new FileOutputStream(new File(filePath));
            Writer wr = new BufferedWriter(new OutputStreamWriter(fos, encoding));
            wr.append(textToWrite.replace("\\n", "\n").replace("\\r", "\r"));
            wr.flush();
            wr.close();
        } catch (UnsupportedEncodingException e) {
            MyLogger.log.error("Ошибка записи файла в кодировке: " + encoding);
            return false;
        } catch (IOException e) {
            MyLogger.log.error("Не записан текст '" + textToWrite + "' в файл '" + filePath + "' в кодировке: " + encoding);
            return false;
        }
        MyLogger.log.info("Текст записан в файл '" + filePath + "' в кодировке: " + encoding);
        return true;
    }

    /**
     * Копирует файл
     *
     * @param source Копируемый файл
     * @param dest Путь к новому файлу
     *
     * @return true\false
     */
    public boolean copyFile(String source, String dest) {
        MyLogger.log.info("Копируем файл '" + source + "' в '" + dest + "'");
        try {
            if (source.matches(".*\\*\\..*")) {
                String filter = source.replaceAll(".*\\*(\\..*)", "$1");
                source = source.replaceAll("(.*\\\\).*", "$1");
                IOFileFilter iff = FileFilterUtils.suffixFileFilter(filter);
                FileUtils.copyDirectory(new File(source), new File(dest), iff);
            } else {
                File destFile = new File(dest);
                if (destFile.isDirectory()) {
                    FileUtils.copyFileToDirectory(new File(source), destFile);
                } else {
                    FileUtils.copyFile(new File(source), destFile);
                }
            }
            return true;
        } catch (IOException ex) {
            debug("Can't copy file: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Проверяет, является источник данных ссылкой на файл или текстом
     *
     * @param source Источник данных (ссылка на файл или текст)
     * @param ext Расширение возможного файла, например xml
     *
     * @return true\false
     */
    public static boolean isFile(String source, String ext) {
        return source.length() > ext.length() && (source.toLowerCase().substring(source.length() - (ext.length() + 1)).equals("." + ext.toLowerCase()));
    }

    /**
     * Проверяет, существует ли заданный файл
     *
     * @param path Путь к файлу
     *
     * @return true\false
     */
    public static boolean isFileExist(String path) {
        File file = new File(path);
        return file.exists() && file.isFile();
    }

    /**
     *
     * Распаковывает .exe файлы из текущего JAR в текущую папку и удаляет после
     * использования
     *
     * @param fileName Имя файла внутри JAR
     *
     * @return URL распакованного файла в файловой системе
     * @throws MalformedURLException
     */
    public URL extract(final String fileName) throws MalformedURLException {
        File tempFile = null;
        ZipFile zipFile = null;
        final ZipEntry entry;
        InputStream zipStream = null;
        OutputStream fileStream;

        try {
            tempFile = new File("temp.exe");
            zipFile = new ZipFile(new File(getJarURI()));
        } catch (IOException ex) {
            ex.printStackTrace();
            MyLogger.log.error("cannot create temp file: " + tempFile);
        }
        tempFile.deleteOnExit();
        entry = zipFile.getEntry(fileName);

        if (entry == null) {
            MyLogger.log.error("cannot find file: " + fileName + " in archive: " + zipFile.getName());
        }

        try {
            zipStream = zipFile.getInputStream(entry);
        } catch (IOException | NullPointerException ex) {
            ex.printStackTrace();
        }
        fileStream = null;

        try {
            final byte[] buf;

            fileStream = new FileOutputStream(tempFile);

            buf = new byte[1024];
            int i = 0;

            while ((i = zipStream.read(buf)) != -1) {
                fileStream.write(buf, 0, i);
            }
            zipFile.close();
        } catch (IOException | NullPointerException ex) {
            ex.printStackTrace();
        } finally {
            close(zipStream);
            close(fileStream);
        }
        return (tempFile.toURI().toURL());
    }

    /**
     * Получать URI текущего jar файла
     *
     * @return URI
     */
    private URI getJarURI() {
        final ProtectionDomain domain;
        final CodeSource source;
        final URL url;
        URI uri = null;

        domain = Common.class.getProtectionDomain();
        source = domain.getCodeSource();
        url = source.getLocation();
        try {
            uri = url.toURI();
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
        return uri;
    }

    /**
     * Закрывает потоковые объекты
     *
     * @param stream Поток
     */
    private void close(final Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Экранирует спец символы регулярных выражений
     *
     * @param text Текст
     *
     * @return Экранированный текст
     */
    public static String escapeRegexpChars(String text) {
        return SPECIAL_REGEXP_CHARS.matcher(text).replaceAll("\\\\$0");
    }

    /**
     * Проверяет, есть у заданного имени файла необходимое расширение и, если
     * нет, добавляет его
     *
     * @param filePath Путь к файлу
     * @param ext Расширение
     *
     * @return Готовый путь
     */
    public static String checkFileExtension(String filePath, String ext) {
        String extEx = ".".concat(ext);
        if (!filePath.endsWith(extEx)) {
            filePath += extEx;
        }
        return filePath;
    }

    /**
     * Converts StackTrace to String
     *
     * @param exception Throwable exception
     *
     * @return StackTrace as String
     */
    public static String getStackTrace(Throwable exception) {
        String stackTrace = "";
        for (StackTraceElement stack : exception.getStackTrace()) {
            stackTrace += "\t" + stack.toString() + "\n";
        }
        return stackTrace;
    }

    //======================================================= Работа с Датами ========================================================================
    /**
     * Получает указанную дату в заданном формате
     *
     * @param date Дата
     * @param pattern Формат отображения
     *
     * @return Форматированная дата
     */
    public static String getFormattedDate(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * Получает текущую дату в заданном формате
     *
     * @param pattern патерн для генерации результата.
     *
     * @return Формат отображения
     */
    public String getCurrentDateFormatted(String pattern) {
        return getFormattedDate(new Date(), pattern);
    }

    /**
     * Получает текущую дату в формате "yyyy_MM_dd__HH_mm_ss"
     *
     * @return форматированная строка текущей даты
     */
    public static String getCurrentDateTimeStamp() {
        return getFormattedDate(new Date(), "yyyy_MM_dd__HH_mm_ss_SSS");
    }

    /**
     * Возвращает строку, представляющую заданный тип даты
     *
     * @param delta Дата или смещение относительно текущей даты
     * @param calendarType Тип смещения (день, месяц, год и т.д.)
     *
     * @return Дата с заданным смещением
     */
    private String getDateString(String delta, int calendarType) {
        Calendar cal = Calendar.getInstance();

        if (delta.length() == 0) {
            delta = "0"; // set default = 0;
        }
        try {
            cal.setTime(DateFormat.getInstance().parse(delta));
        } catch (ParseException ex) {
            cal.add(calendarType, Integer.parseInt(delta));
        }
        return String.valueOf(cal.get(calendarType));
    }

    /**
     * Добавляет к заданной дате заданный промежуток времени
     *
     * @param date Дата. Может быть пустой, в этом случае берется текущая
     * @param type Тип интервала времени. 'y' - год, 'M' - месяц, 'd' - день
     * @param value Значение интервала времени
     * @param format Формат даты, по умолчанию: yyyy-MM-dd
     *
     * @return Дата со смещением
     */
    public String dateAdd(String date, String type, String value, String format) {
        Calendar cal = Calendar.getInstance();
        int dateType = Calendar.DATE;
        String dateFormat = format;

        if (format.equals("")) {
            dateFormat = DEFAULT_DATE_FORMAT;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

        if (!date.equals("")) {
            try {
                cal.setTime(sdf.parse(date));
            } catch (ParseException ex) {
                MyLogger.log.error("Неверный формат даты: '" + format + "' - " + date);
            }
        }
        switch (type) {
            case "y":
                dateType = Calendar.YEAR;
                break;
            case "M":
                dateType = Calendar.MONTH;
                break;
            case "d":
                dateType = Calendar.DAY_OF_MONTH;
                break;
            default:
                MyLogger.log.error( "Не указан тип смещения даты");
        }
        cal.add(dateType, Integer.parseInt(value));

        return sdf.format(cal.getTime());
    }

    /**
     * Делаем скриншот экрана и сохроняем
     */
    public static void grabScreenShot() {
        //Из Environment извлекаем step id
        String sStepId = GlobalDict.getTestInstance().get("awn_CurrentStepId");
        String isGrab = globalConfig.get(GRAB_SCRENSHOOT);
        if (isGrab != null && !Boolean.parseBoolean(isGrab)) {
            MyLogger.log.debug("Не делаем снимок экрана ");
            return;
        }
        MyLogger.log.debug(" Делаем снимок экрана ");

        String fileName = getCurrentDateTimeStamp() + "_" + "ScreenOnStep_" + sStepId + ".png";

        takeDeviceScreenshot(TESTNG_OUTPUT_HTML + fileName);
        MyLogger.log.info(String.format("<a href='%1$s'>" + " снимок экрана для шага  " + sStepId + "<br> <img src = '%1$s' height='100'/>  </a>", fileName)); //for second report
        MyLogger.log.debug(" снимок экрана сохранен в файл " + fileName);
    }

    public static void takeDeviceScreenshot(String fileDest){
        Android.adb.takeScreenshot("/storage/"+sdCard+"/Pictures/screen1.png");
        Android.adb.pullFile("/storage/"+sdCard+"/Pictures/screen1.png", fileDest);
        Android.adb.deleteFile("/storage/"+sdCard+"/Pictures/screen1.png");
    }

    //======================================================= Генерация данных =======================================================================
    /**
     * @param inputSet -- дает указание из каких символов генерировать слово "Я"
     * -- только руский большие "z" -- только английский маленькие "я" -- только
     * руский маленькие "Z" -- толькоанглийский большие "1" -- только цифры "яЯ"
     * -- руский большие и маленькие "!" -- только восклицательный знак "Z@#$%^"
     * -- английский большие и символы @#$%^ если "" то руские большие и
     * маленькие
     *
     * @param length -- длинна слова генеренного (если "" пусто то 10)
     *
     * @return возращаяет сгенерированную строку
     */
    public String getRandomString(String inputSet, String length) {
        final String ruLow = "йцукенгшщзхъфывапролджэячсмитьбю";
        final String ruHi = ruLow.toUpperCase();
        final String engLow = "qwertyuiopasdfghjklzxcvbnm";
        final String digits = "1234567890";
        final String engHi = engLow.toUpperCase();
        final String enLowMark = "z";
        final String enHiMark = "Z";
        final String ruHiMark = "Я";
        final String ruLowMark = "я";
        final String digitMask = "1";
        final String[] charsSet = {ruLow, ruHi, engLow, engHi, digits};
        final String[] masks = {ruLowMark, ruHiMark, enLowMark, enHiMark, digitMask};

        String totalChars = "";
        String inSet = inputSet;
        for (int i = 0; i < charsSet.length; i++) {
            String set = charsSet[i];
            String mask = masks[i];
            if (inSet.contains(mask)) {
                totalChars += set;
                inSet = inSet.replace(mask, "");
            }
        }
        int len = 10;

        if (length.trim().length() != 0) {
            try {
                len = Integer.parseInt(length.trim());
            } catch (Exception ignored) {
            }
        }

        totalChars += inSet;
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(totalChars.charAt(RND.nextInt(totalChars.length())));
        }
        return sb.toString();
    }

    /**
     * @param delta -- на сколько смещать возрощаемый результат (по дефолту 0
     *
     * @return возрощает номер дня в месяце смещенным на дельта
     */
    public String getDayNumOfMonth(String delta) {
        return getDateString(delta, Calendar.DAY_OF_MONTH);
    }

    /**
     * @param delta -- на сколько смещать возрощаемый результат (по дефолту 0)
     *
     * @return возрощает номер месяца смещенным на дельта
     */
    public String getMonthNum(String delta) {
        if (delta.length() == 0) {
            delta = "0"; // set default = 0;
        }
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, Integer.parseInt(delta));
        return String.valueOf((cal.get(Calendar.MONTH)) + 1);
    }

    /**
     * @param delta -- на сколько смещать возрощаемый результат (по дефолту 0
     *
     * @return возрощает номер года смещенным на дельта
     */
    public String getYearNum(String delta) {
        return getDateString(delta, Calendar.YEAR);
    }

    /**
     * Функция генерации случайного числа с inFrom о inTo
     *
     * @param from -- с какого числа генерить число по дефолту = 0
     * @param to -- по какой число генерить число по дефолту = 10
     *
     * @return возращает число в диапозоне from..to
     */
    public String getRandomInt(String from, String to) {
        int _from, _to;
        try {
            _from = Integer.parseInt((from.length() > 0) ? from : "0");
            _to = Integer.parseInt((to.length() > 0) ? to : "10");
        } catch (Exception e) {
            _from = 0;
            _to = 10;
        }
        if (_from > _to) {
            int swap = _to;
            _to = _from;
            _from = swap;
        }
        return String.valueOf(_from + RND.nextInt(_to - _from));
    }

    //================================================================================================================================================
    //================================================================================================================================================
    @Override
    protected String formatLogMsg(String message) {
        return message;
    }

}
