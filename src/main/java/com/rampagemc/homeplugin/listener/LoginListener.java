package com.rampagemc.homeplugin.listener;

import com.rampagemc.homeplugin.HomePlugin;
import com.rampagemc.homeplugin.service.Home;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.io.File;
import java.io.IOException;

public class LoginListener implements Listener {
    private static HomePlugin INSTANCE;

    public LoginListener(HomePlugin plugin) {
        INSTANCE = plugin;
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event){
        Home home = null;
        Player player = event.getPlayer();
        try {
            File file = new File(INSTANCE.getDataFolder() + File.separator + "playerHomes" + File.separator + player.getName() + ".yml");
            if (!file.exists()){
                File dir = new File(INSTANCE.getDataFolder() + File.separator + "playerHomes" + File.separator);
                dir.mkdir();
            }
            home = INSTANCE.mapper.readValue(file, Home.class);

        } catch (IOException e) {
        }
        if (home != null) {
            INSTANCE.homes.put(player, home);
        }
    }
}
