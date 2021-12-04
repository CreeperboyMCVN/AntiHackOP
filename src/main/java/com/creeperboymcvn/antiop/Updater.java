package com.creeperboymcvn.antiop;

import java.io.File;

public class Updater {
    
    public static void check() {
        int confVersion = Main.inst.getConfig().getInt("config-version", 0);
        int msgVersion = Main.inst.getMessageConf().getConfig().getInt("config-version", 0);
        
        if (confVersion == 0 || confVersion < 1) {
            File f = new File(Main.inst.getDataFolder()+File.separator+"config.yml");
            if (!f.exists()) {
                Main.inst.getLogger().info("Đang tạo file config.yml mới");
                Main.inst.saveResource("config.yml", true);
                Main.inst.getLogger().info("Hoàn thành!");
            } else {
                Main.inst.getLogger().info("Tệp config.yml của bạn đã cũ. Đang tạo file config.yml mới");
                f.delete();
                Main.inst.saveResource("config.yml", true);
                Main.inst.getLogger().info("Hoàn thành!");
            }
        }
        
        if (msgVersion == 0 || msgVersion < 2) {
            File f = new File(Main.inst.getDataFolder()+File.separator+"message.yml");
            if (!f.exists()) {
                Main.inst.getLogger().info("Đang tạo file message.yml mới");
                Main.inst.saveResource("message.yml", true);
                Main.inst.getLogger().info("Hoàn thành!");
            } else {
                Main.inst.getLogger().info("Tệp message.yml của bạn đã cũ. Đang tạo file message.yml mới");
                f.delete();
                Main.inst.saveResource("message.yml", true);
                Main.inst.getLogger().info("Hoàn thành!");
            }
        }
    }

}
