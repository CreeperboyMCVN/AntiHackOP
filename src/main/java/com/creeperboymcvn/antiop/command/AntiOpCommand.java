package com.creeperboymcvn.antiop.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AntiOpCommand implements CommandExecutor {
    
    public AntiOpCommand() {
    
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        return true;
    }

}
