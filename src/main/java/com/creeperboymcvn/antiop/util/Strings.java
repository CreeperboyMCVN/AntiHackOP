package com.creeperboymcvn.antiop.util;

import com.creeperboymcvn.antiop.Main;
import org.bukkit.ChatColor;

public class Strings {
    
    public static String colorChat(String s) {
        return ChatColor.translateAlternateColorCodes('&', Main.inst.getMessageConf().getConfig().getString("prefix")+" "+s);
    }
    
    public static String colorChatNoPrefix(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
    
    public static String deColorChat(String s, char deChar) {
        return s.replace('§', deChar);
    }

}
