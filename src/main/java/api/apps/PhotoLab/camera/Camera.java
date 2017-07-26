package api.apps.PhotoLab.camera;

import api.android.Android;
import api.interfaces.Activity;
import api.apps.PhotoLab.camera.CameraUiObjects;
import core.MyLogger;

/**
 * Created by User on 5/2/2017.
 */
public class Camera implements Activity {

    public CameraUiObjects uiObject = new CameraUiObjects();

    @Override
    public Camera waitToLoad(){
        try{
            MyLogger.log.info("waiting for Camera activity");
            uiObject.shuter().waitToAppear(10);
            return Android.app.photoLab.camera;
        }catch (AssertionError e) {
            throw new AssertionError("Camera activity failed to load/open");
        }
    }

    public void tapShuter(){
        try{
            MyLogger.log.info("Tap to camera Shuter.");
            uiObject.shuter().tap();
        }catch (AssertionError e) {
            throw new AssertionError("Camera shuter failed to tap.");
        }
    }

    public void tapClose(){
        try{
            MyLogger.log.info("Close camera roll.");
            uiObject.close().tap();
        }catch (AssertionError e) {
            throw new AssertionError("Camera roll failed to close.");
        }
    }

}
