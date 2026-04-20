package org.albescu.portalSettings.utils;

import org.albescu.portalSettings.PortalSettings;
import org.albescu.portalSettings.classes.WorldSettings;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler {
    static FileConfiguration config = PortalSettings.getInstance().getConfig();
    static HashMap<String, WorldSettings> worldSettings = new HashMap<>();

    public static void Reload() {
        PortalSettings.getInstance().reloadConfig();
        config = PortalSettings.getInstance().getConfig();

        worldSettings.clear();

        ConfigurationSection worldSection = config.getConfigurationSection("worlds");
        if (worldSection == null) {
            Logger.ConsoleError("Worlds section not found in config!");
            return;
        }

        for (String worldName : worldSection.getKeys(false)) {
            ConfigurationSection section = worldSection.getConfigurationSection(worldName);
            if (section == null) {
                Logger.ConsoleError("Settings for " + worldName + " were null!");
                return;
            }

            boolean canCreatePortals = section.getBoolean("can-create-portals", true);
            boolean canEntitiesTeleport  = section.getBoolean("can-entities-teleport", true);
            boolean canPlayersTeleport  = section.getBoolean("can-players-teleport", true);


            List<EntityType> entityWhitelist = new ArrayList<>();
            for (String entityName : section.getStringList("entity-whitelist")) {
                try {
                    EntityType entity = EntityType.valueOf(entityName.toUpperCase());
                    entityWhitelist.add(entity);
                }

                catch (Exception e) {
                    Logger.ConsoleError(entityName + " is not a valid entity!");
                }
            }

            List<EntityType> entityBlacklist = new ArrayList<>();
            for (String entityName : section.getStringList("entity-blacklist")) {
                try {
                    EntityType entity = EntityType.valueOf(entityName.toUpperCase());
                    entityBlacklist.add(entity);
                }

                catch (Exception e) {
                    Logger.ConsoleError(entityName + " is not a valid entity!");
                }
            }
            worldSettings.put(worldName,
                    new WorldSettings(
                            canCreatePortals,
                            worldName,
                            canEntitiesTeleport,
                            canPlayersTeleport,
                            entityWhitelist,
                            entityBlacklist
                    )
            );
        }
    }

    public static WorldSettings getWorldSettings(String worldName) {
        // Null handling
        if (worldName == null) {
            if (ConfigHandler.isDebugMode()) {
                Logger.ConsoleError("World name was null!");
            }
        }

        WorldSettings settings = worldSettings.get(worldName);

        // Don't have settings for this world? Return defaults
        if (settings == null) return new WorldSettings();

        // Actual return
        return worldSettings.get(worldName);
    }

    public static boolean isDebugMode() {
        return config.getBoolean("debug");
    }
}
