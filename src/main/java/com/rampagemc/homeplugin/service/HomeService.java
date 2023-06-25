package com.rampagemc.homeplugin.service;

import com.rampagemc.homeplugin.HomePlugin;
import com.rampagemc.homeplugin.config.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class HomeService {
    private HomePlugin INSTANCE;
    private HashMap<Player, Integer> cooldownHome;
    private HashMap<Player, BukkitRunnable> cooldownTaskHome;

    public HomeService(HomePlugin plugin){
        INSTANCE = plugin;
        cooldownHome = new HashMap<>();
        cooldownTaskHome = new HashMap<>();
    }

    public void execute(Player player){
        int cooldown = Config.getHomeCooldown();
        if (cooldownHome.containsKey(player)) {
            player.sendMessage(ChatColor.WHITE + "Wait " + ChatColor.RED + cooldownHome.get(player) + ChatColor.WHITE + " more seconds");
        } else {
            if (tpToHome(player)) {
                setCooldownHome(player, cooldown);
            }
        }
    }

    public boolean tpToHome(Player player) {
        Home home = INSTANCE.homes.get(player);
        if (home == null){
            player.sendMessage(ChatColor.RED + "You must /sethome first!");
            return false;
        }
        player.teleport(new Location(Bukkit.getWorld(home.world), home.x, home.y, home.z, home.yaw, home.pitch));
        return true;
    }

    void setCooldownHome(Player player, int coolDown) {
        cooldownHome.put(player, coolDown);
        cooldownTaskHome.put(player, new BukkitRunnable() {
            public void run() {
                cooldownHome.put(player, cooldownHome.get(player) - 1);
                if (cooldownHome.get(player) == 0) {
                    cooldownHome.remove(player);
                    cooldownTaskHome.remove(player);
                    cancel();
                }
            }
        });
        cooldownTaskHome.get(player).runTaskTimer(INSTANCE, 20, 20);
    }
}
