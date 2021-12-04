package com.creeperboymcvn.antiop.command;

import com.creeperboymcvn.antiop.Main;
import com.creeperboymcvn.antiop.util.Strings;
import com.creeperboymcvn.antiop.util.Values;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

public class AntiOpCommand implements TabExecutor {
    
    List<String> tab;
    
    public AntiOpCommand() {
        tab = new ArrayList<>();
        tab.add("reload");
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args[0].equalsIgnoreCase("reload")&&sender.hasPermission("antibypasslogin.reload")) {
            Main.reloadPlugin();
            sender.sendMessage(Strings.colorChat(Values.RELOAD));
            return true;
        }
        sender.sendMessage(Strings.colorChat(Values.UNKNOWN_COMMAND));
        return true;
    }
    
    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        return tab;
    }

}
