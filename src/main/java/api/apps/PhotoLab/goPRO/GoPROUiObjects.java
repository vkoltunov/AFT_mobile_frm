package api.apps.PhotoLab.goPRO;

import core.UiObject;
import core.UiSelector;

/**
 * Created by User on 5/29/2017.
 */
public class GoPROUiObjects {

    private static UiObject
            goPRO;

    public UiObject goPRO(){
        if(goPRO == null) goPRO = new UiSelector().className("android.widget.Button").resourceId("android:id/button1").textContains("Go PRO now").makeUiObject();
        return goPRO;
    }

}
