//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package api.apps.PhotoLab;

import core.MyLogger;
import io.appium.java_client.android.AndroidDriver;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import javax.net.ssl.HttpsURLConnection;
import org.apache.commons.io.IOUtils;
import org.apache.http.util.TextUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;


public class PushNotificationTools {
    private static final String TOKEN_MAGIC1 = "token = ";
    private static final JSONObject TEST_DATA_JSON = new JSONObject();

    public PushNotificationTools() {
    }

    public static boolean sentData(AndroidDriver dr, JSONObject data) throws MalformedURLException, InterruptedException {
        String token = getToken(dr);

        if(token != null) {
            return sendFCM(token, data);
        } else {
            MyLogger.log.error("Token not found");
            return false;
        }
    }

    public static boolean sentTestData(AndroidDriver dr) throws MalformedURLException, InterruptedException {
        return sentData(dr, TEST_DATA_JSON);
    }

    public static String getToken(AndroidDriver dr) {
        String token = null;
        String tokenRes = null;
        LogEntries logEntries = dr.manage().logs().get("logcat");
        Iterator iterator = logEntries.iterator();

        while(iterator.hasNext()) {
            LogEntry entry = (LogEntry)iterator.next();
            //MyLogger.log.debug("entry: " + entry);
            if((token = checkLogEntry(entry, ", token = ")) != null) {
                tokenRes = token;
                //break;
            }
        }
        return tokenRes;
        //return token;
    }

    private static String checkLogEntry(LogEntry entry, String TOKEN_MAGIC) {
        String token = null;
        if(entry != null && entry.getMessage() != null && entry.getMessage().contains(TOKEN_MAGIC)) {
            String message = entry.getMessage();
            if(entry.getMessage().contains("token") && (entry.getMessage().contains(", os"))) {
                token = message.substring(message.indexOf(", token = "), message.indexOf(", os"));
                MyLogger.log.debug("Token: " + token);
            }

            if(TextUtils.isEmpty(token) || token.length() < 100) {
                MyLogger.log.info("Empty or incorrect token: " + token);
                token = null;
            }
        }

        return token;
    }


    public static boolean sendFCM(String token, JSONObject data) {
        try {
            URL e = new URL("https://fcm.googleapis.com/fcm/send");
            HttpsURLConnection urlConn = (HttpsURLConnection)e.openConnection();
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            urlConn.setUseCaches(false);
            urlConn.setRequestProperty("Content-Type", "application/json");
            urlConn.setRequestProperty("Authorization", "key=AIzaSyCpDGktziVA4-VmajphY1r7gZdbp0ErOtQ");
            urlConn.connect();
            JSONObject root = new JSONObject();
            //root.put("to", token);
            //root.put("data", data);
            DataOutputStream printout = new DataOutputStream(urlConn.getOutputStream());
            printout.writeBytes(root.toString());
            printout.flush();
            printout.close();
            int code = urlConn.getResponseCode();
            if(code != 200) {
                MyLogger.log.error("https://fcm.googleapis.com/fcm/send: " + code);
            } else {
                MyLogger.log.info("https://fcm.googleapis.com/fcm/send: " + code);
            }

            BufferedInputStream in = new BufferedInputStream(urlConn.getInputStream());
            String result = IOUtils.toString(in, "UTF-8");
            JSONObject jsonObject = (JSONObject)(new JSONParser()).parse(result);
            return "1".equals(jsonObject.get("success"));
        } catch (Exception var10) {
            var10.printStackTrace();
            MyLogger.log.error("sendFCM failed: " + var10);
            return false;
        }
    }

    static {
//        TEST_DATA_JSON.put("title", "It`s Thanksgiving Day!");
//        TEST_DATA_JSON.put("body", "Time to be thankful & send your gratitude in a classy wrapping.");
//        TEST_DATA_JSON.put("attachment", "http://d3kk92hl2t7r5d.cloudfront.net/i/e/default/1552.jpg");
//        TEST_DATA_JSON.put("action", "navigate=fx&id=1552");
    }
}
