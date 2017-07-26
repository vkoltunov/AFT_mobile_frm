package api.apps.PhotoLab.goPRO;

import api.android.Android;
import api.apps.PhotoLab.main.Main;
import api.apps.PhotoLab.main.MainUiObjects;
import api.apps.PhotoLab.menu.Menu;
import api.apps.PhotoLab.store.Store;
import api.interfaces.Activity;
import core.MyLogger;

/**
 * Created by User on 5/29/2017.
 */
public class GoPRO implements Activity {

    public GoPROUiObjects uiObject = new GoPROUiObjects();

    @Override
    public GoPRO waitToLoad(){
        try{
            MyLogger.log.info("Waiting for Go PRO now page.");
            uiObject.goPRO().waitToAppear(10);
            return Android.app.photoLab.goPRO;
        }catch (AssertionError e) {
            throw new AssertionError("Go PRO now page failed to load/open");
        }
    }

    public Store tapGoPRO(){
        try{
            MyLogger.log.info("Tap to go PRO button.");
            uiObject.goPRO().tap();
            return Android.app.photoLab.store.waitToLoad();
        }catch (AssertionError e) {
            throw new AssertionError("Go PRO button failed to tap/load");
        }
    }
}
