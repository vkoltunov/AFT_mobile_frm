package api.apps.PhotoLab;

import api.android.Android;
import api.apps.PhotoLab.aboutCombo.AboutCombo;
import api.apps.PhotoLab.advertisement.Advertisement;
import api.apps.PhotoLab.animate.Animate;
import api.apps.PhotoLab.art.Art;
import api.apps.PhotoLab.dialogs.Dialogs;
import api.apps.PhotoLab.facebook.Facebook;
import api.apps.PhotoLab.goPRO.GoPRO;
import api.apps.PhotoLab.logIn.LogIn;
import api.apps.PhotoLab.notifications.Notifications;
import api.apps.PhotoLab.pictures.Pictures;
import api.apps.PhotoLab.about.About;
import api.apps.PhotoLab.camera.Camera;
import api.apps.PhotoLab.categories.Categories;
import api.apps.PhotoLab.main.Main;
import api.apps.PhotoLab.menu.Menu;
import api.apps.PhotoLab.profile.Profile;
import api.apps.PhotoLab.property.Property;
import api.apps.PhotoLab.processing.Processing;
import api.apps.PhotoLab.result.Result;
import api.apps.PhotoLab.save.Save;
import api.apps.PhotoLab.store.Store;
import api.apps.PhotoLab.text.Text;
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
public class PhotoLab implements Application{

    public Boolean favoriteFlag = false;
    private Common commonFunc;

    public Main main = new Main();
    public Menu menu = new Menu();
    public Categories categories = new Categories();
    public Pictures pictures = new Pictures();
    public About about = new About();
    public Camera camera = new Camera();
    public Property property = new Property();
    public Processing processing = new Processing();
    public Result result = new Result();
    public Animate animate = new Animate();
    public Art art = new Art();
    public Text text = new Text();
    public Save save = new Save();
    public Custom custom = new Custom();
    public Store store = new Store();
    public GoPRO goPRO = new GoPRO();
    public Dialogs dialogs = new Dialogs();
    public LogIn logIn = new LogIn();
    public Facebook facebook = new Facebook();
    public Advertisement advertisement = new Advertisement();
    public Notifications notifications = new Notifications();
    public AboutCombo aboutCombo = new AboutCombo();
    public Profile profile = new Profile();

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
                    fileName = commonFunc.getMatch(path, "(PhotoLab-Play.+).apk");
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
        return "vsin.t16_funny_photo";
    }

    @Override
    public String activityID() {
        return "com.vicman.photolab.activities.MainActivity";
    }




}

