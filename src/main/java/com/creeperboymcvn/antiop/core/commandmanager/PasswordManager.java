package com.creeperboymcvn.antiop.core.commandmanager;

import com.creeperboymcvn.antiop.Main;
import com.creeperboymcvn.antiop.util.Values;
import java.io.IOException;
import org.apache.commons.lang.RandomStringUtils;
import org.bukkit.entity.Player;

public class PasswordManager {
    
    public static void setPassword(String player) {
        String password = RandomStringUtils.randomAlphanumeric(Values.__PASSWORD_LENGTH);
        int nextReset = Main.inst.getConfig().getInt("resetPasswordTimer");
        long nextResetInMillis = nextReset*60000;
        long _nextReset = System.currentTimeMillis()+nextResetInMillis;
        Main.inst.getDataConf().getConfig().set("password."+player, password);
        Main.inst.getDataConf().getConfig().set("nextReset", _nextReset);
        try {
            Main.inst.getDataConf().saveConfig();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static String getPassword(Player p) {
        return Main.inst.getDataConf().getConfig().getString("password."+p.getName());
    }
    
    public static String getPassword(String s) {
        return Main.inst.getDataConf().getConfig().getString("password."+s);
    }

}
