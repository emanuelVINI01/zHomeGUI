package com.zhomegui;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class home implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)){
            return true;
        }
        if(strings.length < 1 || strings.length > 1){
            commandSender.sendMessage("§cUso: /home <nome>");
            return true;
        }
        String nome = strings[0];
        boolean hm = HomesAPI.home(nome,((Player) commandSender).getPlayer());
        if(hm){
            commandSender.sendMessage(String.format("§aVocê se teleportou para a home %s com sucesso!", nome));
        }else{
            try {
                commandSender.sendMessage(String.format("§cA home %s não existe! As homes que existem são %s", nome, HomesAPI.getHomes(((Player) commandSender).getPlayer())));
            }catch(Exception e){
                commandSender.sendMessage(String.format("§cA home %s não existe! As homes que existem são [§cNenhuma home encontrada!]", nome));
            }
        }
        return false;
    }
}
