package com.zhomegui;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class delHome implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)){
            return true;
        }
        if(strings.length < 1 || strings.length > 1){
            commandSender.sendMessage("§cUso: /delhome <nome>");
            return true;
        }
        String nome = strings[0];
        boolean hm = HomesAPI.delHome(nome,((Player) commandSender).getPlayer());
        if(hm){
            commandSender.sendMessage(String.format("§aHome %s foi deletada com sucesso!", nome));
        }else{
            commandSender.sendMessage(String.format("§cA home %s não existe!", nome));
        }
        return false;
    }
}
