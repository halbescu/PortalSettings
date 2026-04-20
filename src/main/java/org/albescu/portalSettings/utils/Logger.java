package org.albescu.portalSettings.utils;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.albescu.portalSettings.PortalSettings;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Logger {
    private static final Plugin plugin = PortalSettings.getInstance();
    private static final String prefix = ChatColor.GRAY + "[" + ChatColor.DARK_PURPLE + "PortalSettings" + ChatColor.GRAY + "] ";

    /**
     * Used for plain console output.
     *
     * @param msg Message to be sent.
     */
    public static void Console(String msg) {
        Bukkit.getConsoleSender().sendMessage(prefix + ChatColor.GRAY + msg);
    }

    /**
     * Used for warning console output.
     *
     * @param msg Message to be sent.
     */
    public static void ConsoleWarning(String msg) {
        plugin.getLogger().warning(msg);
    }

    /**
     * Used for error console output.
     *
     * @param msg Message to be sent.
     */
    public static void ConsoleError(String msg) {
        plugin.getLogger().severe(msg);
    }

    /**
     * Used for plain player output.
     *
     * @param recipient In-game player recipient.
     * @param msg       Message to be sent.
     */
    public static void Message(CommandSender recipient, String msg) {
        recipient.sendMessage(prefix + ChatColor.GRAY + msg);
    }

    /**
     * Used for plain player output.
     *
     * @param recipient In-game player recipient.
     * @param msg       Message to be sent.
     */
    public static void Message(CommandSender recipient, TextComponent msg) {
        TextComponent pre = new TextComponent(prefix);
        pre.addExtra(msg);
        recipient.spigot().sendMessage(pre);
    }

    /**
     * Used for success player output.
     *
     * @param recipient In-game player recipient.
     * @param msg       Message to be sent.
     */
    public static void Success(CommandSender recipient, String msg) {
        recipient.sendMessage(prefix + ChatColor.GREEN + msg);
    }

    /**
     * Used for error player output.
     *
     * @param recipient In-game player recipient.
     * @param msg       Message to be sent.
     */
    public static void Error(CommandSender recipient, String msg) {
        recipient.sendMessage(ChatColor.GRAY + "[" + ChatColor.LIGHT_PURPLE + "VoreMe" + ChatColor.GRAY + "] " + ChatColor.RED + msg);
    }
}
