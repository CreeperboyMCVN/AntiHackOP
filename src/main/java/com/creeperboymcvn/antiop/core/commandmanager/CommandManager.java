package com.creeperboymcvn.antiop.core.commandmanager;

import com.creeperboymcvn.antiop.Main;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.entity.Player;

public class CommandManager {
    
    private static final Map<Player, Boolean> isPassword = new HashMap<>();
    private static final Map<Player, Boolean> isInputingPassword = new HashMap<>();
    
    public static boolean isPlayerInList(Player p) {
        
        List<String> pList = Main.inst.getDataConf().getConfig().getStringList("playerAllowToDoCommand");
        
        for (String s:pList) {
            if (s.equalsIgnoreCase(p.getName())) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isCheckCommand(List<String> commands, String command) {
        for (String s: commands) {
            if (command.contains("/"+s)) {
                return true;
            }
        }
        return false;
    }
    
    public static void setIsPassword(Player p, boolean bl) {
        isPassword.put(p, bl);
    }
    
    public static void resetIsPassword() {
        isPassword.clear();
    }
    
    public static boolean getIsPassword(Player p) {
        if (!isPassword.containsKey(p)) {
            return false;
        }
        return isPassword.get(p);
    }
    
    public static void setIsInputingPassword(Player p, boolean bl) {
        isInputingPassword.put(p, bl);
    }
    
    public static void resetIsInputingPassword() {
        isInputingPassword.clear();
    }
    
    public static boolean getIsInputingPassword(Player p) {
        
        if (!isInputingPassword.containsKey(p)) {
            return false;
        }
        
        return isInputingPassword.get(p);
        
    } 
    
}
