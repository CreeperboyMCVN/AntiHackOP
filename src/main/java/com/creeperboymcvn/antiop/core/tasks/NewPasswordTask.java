package com.creeperboymcvn.antiop.core.tasks;

import com.creeperboymcvn.antiop.Main;
import com.creeperboymcvn.antiop.core.commandmanager.CommandManager;
import com.creeperboymcvn.antiop.core.commandmanager.PasswordManager;
import com.creeperboymcvn.antiop.util.hook.discord.Webhook;
import com.creeperboymcvn.antiop.util.Values;
import com.creeperboymcvn.antiop.util.hook.discord.EmbedObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
            sb.append(Values.DISCORD_RESET_PASSLIST.replace("%1", s).replace("%2", PasswordManager.getPassword(s)));
        }
        
        String url = Values.__DISCORD_WEBHOOK_URL;
        if (url.equalsIgnoreCase("")) return;
        
        
        Webhook wh = new Webhook(url);
        EmbedObject eo = new EmbedObject();
        eo.setTitle(Values.DISCORD_RESET_WEBHOOK_EMBED_TITLE);
        eo.setColor(Values.DISCORD_RESET_WEBHOOK_EMBED_COLOR);
        eo.setUrlDescription(Values.DISCORD_RESET_WEBHOOK_EMBED_URL);
        eo.setDescription(Values.DISCORD_RESET_WEBHOOK_EMBED_DESC.replace("%passwordList%", sb.toString()));
        eo.setThumbnail(Values.DISCORD_RESET_WEBHOOK_EMBED_THUMB);
        eo.setImage(Values.DISCORD_RESET_WEBHOOK_EMBED_IMG);
        eo.setTimeStamp(new Date(System.currentTimeMillis()));
        wh.setContent(Values.DISCORD_RESET_WEBHOOK_CONTENT);
        wh.addEmbed(eo.getEmbed());
        wh.sendMessage();
    }

}
