package core;

import core.managers.ServerManager;
import org.aspectj.lang.annotation.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import static core.managers.DriverManager.sdCard;

/**
 * Created by User on 3/28/2017.
 */

public class ADB {

    private String ID;
    private static String card = sdCard;

    public ADB(String deviceID){ID = deviceID;}

    public static String command(String command){
        MyLogger.log.debug("Formatting ADB Command: "+command);
        if(command.startsWith("adb")) command = command.replace("adb ", ServerManager.getAndroidHome()+"/platform-tools/adb ");
        else throw new RuntimeException("This method is designed to run ADB commands only!");
        MyLogger.log.debug("Formated ADB Command: "+command);
        String output = ServerManager.runCommand(command);
        MyLogger.log.debug("Output of the ADB Command: "+output);
        if(output == null) return "";
        else return output;
    }

    public static void killServer(){
        command("adb kill-server");
    }

    public static void startServer(){
        command("adb start-server");
    }

    public static ArrayList getConnectedDevices(){
        ArrayList<String> devices = new ArrayList<>();
        String output = command("adb devices");
        for(String line : output.split("\n")){
            line = line.trim();
            if(line.endsWith("device")) devices.add(line.replace("device", "").trim());
        }
        return devices;
    }

    public String getForegroundActivity(){
        return command("adb -s "+ID+" shell displays window windows | grep mCurrentFocus");
    }

    public String getAndroidVersionAsString(){
        String output = command("adb -s "+ID+" shell getprop ro.build.version.release");
        if(output.length() == 3) output=".0";
        return output;
    }

    public int getAndroidVersion(){
        return Integer.parseInt(getAndroidVersionAsString().replaceAll("\\.", ""));
    }

    public ArrayList getInstalledPackages(){
        ArrayList<String> packages = new ArrayList<>();
        String[] output = command("adb -s "+ID+" shell pm list packages").split("\n");
        for(String packageID : output) packages.add(packageID.replace("package:", "").trim());
        return packages;
    }

    public void openAppsActivity(String packageID, String activityID){
        command("adb -s "+ID+" shell am start -c api.android.intent.category.LAUNCHER -a api.android.intent.action.MAIN -n "+packageID+"/"+activityID);
    }

    public void clearAppsData(String packageID){
        command("adb -s "+ID+" shell pm clear "+packageID);
    }
    public void forceStopApp(String packageID){
        command("adb -s "+ID+" shell am force-stop "+packageID);
    }

    public void installApp(String apkPath){
        command("adb -s "+ID+" install "+apkPath);
    }

    public void uninstallApp(String packageID){
        command("adb -s "+ID+" uninstall "+packageID);
    }

    public void clearLogBuffer(){
        command("adb -s "+ID+" shell -c");
    }

    public void pushFile(String source, String target){
        command("adb -s "+ID+" push "+source+" "+target);
    }

    public void pullFile(String source, String target){
        command("adb -s "+ID+" pull "+source+" "+target);
    }

    public void deleteFile(String target){
        command("adb -s "+ID+" shell rm "+target);
    }

    public void createFolder(String target){
        command("adb -s "+ID+" shell mkdir "+target);
    }

    public void deleteFolderFiles(String target){
        command("adb -s "+ID+" shell rm -rfv "+target+"/*");
    }

    public void moveFile(String source, String target){
        command("adb -s "+ID+" shell mv "+source+" "+target);
    }

    public void takeScreenshot(String target){
        command("adb -s "+ID+" shell screencap "+target);
    }

    public void changeDeviceLanguage(String lang){
        command("adb -s "+ID+" shell pm grant net.sanapeli.adbchangelanguage android.permission.CHANGE_CONFIGURATION");
        command("adb -s "+ID+" shell am start -n net.sanapeli.adbchangelanguage/.AdbChangeLanguage -e language "+ lang);
    }

    public void rebootDevice(){
        command("adb -s "+ID+" reboot");
    }

    public String getDeviceModel(){
        return command("adb -s "+ID+" shell getprop ro.product.model");
    }

    public String getDeviceSerialNumber(){
        return command("adb -s "+ID+" shell getprop ro.serialno");
    }

    public String getDeviceCarrier(){
        return command("adb -s "+ID+" shell getprop gsm.operator.alpha");
    }

    public void updateBroadcastMediaMounted (){
        command("adb -s "+ID+" shell am broadcast -a android.intent.action.MEDIA_MOUNTED -d file:///mnt/sdcard/Pictures");
    }

    public List<String> getLogcatProcesses(){
        String[] output = command("adb -s "+ID+" shell top -n 1 | grep -i 'logcat'").split("\n");
        List<String> processes = new ArrayList<>();
        for(String line : output){
            processes.add(line.split(" ")[0]);
            processes.removeAll(Arrays.asList("", null));
        }
        return processes;
    }

    List<String> processes = new ArrayList<String>();

    public Object startLogcat(final String logID, final String grep){
        List<String> pidBefore = getLogcatProcesses();

        Thread logcat = new Thread(new Runnable() {
            @Override
            public void run() {
                if(grep == null) command("adb -s "+ID+" shell logcat -v threadtime > /"+card+"/"+logID+".txt");
                else command("adb -s "+ID+" shell logcat -v threadtime | grep -i '"+grep+"'> /"+card+"/"+logID+".txt");
            }
        });
        logcat.setName(logID);
        logcat.start();
        logcat.interrupt();

        List<String> pidAfter =  getLogcatProcesses();
        Timer timer = new Timer();
        timer.start();
        while(!timer.expired(5)){
            if(pidBefore.size() > 0) pidAfter.removeAll(pidBefore);
            if(pidAfter.size() > 0) break;
            pidAfter = getLogcatProcesses();
        }
        if(pidAfter.size() == 1) return pidAfter.get(0);
        else if(pidAfter.size() > 1) throw new RuntimeException("Multiple logcat processes were started when only one was expected!");
        else throw new RuntimeException("Failed to start logcat process!");
    }

    public void stopLogcat(Object PID){
        command("adb -s "+ID+" shell kill "+PID);
    }

//    @Test
//    public void test(){
//        ID = "emulator-5554";
//        System.out.println("Processes Prior to starting new logcat: "+getLogcatProcesses());
//        Object PID = startLogcat("1", null);
//        System.out.println("Started logcat on PID: "+PID);
//        System.out.println("Processes after starting new logcat: "+getLogcatProcesses());
//        stopLogcat(PID);
//        System.out.println("Processes after stopping new logcat: "+getLogcatProcesses());
//    }
}
