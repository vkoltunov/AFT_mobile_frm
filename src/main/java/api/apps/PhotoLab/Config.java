package api.apps.PhotoLab;

import api.android.Android;
import com.opencsv.CSVReader;
import core.MyLogger;
import core.utils.Common;
import javafx.util.Pair;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 9/7/2017.
 */
public class Config {

    public Boolean checkEffectsPreview(String configURL) throws org.json.simple.parser.ParseException {

        try {
            ArrayList listFailedEffects = new ArrayList();
            String configJson = IOUtils.toString(new URL(configURL));
            JSONObject configJsonObject = (JSONObject) JSONValue.parseWithException(configJson);

            org.json.simple.JSONArray effects = (org.json.simple.JSONArray) configJsonObject.get("effects");

            for(int j=0; j<effects.size(); j++) {

                JSONObject effectItem = (JSONObject) effects.get(j);
                String previewUrl = (String) effectItem.get("preview");

                if (!Common.urlValidation(previewUrl)){

                    JSONObject titles = (JSONObject) effectItem.get("title");
                    String effectTitle = (String) titles.get("en");

                    MyLogger.log.info("Effect : "+effectTitle+" is have bad preview URL : "+previewUrl);
                    listFailedEffects.add(effectTitle);
                }
            }
            if (listFailedEffects.isEmpty()) return true;
            else {
                MyLogger.log.info("Failed effects preview : "+listFailedEffects.toString());
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean checkEffectsPopularity(String configURL, String csvURL) throws org.json.simple.parser.ParseException {

        try {
            ArrayList listFailedEffects = new ArrayList();
            String configJson = IOUtils.toString(new URL(configURL));
            JSONObject configJsonObject = (JSONObject) JSONValue.parseWithException(configJson);
            String tabName = getTabByLegacy(configJsonObject, "en", "categories");

            org.json.simple.JSONArray effects = (org.json.simple.JSONArray) configJsonObject.get("effects");

            CSVReader reader = Common.readCSV(csvURL);
            String[] line;
            String currentCategoryLegacy = "";
            Integer position = 0;
            Integer isNewCount = 0;
            Integer isNewIncluded = 0;

            while ((line = reader.readNext()) != null) {
                Long csvEffectId = Long.parseLong(line[0]);
                String csvEffectLegacy = line[1];
                String csvCategoryLegacy = line[2];
                String csvPopularity = line[3];

                switch (csvEffectLegacy) {
                    case "mickey_mouse_dreams":
                    case "british_pound_sterling":
                    case "transparent_calendar_2016":
                    case "calendar_2016":
                    case "2162":
                    case "2002":
                    case "hatched":
                    case "harley_quinn":
                    case "in_a_blurry_world":
                        MyLogger.log.info("Effect '"+csvEffectLegacy+"' is not used for Android App.");
                        break;
                    default:
                        if (currentCategoryLegacy.equals(csvCategoryLegacy)) position = position+1;
                        else {
                            position = 0;
                            isNewCount = 0;
                            isNewIncluded = 0;
                            currentCategoryLegacy = csvCategoryLegacy;
                        }

                        for(int j=0; j<effects.size(); j++) {

                            JSONObject effectItem = (JSONObject) effects.get(j);

                            Long effectId = (Long) effectItem.get("id");
                            if (csvEffectId.longValue() == effectId.longValue()){

                                Pair<Integer, String> resultSetWithAds = getEffectPositionInCategory(configJsonObject, effectId, true);
                                //Pair<Integer, String> resultSetWithoutAds = getEffectPositionInCategory(configJsonObject, effectId, false);
                                String categoryTitle = resultSetWithAds.getValue();
                                Integer effectPosition = resultSetWithAds.getKey();

                                JSONObject categoryJSON = getCategoryByTitle("", categoryTitle, "en", configJsonObject);
                                Boolean isNew = (Boolean) effectItem.get("is_new");

                                Map<Integer, Long> isNewResult = getNewEffectsFromCategory(configJsonObject, categoryJSON, true);


                                JSONObject titles = (JSONObject) effectItem.get("title");
                                String effectTitle = (String) titles.get("en");

                                Integer addToPosition = 0;

                                if (!isNewResult.isEmpty()){
                                    for(Map.Entry<Integer, Long> item : isNewResult.entrySet()){
                                        if ((effectId.longValue() == item.getValue().longValue())) {
                                            MyLogger.log.info("Category : " + categoryTitle + " |  Effect : " + effectTitle + " is have position: " + effectPosition + " , but him isNew position:" + item.getKey());
                                            isNewIncluded = isNewIncluded + 1;
                                            //break;
                                        }
                                    }
                                    Integer isNewSize = isNewResult.size();
                                    addToPosition = isNewResult.size() - isNewIncluded;
                                    //MyLogger.log.info("IsNew Size =  "+isNewSize);
                                }
                                if ((effectPosition-addToPosition) == (position)) MyLogger.log.info("Category : "+categoryTitle+" |  Effect : "+effectTitle+" is have equals with popularity file position.");
                                else {
                                    MyLogger.log.info("Category : "+categoryTitle+" |  Effect : " + effectTitle + " is have position: " + (effectPosition-addToPosition) + " , but popularity position is:" + position);
                                    listFailedEffects.add(effectTitle);
                                }
                            }
                        }
                        break;
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

    public static Map getNewEffectsFromCategory(JSONObject configObject, JSONObject categoryItem, Boolean withAdsPosition) throws org.json.simple.parser.ParseException {

        Integer position = 0;
        org.json.simple.JSONArray content = (org.json.simple.JSONArray) categoryItem.get("content");
        Map<Integer, Long> result = new HashMap<Integer, Long>();
        for (int i = 0; i < content.size(); i++) {
            JSONObject element = (JSONObject) content.get(i);
            String type = (String)element.get("type");
            if (type.equals("fx")) {
                Long id = (Long) element.get("id");
                JSONObject effectItem = getEffectById("", id, configObject);
                position = position + 1;
                Boolean isNew = (Boolean) effectItem.get("is_new");

                if (isNew) {
                    //JSONObject titles = (JSONObject) effectItem.get("title");
                    //String effectTitle = (String) titles.get("en");
                    if (withAdsPosition) result.put(position-1, id);
                    else result.put(i, id);
                }
            }
        }
        return result;
    }

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
                    Pair<Integer, String> resultSet = getEffectPositionInCategory(configJsonObject, effectId, false);
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

    public static JSONObject getCategoryByTitle(String configURL, String title, String lang, JSONObject optional) throws org.json.simple.parser.ParseException {

        try {
            JSONObject configJsonObject;
            String categoryName;
            if (configURL.isEmpty()) configJsonObject = optional;
            else {
                String configJson = IOUtils.toString(new URL(configURL));
                configJsonObject = (JSONObject) JSONValue.parseWithException(configJson);
            }

            org.json.simple.JSONArray categories = (org.json.simple.JSONArray) configJsonObject.get("categories");

            for(int j=0; j<categories.size(); j++) {

                JSONObject categoryItem = (JSONObject) categories.get(j);
                JSONObject titles = (JSONObject) categoryItem.get("title");
                categoryName = (String) titles.get(lang);
                if (categoryName.equals(title)) return categoryItem;
            } return null;
        } catch (IOException e) {
            throw new AssertionError("Failed to get category JSON object.");
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

    public static Pair getEffectPositionInCategory(JSONObject configJson, Long effectId, Boolean withAdsPosition) throws org.json.simple.parser.ParseException {

        JSONObject configJsonObject = configJson;

        org.json.simple.JSONArray category = (org.json.simple.JSONArray) configJsonObject.get("categories");

        for(int j=0; j<category.size(); j++) {

            JSONObject categoryItem = (JSONObject) category.get(j);
            org.json.simple.JSONArray content = (org.json.simple.JSONArray) categoryItem.get("content");
            JSONObject titles = (JSONObject) categoryItem.get("title");
            String title = (String) titles.get("en");
            Integer position = 0;
            for (int i = 0; i < content.size(); i++) {
                JSONObject element = (JSONObject) content.get(i);
                String type = (String)element.get("type");
                if (type.equals("fx")) position = position + 1;
                Long id = (Long) element.get("id");
                if (id.longValue() == effectId.longValue()) {
                    Pair<Integer, String> result;
                    if (withAdsPosition) result = new Pair<>(position-1, title);
                    else result = new Pair<>(i, title);
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
            for(int j=0; j<tab.size(); j++) {
                JSONObject tabItem = (JSONObject) tab.get(j);
                Long tabId = (Long) tabItem.get("id");
                if (defaultTabId.longValue() == tabId.longValue()) {
                    JSONObject titles = (JSONObject) tabItem.get("title");
                    if (titles.containsKey(lang)) {
                        tabName = (String) titles.get(lang);
                        MyLogger.log.info("Config default TAB = " + tabName);
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
