package core;

import core.managers.ServerManager;

import java.util.ArrayList;

/**
 * Created by User on 4/18/2017.
 */
public class AVD {

    public static String command(String command){
        MyLogger.log.debug("Formatting Emulator Command: "+command);
        if(command.startsWith("emulator")) command = command.replace("emulator ", ServerManager.getAndroidHome()+"/tools/emulator ");
        else throw new RuntimeException("This method is designed to run Emulator commands only!");
        MyLogger.log.debug("Formated Emulator Command: "+command);
        String output = ServerManager.runCommand(command);
        MyLogger.log.debug("Output of the Emulator Command: "+output);
        if(output == null) return "";
        else return output;
    }

    public static ArrayList getEmulatorList(){
        ArrayList emulators = new ArrayList();
        String output = command("emulator -list-avds");
        for(String line : output.split("\n")){
            line = line.trim();
            if(line.length() != 0) emulators.add(line);
        }
        return emulators;
    }

    public static void startEmulator(String emulatorID){
        command("emulator -avd "+emulatorID);
    }
}
