package com.zhomegui;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HomesAPI {
    public static boolean setHome(String home,Location l, Player p){
        FileConfiguration config = Main.getPlugin(Main.class).getConfig();
        if(config.contains("data."+p.getUniqueId().toString()+"."+home+"."+"location.world")){
            return false;
        }
        config.set("data."+p.getUniqueId().toString()+"."+home+"."+"location.world",l.getWorld().getName());
        config.set("data."+p.getUniqueId().toString()+"."+home+"."+"location.z",l.getZ());
        config.set("data."+p.getUniqueId().toString()+"."+home+"."+"location.x",l.getX());
        config.set("data."+p.getUniqueId().toString()+"."+home+"."+"location.y",l.getY());
        Main.getPlugin(Main.class).saveConfig();
        return true;
    }
    public static boolean delHome(String home, Player p){
        FileConfiguration config = Main.getPlugin(Main.class).getConfig();
        if(!config.contains("data."+p.getUniqueId().toString()+"."+home+"."+"location.world")){
            return false;
        }else{
            config.set("data."+p.getUniqueId().toString()+"."+home+"."+"location.world",null);
            config.set("data."+p.getUniqueId().toString()+"."+home+"."+"location.z",null);
            config.set("data."+p.getUniqueId().toString()+"."+home+"."+"location.x",null);
            config.set("data."+p.getUniqueId().toString()+"."+home+"."+"location.y",null);
            config.set("data."+p.getUniqueId().toString()+"."+home+"."+"location",null);
            config.set("data."+p.getUniqueId().toString()+"."+home,null);
            Main.getPlugin(Main.class).saveConfig();
            return true;
        }

    }
    public static boolean home(String home, Player p){
        FileConfiguration config = Main.getPlugin(Main.class).getConfig();
        if(!config.contains("data."+p.getUniqueId().toString()+"."+home)){
            return false;
        }else{
            Location l = new Location(Bukkit.getWorld(config.getString("data."+p.getUniqueId().toString()+"."+home+"."+"location.world")),config.getDouble("data."+p.getUniqueId().toString()+"."+home+"."+"location.x"),config.getDouble("data."+p.getUniqueId().toString()+"."+home+"."+"location.y"),config.getDouble("data."+p.getUniqueId().toString()+"."+home+"."+"location.z"));
            p.teleport(l);
            return true;
        }

    }
    public static List<String> getHomes(Player p){
        try{
        FileConfiguration config = Main.getPlugin(Main.class).getConfig();
        List<String> homes = new ArrayList<>();
        for (String key : config.getConfigurationSection("data."+p.getUniqueId().toString()).getKeys(false)) {
            ConfigurationSection block = config.getConfigurationSection("data."+p.getUniqueId().toString() + "." +  key);
            homes.add(key);
        }
        if(homes.isEmpty()){
            return null;
        }
            return homes;
        }catch(Exception e){
            return null;
        }

    }
}
