package com.creeperboymcvn.antiop.core.tasks;

import com.creeperboymcvn.antiop.Main;
import com.creeperboymcvn.antiop.core.commandmanager.CommandManager;
import com.creeperboymcvn.antiop.core.commandmanager.PasswordManager;
import com.creeperboymcvn.antiop.util.DiscordWebhook;
import com.creeperboymcvn.antiop.util.Values;
import org.bukkit.scheduler.BukkitRunnable;

public class NewPasswordTask extends BukkitRunnable {
    
    public NewPasswordTask () {
    
    }

    @Override
    public void run() {
        StringBuilder sb = new StringBuilder();
        for (String s: Main.inst.getDataConf().getConfig().getStringList("playerAllowToDoCommand")) {
            PasswordManager.setPassword(s);
            CommandManager.resetIsPassword();
            sb.append(Values.DISCORD_RESET_PASSWORD_CONTENT.replace("%1", s).replace("%2", PasswordManager.getPassword(s)));
        }
        
        String url = Values.__DISCORD_WEBHOOK_URL;
        if (url.equalsIgnoreCase("")) return;
        
        
        new DiscordWebhook(url, Values.DISCORD_RESET_PASSWORD_TITLE, sb.toString(), Values.DISCORD_RESET_PASSWORD_MESSAGE, "#FF0000");
        DiscordWebhook.sendMessage(Values.DISCORD_RESET_PASSWORD_MESSAGE);
        

    }

}
