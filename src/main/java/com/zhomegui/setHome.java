package com.zhomegui;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setHome implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)){
            return true;
        }
        if(strings.length < 1 || strings.length > 1){
            commandSender.sendMessage("§cUso: /sethome <nome>");
            return true;
        }
        String nome = strings[0];
        boolean hm = HomesAPI.setHome(nome,((Player) commandSender).getPlayer().getLocation(),((Player) commandSender).getPlayer());
        if(hm){
            commandSender.sendMessage(String.format("§aHome %s definida com sucesso!", nome));
        }else{
            commandSender.sendMessage(String.format("§cA home %s já existe!", nome));
        }
        return false;
    }
}
