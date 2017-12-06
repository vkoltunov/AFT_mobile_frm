package api.apps.Xmas;

import api.android.Android;
import api.apps.Xmas.about.About;
import api.apps.Xmas.categories.Categories;
import api.apps.Xmas.custom.Custom;
import api.apps.Xmas.main.Main;
import api.apps.Xmas.menu.Menu;
import api.interfaces.Application;
import core.utils.Common;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
 * Created by User on 4/4/2017.
 */
public class Xmas implements Application{

    public Boolean favoriteFlag = false;
    private Common commonFunc;
    public String appType = "free";

    public Main main = new Main();
    public Menu menu = new Menu();
    public Categories categories = new Categories();
    public About about = new About();
    public Custom custom = new Custom();
    public Config config = new Config();

    @Override
    public void forceStop() {
        Android.adb.forceStopApp(packageID());
    }

    @Override
    public void clearData() {
        Android.adb.clearAppsData(packageID());
    }

    @Override
    public void uninstallApp() {
        if (appType.equals("free")) Android.adb.uninstallApp("com.vicman.photolab.newyearapp");
        Android.adb.uninstallApp(packageID());
    }

    @Override
    public String apkPath() {
        return "D:\\AFT\\build";
    }

    @Override
    public void installApp(String path, String type) {
        try {

            if (path.contains(".apk") && !(path.contains("http"))) Android.adb.installApp(path);
            if (path.contains("http")) {
                String fileName;
                if (path.contains(".apk")){
                    fileName = commonFunc.getMatch(path, "(Xmas.+).apk");
                } else {
                    Document doc = Jsoup.connect(path).get();
                    if (type.isEmpty()) type = "debug";
                    Elements links = doc.select("a[href$=" + type + ".apk]"); // a with href
                    fileName = links.attr("href");
                    path = path + fileName;
                }

                Boolean check = new File(apkPath(), fileName).exists();
                if (!check) commonFunc.downloadUsingStream(path, apkPath()+"\\"+fileName);
                Android.adb.installApp(apkPath()+"\\"+fileName);

            }
            else {
                File folder = new File(apkPath());
                File[] listOfFiles = folder.listFiles();

                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile()) {
                        Android.adb.installApp(listOfFiles[i].getPath());
                        break;
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public Object open() {
        Android.adb.openAppsActivity(packageID(), activityID());
        return null;
    }

    @Override
    public String packageID() {
        if (appType.equals("pro")) return "com.vicman.photolabpro";
        else return "com.vicman.photolab.newyearapp";
    }

    @Override
    public String activityID() {
        return "com.vicman.photolab.activities.MainActivity";
    }

    public void setAppType(String type) {
        if (!(type == null)){
            if (type.equals("pro")) appType = "pro";
        }
    }

}

