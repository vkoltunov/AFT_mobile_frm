package api.apps.PhotoLab.notifications;

import api.android.Android;
import api.interfaces.Activity;
import core.MyLogger;

/**
 * Created by User on 6/19/2017.
 */
public class Notifications implements Activity {

    public NotificationsUiObjects uiObject = new NotificationsUiObjects();

    @Override
    public Notifications waitToLoad(){
        try{
            MyLogger.log.info("Waiting for Notification");
            uiObject.notification().waitToAppear(10);
            return Android.app.photoLab.notifications;
        }catch (AssertionError e) {
            throw new AssertionError("Notification failed to load/sent");
        }
    }

    public void tapNotification(){
        try{
            MyLogger.log.info("Tap to Notification for selected effect.");
            uiObject.notification().tap();
        }catch (AssertionError e) {
            throw new AssertionError("Notification failed to tap.");
        }
    }
}
