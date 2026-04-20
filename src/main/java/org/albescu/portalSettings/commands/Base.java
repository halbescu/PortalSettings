package org.albescu.portalSettings.commands;

import org.albescu.portalSettings.PortalSettings;
import org.albescu.portalSettings.utils.ConfigHandler;
import org.albescu.portalSettings.utils.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public class Base implements CommandExecutor, TabCompleter {
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        // This whole file should be replaced with a more proper command handler, but this works, right?
        if (args.length == 0) {
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "PortalSettings" + ChatColor.GRAY + " v" + PortalSettings.getInstance().getDescription().getVersion());
            sender.sendMessage(ChatColor.GRAY + "Made with " + ChatColor.RED + " ❤ " + ChatColor.GRAY + " by " + ChatColor.RED + "Hue Albescu" + ChatColor.GRAY + "!");
        }

        else if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            ConfigHandler.Reload();
            Logger.Success(sender, "Config reloaded!");
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 1) {
            return List.of("reload");
        }

        return List.of();
    }
}
