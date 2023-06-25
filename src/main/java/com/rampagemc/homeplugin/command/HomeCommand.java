package com.rampagemc.homeplugin.command;

import com.rampagemc.homeplugin.HomePlugin;
import com.rampagemc.homeplugin.service.HomeService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HomeCommand implements CommandExecutor {

    private HomePlugin INSTANCE;
    private HomeService homeService;

    public HomeCommand(HomePlugin plugin){
        INSTANCE = plugin;
        homeService = new HomeService(INSTANCE);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (command.getName().equals("home")) {
                homeService.execute(player);
            }
        }
        return true;
    }
}
