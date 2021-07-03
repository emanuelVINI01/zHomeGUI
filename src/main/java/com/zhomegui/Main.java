package com.zhomegui;

import com.zloader.Loader;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {
    public static String nomedomenu = "";
    public static List<String> setandohome = new ArrayList<>();
    public static boolean byLoader = false;
    @Override
    public void onEnable() {
        try {
            if (getServer().getPluginManager().getPlugin("zLoader") != null) {
                Loader l = (Loader) Bukkit.getServer().getPluginManager().getPlugin("zLoader");
                if (l.zhomegui) {
                    byLoader = true;
                }
            }
        }catch (Exception e){

        }
        if(!byLoader){
            getLogger().warning("Você não pode ativar o plugin direto do bukkit.");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }
        nomedomenu = getConfig().getString("nomedomenu");
        super.onEnable();
        saveDefaultConfig();
        getLogger().info("\nAutor: emanuel VINI\nVersão: 1.1\nSuporte: emanuel VINI#8000");
        getCommand("homes").setExecutor(new GUI());
        getCommand("sethome").setExecutor(new setHome());
        getCommand("delhome").setExecutor(new delHome());
        getCommand("home").setExecutor(new home());
        getServer().getPluginManager().registerEvents(new GUI(),this);
    }
}
