package com.rampagemc.homeplugin.listener;

import com.rampagemc.homeplugin.HomePlugin;
import com.rampagemc.homeplugin.service.Home;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnListener implements Listener {
    private static HomePlugin INSTANCE;

    public RespawnListener(HomePlugin plugin) {
        INSTANCE = plugin;
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Home home = INSTANCE.homes.get(event.getPlayer());
        if (home != null) {
            Location location = new Location(Bukkit.getWorld(home.world), home.x, home.y, home.z, home.yaw, home.pitch);
            event.setRespawnLocation(location);
        }
    }

}
