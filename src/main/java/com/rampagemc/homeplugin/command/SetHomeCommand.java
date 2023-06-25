package com.rampagemc.homeplugin.command;

import com.rampagemc.homeplugin.HomePlugin;
import com.rampagemc.homeplugin.service.SetHomeService;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetHomeCommand implements CommandExecutor {
    private HomePlugin INSTANCE;
    private SetHomeService setHomeService;

    public SetHomeCommand(HomePlugin plugin){
        INSTANCE = plugin;
        setHomeService = new SetHomeService(INSTANCE);
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (command.getName().equals("sethome")) {
                setHomeService.execute(player);
            }
        }
        return true;
    }
}
