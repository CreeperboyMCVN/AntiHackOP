package com.creeperboymcvn.antiop.core.commandmanager;

import com.creeperboymcvn.antiop.Main;
import com.creeperboymcvn.antiop.core.tasks.InputPasswordTask;
import org.bukkit.entity.Player;

public class InputPassword {
    
    public static void start(Player player) {
        CommandManager.setIsInputingPassword(player, true);
        InputPasswordTask temp = new InputPasswordTask(player);
        temp.runTaskTimer(Main.inst, 0, 20);
    }

}
