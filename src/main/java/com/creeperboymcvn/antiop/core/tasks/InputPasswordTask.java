package com.creeperboymcvn.antiop.core.tasks;

import com.creeperboymcvn.antiop.core.commandmanager.CommandManager;
import com.creeperboymcvn.antiop.util.Strings;
import com.creeperboymcvn.antiop.util.Values;
import com.creeperboymcvn.antiop.util.hook.discord.EmbedObject;
import com.creeperboymcvn.antiop.util.hook.discord.Webhook;
import java.util.Date;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class InputPasswordTask extends BukkitRunnable {
    
    private final Player player;
    
    public InputPasswordTask (Player p) {
        this.player = p;
    }
    
    int count = Values.__INPUT_PASSWORD_COUNTDOWN;

    @Override
    public void run() {
        if (!player.isOnline()) {
            cancel();
        }
        if (!CommandManager.getIsInputingPassword(player)) {
            cancel();
        }
        player.sendMessage(Strings.colorChat(Values.WARN_MESSAGE.replace("%1", String.valueOf(count))));
        player.sendTitle(Strings.colorChatNoPrefix(Values.WARN_TITLE), Strings.colorChatNoPrefix(Values.WARN_SUBTITLE), 0, 30, 0);
        if (count <= 0) {
            Webhook wh = new Webhook(Values.__DISCORD_WEBHOOK_URL);
                EmbedObject eo = new EmbedObject();
                eo.setTitle(Values.DISCORD_IGNORE_WEBHOOK_EMBED_TITLE);
                eo.setColor(Values.DISCORD_IGNORE_WEBHOOK_EMBED_COLOR);
                eo.setUrlDescription(Values.DISCORD_IGNORE_WEBHOOK_EMBED_URL);
                eo.setDescription(Values.DISCORD_IGNORE_WEBHOOK_EMBED_DESC.replace("%player%", player.getName()));
                eo.setThumbnail(Values.DISCORD_IGNORE_WEBHOOK_EMBED_THUMB.replace("%player%", player.getName()));
                eo.setImage(Values.DISCORD_IGNORE_WEBHOOK_EMBED_IMG);
                eo.setTimeStamp(new Date(System.currentTimeMillis()));
                wh.setContent(Values.DISCORD_IGNORE_WEBHOOK_CONTENT);
                wh.addEmbed(eo.getEmbed());
                wh.sendMessage();
            player.kickPlayer(Strings.colorChat(Values.KICK_MESSAGE));
            cancel();
        }
        count--;
    }

}
