package com.creeperboymcvn.antiop.core.listener;

import com.creeperboymcvn.antiop.Main;
import com.creeperboymcvn.antiop.core.commandmanager.CommandManager;
import com.creeperboymcvn.antiop.core.commandmanager.PasswordManager;
import com.creeperboymcvn.antiop.util.Strings;
import com.creeperboymcvn.antiop.util.Values;
import com.creeperboymcvn.antiop.util.hook.discord.EmbedObject;
import com.creeperboymcvn.antiop.util.hook.discord.Webhook;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {
    
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (CommandManager.getIsInputingPassword(e.getPlayer())) {
            if (!e.getMessage().contains(PasswordManager.getPassword(e.getPlayer()))) {
                e.getPlayer().sendMessage(Strings.colorChat(Values.WRONG_PASS));
                e.setCancelled(true);
            } else {
                CommandManager.setIsInputingPassword(e.getPlayer(), false);
                CommandManager.setIsPassword(e.getPlayer(), true);
                long nextReset = Main.inst.getDataConf().getConfig().getLong("nextReset");
                Date date = new Date(nextReset);
                DateFormat df = new SimpleDateFormat("HH:mm:ss");
                String nextDate = df.format(date);
                e.getPlayer().sendMessage(Strings.colorChat(Values.PASSED.replace("%1", nextDate)));
                
                Webhook wh = new Webhook(Values.__DISCORD_WEBHOOK_URL);
                EmbedObject eo = new EmbedObject();
                eo.setTitle(Values.DISCORD_ACCEPT_WEBHOOK_EMBED_TITLE);
                eo.setColor(Values.DISCORD_ACCEPT_WEBHOOK_EMBED_COLOR);
                eo.setUrlDescription(Values.DISCORD_ACCEPT_WEBHOOK_EMBED_URL);
                eo.setDescription(Values.DISCORD_ACCEPT_WEBHOOK_EMBED_DESC.replace("%player%", e.getPlayer().getName()));
                eo.setThumbnail(Values.DISCORD_ACCEPT_WEBHOOK_EMBED_THUMB.replace("%player%", e.getPlayer().getName()));
                eo.setImage(Values.DISCORD_ACCEPT_WEBHOOK_EMBED_IMG);
                eo.setTimeStamp(new Date(System.currentTimeMillis()));
                wh.setContent(Values.DISCORD_ACCEPT_WEBHOOK_CONTENT);
                wh.addEmbed(eo.getEmbed());
                wh.sendMessage();
                
                
                e.setCancelled(true);
            }
        }
    }

}
