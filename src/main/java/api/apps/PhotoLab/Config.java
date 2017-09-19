package api.apps.PhotoLab;

import api.android.Android;
import core.MyLogger;
import javafx.util.Pair;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 9/7/2017.
 */
public class Config {

    public Boolean checkIsNewEffects(String configURL) throws org.json.simple.parser.ParseException {

        try {
            ArrayList listFailedEffects = new ArrayList();
            String configJson = IOUtils.toString(new URL(configURL));
            JSONObject configJsonObject = (JSONObject) JSONValue.parseWithException(configJson);
            String tabName = getTabByLegacy(configJsonObject, "en", "categories");

            org.json.simple.JSONArray effects = (org.json.simple.JSONArray) configJsonObject.get("effects");

            for(int j=0; j<effects.size(); j++) {

                JSONObject effectItem = (JSONObject) effects.get(j);
                Boolean isNew = (Boolean) effectItem.get("is_new");
                if (isNew){

                    Long effectId = (Long) effectItem.get("id");
                    Pair<Integer, String> resultSet = getCategoryByEffect(configJsonObject, effectId);
                    String categoryTitle = resultSet.getValue();
                    Integer effectPosition = resultSet.getKey();

                    JSONObject titles = (JSONObject) effectItem.get("title");
                    String effectTitle = (String) titles.get("en");

                    Android.app.photoLab.menu.open();
                    Android.app.photoLab.menu.tapHome();
                    Android.app.photoLab.main.waitToLoad();
                    Android.app.photoLab.main.selectCategoryBar(tabName);
                    Android.app.photoLab.main.selectCategory(categoryTitle);

                    if (!Android.app.photoLab.categories.checkEffectIsNew(effectTitle, effectPosition)){
                        MyLogger.log.info("Effect : "+effectTitle+" isn't have IsNew icon.");
                        listFailedEffects.add(effectTitle);
                    }
                }
            }
            if (listFailedEffects.isEmpty()) return true;
            else {
                MyLogger.log.info("Failed effects : "+listFailedEffects.toString());
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean checkCategoryEffects(String configURL, String lang, String legacyID) throws org.json.simple.parser.ParseException {

        try {
            String title,catTitle;
            Map<String, ArrayList> resultMap = new HashMap<String, ArrayList>();
            String configJson = IOUtils.toString(new URL(configURL));
            JSONObject configJsonObject = (JSONObject) JSONValue.parseWithException(configJson);
            String tabName = getTabByLegacy(configJsonObject, lang, "categories");
            org.json.simple.JSONArray category = (org.json.simple.JSONArray) configJsonObject.get("categories");

            for(int j=0; j<category.size(); j++) {

                JSONObject categoryItem = (JSONObject) category.get(j);
                String tabId = (String) categoryItem.get("legacy_id");
                if (legacyID.equals(tabId) || (legacyID.equals(""))){

                    JSONObject titles = (JSONObject) categoryItem.get("title");

                    if (titles.containsKey(lang)) {
                        catTitle = (String) titles.get(lang);

                        ArrayList listFailedEffects = new ArrayList();

                        Android.app.photoLab.menu.open();
                        Android.app.photoLab.menu.tapHome();
                        Android.app.photoLab.main.waitToLoad();
                        Android.app.photoLab.main.selectCategoryBar(tabName);
                        Android.app.photoLab.main.selectCategory(catTitle);

                        org.json.simple.JSONArray content = (org.json.simple.JSONArray) categoryItem.get("content");
                        for(int i=0; i<content.size(); i++) {
                            JSONObject element = (JSONObject) content.get(i);
                            String type = (String)element.get("type");
                            Long id = (Long)element.get("id");

                            if (type.equals("fx")){
                                JSONObject resObject;
                                resObject = getEffectById("", id, configJsonObject);
                                title = getJsonObjTitle(resObject, "en");

                                if (!Android.app.photoLab.categories.effectExists(title)) listFailedEffects.add(title);
                            }
                        }
                        if (!listFailedEffects.isEmpty()){
                            resultMap.put(catTitle, listFailedEffects);
                            listFailedEffects.clear();
                        }
                    } else {
                        MyLogger.log.error("Caategory key value '" + lang + "' not found JSON object.");
                        new AssertionError ("Caategory key value '" + lang + "' not found JSON object.");
                    }
                }
            }
            if (resultMap.isEmpty()) return true;
            else {
                MyLogger.log.info("Failed effects : "+resultMap.toString());
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean checkTabContent(String configURL, String lang, String legacyID) throws org.json.simple.parser.ParseException {

        try {
            String title;
            String configJson = IOUtils.toString(new URL(configURL));
            JSONObject configJsonObject = (JSONObject) JSONValue.parseWithException(configJson);
            String tabName = getTabByLegacy(configJsonObject, lang, legacyID);
            MyLogger.log.info("TAB NAME :"+tabName);
            org.json.simple.JSONArray tab = (org.json.simple.JSONArray) configJsonObject.get("tabs");

            for(int j=0; j<tab.size(); j++) {
                JSONObject tabItem = (JSONObject) tab.get(j);
                String tabId = (String) tabItem.get("legacy_id");
                if (legacyID.equals(tabId)){

                    ArrayList listFailedItem = new ArrayList();

                    Android.app.photoLab.main.waitToLoad();
                    Android.app.photoLab.main.selectCategoryBar(tabName);

                    org.json.simple.JSONArray content = (org.json.simple.JSONArray) tabItem.get("content");
                    for(int i=0; i<content.size(); i++) {
                        JSONObject element = (JSONObject) content.get(i);
                        String type = (String)element.get("type");
                        Long id = (Long)element.get("id");
                        JSONObject resObject;
                        switch (type) {
                            case "fx":
                                resObject = getEffectById("", id, configJsonObject);
                                title = getJsonObjTitle(resObject, "en");
                                //MyLogger.log.info("Type is : EFFECT. Title :"+title);
                                if (!Android.app.photoLab.categories.effectExists(title)) listFailedItem.add(title);
                                break;
                            case "category":
                                resObject  = getCategoryById("", id, configJsonObject);
                                title = getJsonObjTitle(resObject, lang);
                                //MyLogger.log.info("Type is : CATEGORY. Title :"+title);
                                if (!Android.app.photoLab.main.existItem(title, true)) listFailedItem.add(title);
                                break;
                            case "ads":
                                //MyLogger.log.info("This is ADS banner.");
                                break;
                            default:
                                MyLogger.log.info("This element with type = '"+type+"'.");
                                break;
                        }
                    }
                    if (listFailedItem.isEmpty()) return true;
                    else {
                        MyLogger.log.info("Failed elements titles : "+listFailedItem.toString());
                    }
                }
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void checkCategories(String configURL, String lang) throws org.json.simple.parser.ParseException {

        try {
            String categoryName;

            String configJson = IOUtils.toString(new URL(configURL));
            JSONObject configJsonObject = (JSONObject) JSONValue.parseWithException(configJson);
            String tabName = getTabByLegacy(configJsonObject, lang, "categories");
            org.json.simple.JSONArray categories = (org.json.simple.JSONArray) configJsonObject.get("categories");

            for(int j=0; j<categories.size(); j++) {
                JSONObject categoryItem = (JSONObject) categories.get(j);
                JSONObject titles = (JSONObject) categoryItem.get("title");
                if (titles.containsKey(lang)) {
                    categoryName = (String) titles.get(lang);
                    MyLogger.log.info("Category "+j+" name = " + categoryName);
                    Android.app.photoLab.main.waitToLoad();
                    Android.app.photoLab.main.selectCategoryBar(tabName);
                    Android.app.photoLab.main.existItem(categoryName, true);
                } else MyLogger.log.info("Key value '" + lang + "' not found in Conf.json.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkTabs(String configURL, String lang) throws org.json.simple.parser.ParseException {

        try {
            String tabName;
            String configJson = IOUtils.toString(new URL(configURL));
            JSONObject configJsonObject = (JSONObject) JSONValue.parseWithException(configJson);
            org.json.simple.JSONArray tab = (org.json.simple.JSONArray) configJsonObject.get("tabs");

            for(int j=0; j<tab.size(); j++) {
                JSONObject tabItem = (JSONObject) tab.get(j);
                String tabId = (String) tabItem.get("legacy_id");

                switch (tabId) {
                    case "collage_maker":
                    case "favorites":
                        MyLogger.log.info("Tab '" + tabId + "' not used in this application version.");
                        break;
                    default:
                        JSONObject titles = (JSONObject) tabItem.get("title");
                        if (titles.containsKey(lang)) {
                            tabName = (String) titles.get(lang);
                            MyLogger.log.info("TAB name = " + tabName);
                            Android.app.photoLab.main.selectCategoryBar(tabName);
                        } else {
                            MyLogger.log.info("Key value '" + lang + "' not found in Conf.json.");
                        }
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject getCategoryById(String configURL, Long id, JSONObject optional) throws org.json.simple.parser.ParseException {

        try {
            JSONObject configJsonObject;
            if (configURL.isEmpty()) configJsonObject = optional;
            else {
                String configJson = IOUtils.toString(new URL(configURL));
                configJsonObject = (JSONObject) JSONValue.parseWithException(configJson);
            }

            org.json.simple.JSONArray categories = (org.json.simple.JSONArray) configJsonObject.get("categories");

            for(int j=0; j<categories.size(); j++) {
                JSONObject categoryItem = (JSONObject) categories.get(j);

                Long currId = (Long) categoryItem.get("id");
                if (currId.longValue() == id.longValue()){
                    return categoryItem;
                }
            } return null;
        } catch (IOException e) {
            throw new AssertionError("Failed to get category JSON object.");
        }
    }

    public static JSONObject getEffectById(String configURL, Long id, JSONObject optional) throws org.json.simple.parser.ParseException {

        try {
            JSONObject configJsonObject;
            if (configURL.isEmpty()) configJsonObject = optional;
            else {
                String configJson = IOUtils.toString(new URL(configURL));
                configJsonObject = (JSONObject) JSONValue.parseWithException(configJson);
            }

            org.json.simple.JSONArray effects = (org.json.simple.JSONArray) configJsonObject.get("effects");

            for(int j=0; j<effects.size(); j++) {
                JSONObject effectItem = (JSONObject) effects.get(j);
                Long currId = (Long) effectItem.get("id");
                if (currId.longValue() == id.longValue()){
                    return effectItem;
                }
            } return null;
        } catch (IOException e) {
            throw new AssertionError("Failed to get effect JSON object.");
        }
    }

    public static Pair getCategoryByEffect(JSONObject configJson, Long effectId) throws org.json.simple.parser.ParseException {

        JSONObject configJsonObject = configJson;

        org.json.simple.JSONArray category = (org.json.simple.JSONArray) configJsonObject.get("categories");

        for(int j=0; j<category.size(); j++) {

            JSONObject categoryItem = (JSONObject) category.get(j);
            org.json.simple.JSONArray content = (org.json.simple.JSONArray) categoryItem.get("content");
            JSONObject titles = (JSONObject) categoryItem.get("title");
            String title = (String) titles.get("en");

            for (int i = 0; i < content.size(); i++) {
                JSONObject element = (JSONObject) content.get(i);
                Long id = (Long) element.get("id");
                if (id.longValue() == effectId.longValue()) {
                    Pair<Integer, String> result = new Pair<>(i, title);
                    return result;
                }
            }
        }
        return null;
    }

    public static String getJsonObjTitle(JSONObject obj, String lang) throws org.json.simple.parser.ParseException {
        JSONObject titles = (JSONObject) obj.get("title");
        if (titles.containsKey(lang)) {
            String title = (String) titles.get(lang);
            return title;
        } else {
            MyLogger.log.info("Key value '" + lang + "' not found JSON object.");
            return null;
        }
    }

    public static String getConfigVersion(String configURL) throws org.json.simple.parser.ParseException {

        try {
            String configJson = IOUtils.toString(new URL(configURL));
            JSONObject configJsonObject = (JSONObject) JSONValue.parseWithException(configJson);

            JSONObject rules = (JSONObject) configJsonObject.get("rules");
            String version = (String) rules.get("version");
            return version;
        } catch (IOException e) {
            throw new AssertionError("Failed to get config version value.");
        }
    }

    public static String getDefaultTab(String configURL, String lang) throws org.json.simple.parser.ParseException {

        try {
            Boolean bFlag = false;
            String tabName;
            String configJson = IOUtils.toString(new URL(configURL));
            JSONObject configJsonObject = (JSONObject) JSONValue.parseWithException(configJson);

            org.json.simple.JSONArray tab = (org.json.simple.JSONArray) configJsonObject.get("tabs");
            JSONObject settings = (JSONObject) configJsonObject.get("settings");
            Long defaultTabId = (Long) settings.get("default_tab");
            MyLogger.log.info("DEFAULT TAB = " + defaultTabId);
            for(int j=0; j<tab.size(); j++) {
                JSONObject tabItem = (JSONObject) tab.get(j);
                Long tabId = (Long) tabItem.get("id");

                if (defaultTabId.longValue() == tabId.longValue()) {
                    JSONObject titles = (JSONObject) tabItem.get("title");
                    if (titles.containsKey(lang)) {
                        tabName = (String) titles.get(lang);
                        return tabName;
                    } else MyLogger.log.info("Key value '" + lang + "' not found in Conf.json.");
                    break;
                }
            }
            return null;
        } catch (IOException e) {
            throw new AssertionError("Failed to check config default Tab value.");
        }
    }

    public static String getTabByLegacy(JSONObject jsonConfig, String lang, String legacyID) throws org.json.simple.parser.ParseException, IOException {

        Boolean bFlag = false;
        JSONObject configJsonObject = jsonConfig;
        org.json.simple.JSONArray tab = (org.json.simple.JSONArray) configJsonObject.get("tabs");

        for(int j=0; j<tab.size(); j++) {
            JSONObject tabItem = (JSONObject) tab.get(j);
            String tabId = (String) tabItem.get("legacy_id");

            if (tabId.equals(legacyID)){
                JSONObject titles = (JSONObject) tabItem.get("title");
                if (titles.containsKey(lang)) {
                    bFlag = true;
                    return (String) titles.get(lang);
                } else MyLogger.log.info("Key value '" + lang + "' not found in Conf.json.");
            }
        } throw new AssertionError("Failed to get Tab value by Legacy_id.");
    }
}
