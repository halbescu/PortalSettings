package org.albescu.portalSettings;

import org.albescu.portalSettings.commands.Base;
import org.albescu.portalSettings.events.PortalEventListener;
import org.albescu.portalSettings.utils.ConfigHandler;
import org.albescu.portalSettings.utils.Logger;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

public final class PortalSettings extends JavaPlugin {

    private static PortalSettings instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        setupConfig();

        Logger.Console(ChatColor.GREEN + "Registering event listeners...");
        new PortalEventListener(this);
        getCommand("portalsettings").setExecutor(new Base());



        Logger.Console(ChatColor.GREEN + "Plugin loaded!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Logger.Console(ChatColor.RED + "Shutting down!");
    }

    public void setupConfig() {
        saveDefaultConfig();
        ConfigHandler.Reload();
    }

    public static PortalSettings getInstance() {
        return instance;
    }
}
