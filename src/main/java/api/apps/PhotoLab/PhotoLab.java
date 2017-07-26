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
import api.apps.PhotoLab.property.Property;
import api.apps.PhotoLab.processing.Processing;
import api.apps.PhotoLab.result.Result;
import api.apps.PhotoLab.save.Save;
import api.apps.PhotoLab.store.Store;
import api.apps.PhotoLab.text.Text;
import api.interfaces.Application;

import java.io.File;

/**
 * Created by User on 4/4/2017.
 */
public class PhotoLab implements Application{

    public Boolean favoriteFlag = false;

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
    public void installApp(String path) {
        if (path.contains(".apk")) Android.adb.installApp(path);
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
    }

    @Override
    public Object open() {
        Android.adb.openAppsActivity(packageID(), activityID());
        return null;
    }

    @Override
    public String apkPath() {
        return "D:\\PhotoLab\\build";
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

