package com.rampagemc.homeplugin.listener;

import com.rampagemc.homeplugin.HomePlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LogoutListener implements Listener {
    private static HomePlugin INSTANCE;

    public LogoutListener(HomePlugin plugin) {
        INSTANCE = plugin;
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event){
        INSTANCE.homes.remove(event.getPlayer());
    }
}
