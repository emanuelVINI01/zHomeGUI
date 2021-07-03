package com.zhomegui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class GUI implements CommandExecutor, Listener {
    public void createGUI(Player p){
        Inventory inv = Bukkit.createInventory(null,36,Main.getPlugin(Main.class).nomedomenu);
        FileConfiguration config = Main.getPlugin(Main.class).getConfig();
        ItemStack perfilitem = new ItemStack(Material.SKULL_ITEM);
        SkullMeta perfilmeta = (SkullMeta) perfilitem.getItemMeta();
        perfilmeta.setDisplayName("§eSeu perfil");
        List<String> lo = new ArrayList<>();
        try {
            lo.add(String.format("§eQuantia de homes: %s", HomesAPI.getHomes(p).size()));
        }catch (Exception e){
            lo.add(String.format("§eQuantia de homes: %s", 0));
        }
        perfilmeta.setOwner(p.getName());
        perfilmeta.setLore(lo);
        perfilitem.setItemMeta(perfilmeta);
        inv.setItem(4,perfilitem);
        int in = 10;
        try{
        for(String home : HomesAPI.getHomes(p)){
            ItemStack homeitem = new ItemStack(Material.getMaterial(config.getString("menu.home.material")));
            ItemMeta homemeta = homeitem.getItemMeta();
            homemeta.setDisplayName(String.format(config.getString("menu.home.nome"), home));
            homemeta.setLore(config.getStringList("menu.home.lore"));
            homeitem.setItemMeta(homemeta);
            inv.setItem(in, homeitem);
            in++;
        }
            p.openInventory(inv);
        }catch (Exception e){
            p.sendMessage("§cPara abrir o menu, defina alguma home com /sethome.");
        }
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)){
            return true;
        }
        createGUI(((Player) commandSender).getPlayer());
        return false;
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        FileConfiguration config = Main.getPlugin(Main.class).getConfig();
        if(e.getClickedInventory().getName().equalsIgnoreCase(Main.getPlugin(Main.class).nomedomenu)){
            if(e.getClickedInventory().getItem(e.getSlot()).getItemMeta().getDisplayName().contains(config.getString("menu.home.nome").replace("%s",""))){
                String home = e.getClickedInventory().getItem(e.getSlot()).getItemMeta().getDisplayName();
                String s1 = home.substring(home.indexOf(" ")+1);
                s1.trim();
                Player p = (Player) e.getWhoClicked();
                p.performCommand(String.format("home %s", s1));
            }
            e.setCancelled(true);
        }
    }
}
