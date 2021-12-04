package com.creeperboymcvn.antiop;

import com.creeperboymcvn.antiop.command.AntiOpCommand;
import com.creeperboymcvn.antiop.core.listener.ChatEvent;
import com.creeperboymcvn.antiop.core.listener.CommandEvent;
import com.creeperboymcvn.antiop.core.tasks.NewPasswordTask;
import com.creeperboymcvn.antiop.util.Storage;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    
    public static Main inst;
    private Storage message;
    private Storage data;
    private static NewPasswordTask task;

    @Override
    public void onEnable() {
        setInst(this);
        
        saveDefaultConfig();
        message = new Storage(this, "message.yml");
        data = new Storage(this, "data.yml");
        Updater.check();
        this.getServer().getPluginManager().registerEvents(new ChatEvent(), this);
        this.getServer().getPluginManager().registerEvents(new CommandEvent(), this);
        task = new NewPasswordTask();
        task.runTaskTimer(this, 0, this.getConfig().getInt("resetPasswordTimer")*20*60);
        this.getCommand("antibypasslogin").setExecutor(new AntiOpCommand());
        this.getCommand("antibypasslogin").setTabCompleter(new AntiOpCommand());
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
    
    public static void reloadPlugin() {
        Main.inst.getMessageConf().reloadConfig();
        Main.inst.getDataConf().reloadConfig();
        Main.inst.reloadConfig();
        task.cancel();
        task = new NewPasswordTask();
        task.runTaskTimer(Main.inst, 0, Main.inst.getConfig().getInt("resetPasswordTimer")*20*60);
    }
    
}
