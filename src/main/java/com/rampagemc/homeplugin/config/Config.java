package com.rampagemc.homeplugin.config;

import com.rampagemc.homeplugin.HomePlugin;
import org.bukkit.configuration.file.FileConfiguration;

import javax.swing.*;

public class Config {
    private static FileConfiguration config;

    public Config(HomePlugin plugin){
        config = plugin.getConfig();
        config.addDefault("sethome_delay", 5);
        config.addDefault("home_delay", 5);
        config.options().copyDefaults(true);
        plugin.saveConfig();
    }

    public static int getSetHomeCooldown(){
        return config.getInt("sethome_delay");
    }

    public static int getHomeCooldown(){
        return config.getInt("home_delay");
    }
}
