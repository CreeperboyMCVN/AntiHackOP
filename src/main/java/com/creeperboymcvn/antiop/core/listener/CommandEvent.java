package com.creeperboymcvn.antiop.core.listener;

import com.creeperboymcvn.antiop.Main;
import com.creeperboymcvn.antiop.core.commandmanager.CommandManager;
import com.creeperboymcvn.antiop.core.commandmanager.InputPassword;
import com.creeperboymcvn.antiop.util.Strings;
import com.creeperboymcvn.antiop.util.Values;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandEvent implements Listener {
    
    
    
    public CommandEvent() {
    }
    
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        List<String> commands = Main.inst.getConfig().getStringList("checkCommand");
        
        if (CommandManager.getIsInputingPassword(e.getPlayer())) {
            e.getPlayer().sendMessage(Strings.colorChat(Values.INPUTING_PASSWORD));
            return;
        }
        //Bukkit.getPlayer("CreeperboyMCVN").sendMessage("[PlayerCommandPreprocessEvent Debugger] var 'commands', value: "+commands.toString());
        //Bukkit.getPlayer("CreeperboyMCVN").sendMessage("[PlayerCommandPreprocessEvent Debugger] var 'e.getMessage()', value: "+e.getMessage());
        if (!CommandManager.isCheckCommand(commands, e.getMessage())) {
            //Bukkit.getPlayer("CreeperboyMCVN").sendMessage("[PlayerCommandPreprocessEvent Debugger] boolean '!CommandManager.isCheckCommand(commands, e.getMessage())' return false");
            return;
        }
        
        
        if (!CommandManager.isPlayerInList(e.getPlayer())) {
            //Bukkit.getPlayer("CreeperboyMCVN").sendMessage("[PlayerCommandPreprocessEvent Debugger] boolean '!CommandManager.isPlayerInList(e.getPlayer())' return false");
            e.getPlayer().sendMessage(Strings.colorChat(Values.NOT_IN_PLAYER_LIST));
            e.setCancelled(true);
            return;
        }
        
        if (!CommandManager.getIsPassword(e.getPlayer())) {
            //Bukkit.getPlayer("CreeperboyMCVN").sendMessage("[PlayerCommandPreprocessEvent Debugger] boolean '!CommandManager.getIsPassword(e.getPlayer())' return false");
            //input password
            InputPassword.start(e.getPlayer());
            e.setCancelled(true);
        }
        
    }
}
