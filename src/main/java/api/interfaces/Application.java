package api.interfaces;

/**
 * Created by User on 4/4/2017.
 */
public interface Application {

    void forceStop();
    void clearData();
    void uninstallApp();
    void installApp(String path);
    Object open();
    String apkPath();
    String packageID();
    String activityID();
}
