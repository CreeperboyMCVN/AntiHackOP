package com.creeperboymcvn.antiop.core.listener;

import com.creeperboymcvn.antiop.Main;
import com.creeperboymcvn.antiop.core.commandmanager.CommandManager;
import com.creeperboymcvn.antiop.core.commandmanager.PasswordManager;
import com.creeperboymcvn.antiop.util.Strings;
import com.creeperboymcvn.antiop.util.Values;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class ChatEvent implements Listener {
    
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (CommandManager.getIsInputingPassword(e.getPlayer())) {
            Bukkit.getConsoleSender().sendMessage("Is inputing password: TRUE");
            if (!e.getMessage().contains(PasswordManager.getPassword(e.getPlayer()))) {
                Bukkit.getConsoleSender().sendMessage("Wrong Password: TRUE");
                e.getPlayer().sendMessage(Strings.colorChat(Values.WRONG_PASS));
                e.setCancelled(true);
            } else {
                Bukkit.getConsoleSender().sendMessage("Wrong Password: FALSE");
                CommandManager.setIsInputingPassword(e.getPlayer(), false);
                CommandManager.setIsPassword(e.getPlayer(), true);
                long nextReset = Main.inst.getDataConf().getConfig().getLong("nextReset");
                Date date = new Date(nextReset);
                DateFormat df = new SimpleDateFormat("HH:mm:ss");
                String nextDate = df.format(date);
                e.getPlayer().sendMessage(Strings.colorChat(Values.PASSED.replace("%1", nextDate)));
                e.setCancelled(true);
            }
        }
    }

}
