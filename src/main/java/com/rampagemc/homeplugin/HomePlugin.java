package com.rampagemc.homeplugin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.rampagemc.homeplugin.command.HomeCommand;
import com.rampagemc.homeplugin.command.SetHomeCommand;
import com.rampagemc.homeplugin.config.Config;
import com.rampagemc.homeplugin.listener.LoginListener;
import com.rampagemc.homeplugin.listener.LogoutListener;
import com.rampagemc.homeplugin.listener.RespawnListener;
import com.rampagemc.homeplugin.service.Home;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class HomePlugin extends JavaPlugin {
    public HashMap<Player, Home> homes;
    public final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    private Config config;

    @Override
    public void onEnable() {
        config = new Config(this);
        homes = new HashMap<>();

        getServer().getPluginManager().registerEvents(new LoginListener(this), this);
        getServer().getPluginManager().registerEvents(new LogoutListener(this), this);
        getServer().getPluginManager().registerEvents(new RespawnListener(this), this);
        getCommand("sethome").setExecutor(new SetHomeCommand(this));
        getCommand("home").setExecutor(new HomeCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
