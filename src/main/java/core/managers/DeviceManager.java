package core.managers;

import api.android.Android;
import core.AVD;
import core.MyLogger;
import io.appium.java_client.android.AndroidDriver;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static core.AVD.getEmulatorList;
import static core.AVD.startEmulator;
/**
 * Created by User on 4/17/2017.
 */
public class DeviceManager {

    public static void launchEmulator(String nameOfAVD) {
        MyLogger.log.info("Launch Android Emulator.");
        try {
            if (isExist(nameOfAVD)) startEmulator(nameOfAVD);
            else MyLogger.log.info("Emulator "+nameOfAVD+" isn't found.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isExist (String emulatorID) {
        MyLogger.log.info("Checking for emulator exist");
        ArrayList<String> emulators = getEmulatorList();
        Boolean fExist = false;
        for(String emulator : emulators){
            try{
                if(emulator == emulatorID){
                    MyLogger.log.info("Emulator "+emulatorID+" exist.");
                    fExist = true;
                    break;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return fExist;
    }
}
