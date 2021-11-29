package com.creeperboymcvn.antiop;

import com.creeperboymcvn.antiop.core.listener.ChatEvent;
import com.creeperboymcvn.antiop.core.listener.CommandEvent;
import com.creeperboymcvn.antiop.core.tasks.NewPasswordTask;
import com.creeperboymcvn.antiop.util.Storage;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    
    public static Main inst;
    private Storage message;
    private Storage data;

    @Override
    public void onEnable() {
        setInst(this);
        
        saveDefaultConfig();
        message = new Storage(this, "message.yml");
        data = new Storage(this, "data.yml");
        Updater.check();
        this.getServer().getPluginManager().registerEvents(new ChatEvent(), this);
        this.getServer().getPluginManager().registerEvents(new CommandEvent(), this);
        NewPasswordTask task = new NewPasswordTask();
        task.runTaskTimer(this, 0, this.getConfig().getInt("resetPasswordTimer")*20*60);
    }
    
    private void setInst(Main inst) {
        Main.inst = inst;
    }
    
    public Storage getMessageConf() {
        return message;
    }
    
    public Storage getDataConf() {
        return data;
    }
    
}
