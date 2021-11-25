package com.creeperboymcvn.antiop.core.tasks;

import com.creeperboymcvn.antiop.core.commandmanager.CommandManager;
import com.creeperboymcvn.antiop.util.Strings;
import com.creeperboymcvn.antiop.util.Values;
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
        player.sendTitle(Strings.colorChatNoPrefix(Values.WARN_TITLE), Strings.colorChatNoPrefix(Values.WARN_SUBTITLE), 0, 20, 0);
        if (count <= 0) {
            player.kickPlayer(Strings.colorChat(Values.KICK_MESSAGE));
            cancel();
        }
        count--;
    }

}
