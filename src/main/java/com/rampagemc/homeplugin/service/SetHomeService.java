package com.rampagemc.homeplugin.service;

import com.rampagemc.homeplugin.HomePlugin;
import com.rampagemc.homeplugin.config.Config;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class SetHomeService {
    private HomePlugin INSTANCE;
    private HashMap<Player, Integer> cooldownSetHome;
    private HashMap<Player, BukkitRunnable> cooldownTaskSetHome;

    public SetHomeService(HomePlugin plugin){
        INSTANCE = plugin;
        cooldownSetHome = new HashMap<>();
        cooldownTaskSetHome = new HashMap<>();
    }

    public void execute(Player player){
        int cooldown = Config.getSetHomeCooldown();
        if (cooldownSetHome.get(player) != null) {
            player.sendMessage(ChatColor.WHITE + "Wait " + ChatColor.RED + cooldownSetHome.get(player) + ChatColor.WHITE + " more seconds");
        } else {
            setHome(player);
            setCooldownSethome(player, cooldown);
            player.sendMessage(ChatColor.WHITE + "Home point set!");
        }
    }

    private void setCooldownSethome(Player player, int cooldown) {
        cooldownSetHome.put(player, cooldown);
        cooldownTaskSetHome.put(player, new BukkitRunnable() {
            public void run() {
                cooldownSetHome.put(player, cooldownSetHome.get(player) - 1);
                if (cooldownSetHome.get(player) == 0) {
                    cooldownSetHome.remove(player);
                    cooldownTaskSetHome.remove(player);
                    cancel();
                }
            }
        });
        cooldownTaskSetHome.get(player).runTaskTimer(INSTANCE, 20, 20);
    }

    private void setHome(Player player) {
        Home home = new Home();
        home.world = player.getWorld().getName();
        home.x = player.getLocation().getX();
        home.y = player.getLocation().getY();
        home.z = player.getLocation().getZ();
        home.yaw = player.getLocation().getYaw();
        home.pitch = player.getLocation().getPitch();
        INSTANCE.homes.put(player, home);
        try {
            File file = new File(INSTANCE.getDataFolder() + File.separator + "playerHomes" + File.separator + player.getName() + ".yml");
            if (!file.exists()){
                File dir = new File(INSTANCE.getDataFolder() + File.separator + "playerHomes" + File.separator);
                dir.mkdir();
            }
            file.createNewFile();
            INSTANCE.mapper.writeValue(file, home);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
