package api.apps.PhotoLab.notifications;

import core.UiObject;
import core.UiSelector;

/**
 * Created by User on 6/19/2017.
 */
public class NotificationsUiObjects {
    private static UiObject
            notification;

    public UiObject notification(){
        if(notification == null) notification = new UiSelector().className("android.widget.TextView").resourceId("android:id/title").textContains("It`s Thanksgiving Day!").makeUiObject();
        return notification;
    }

}
