package org.albescu.portalSettings.events;

import org.albescu.portalSettings.classes.WorldSettings;
import org.albescu.portalSettings.utils.ConfigHandler;
import org.albescu.portalSettings.utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPortalEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.plugin.Plugin;

public class PortalEventListener implements Listener {
    public PortalEventListener(Plugin _plugin) {
        Bukkit.getPluginManager().registerEvents(this, _plugin);
    }

    @EventHandler
    public void onEntityPortalEvent(EntityPortalEvent event) {
        Location from =  event.getFrom();

        if (ConfigHandler.isDebugMode()) {
            Logger.Console("Portal attempted to teleport entity (" + event.getEntity().getName() + ") in " + from.getWorld().getName());
        }

        // Check if in world configs?
        WorldSettings settings =  ConfigHandler.getWorldSettings(from.getWorld().getName());
        EntityType entityType = event.getEntity().getType();

        // Blacklisted entities plain don't go at all
        if (settings.isEntityBlacklisted(entityType)) {
            if (ConfigHandler.isDebugMode()) {
                Logger.Console("Entity is blacklisted!");
            }
            event.setCancelled(true);
            return;
        }

        // If teleporting is enabled, no need to check anything else
        if (settings.canEntitiesTeleport()) {
            if (ConfigHandler.isDebugMode()) {
                Logger.Console("Teleporting is enabled!");
            }
            return;
        }

        // If teleporting is not enabled, only whitelisted go through
        if (settings.isEntityWhitelisted(entityType)) {
            if (ConfigHandler.isDebugMode()) {
                Logger.Console("Entity is whitelisted!");
            }
            return;
        }

        // Entity didn't pass any of the checks, cancel teleport
        if (ConfigHandler.isDebugMode()) {
            Logger.Console("Teleport was blocked!");
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerPortalEvent(PlayerPortalEvent event) {
        Location from =  event.getFrom();

        if (ConfigHandler.isDebugMode()) {
            Logger.Console("Portal attempted to teleport player (" + event.getPlayer().getName() + ") in " + from.getWorld().getName());
        }

        // Check if in world configs?
        WorldSettings settings =  ConfigHandler.getWorldSettings(from.getWorld().getName());
        Player player = event.getPlayer();

        // If player has bypass permission, nothing else matters
        if (player.hasPermission("portalsettings."+ from.getWorld().getName() + ".portal-teleport")) {
            if (ConfigHandler.isDebugMode()) {
                Logger.Console("Player had bypass permission!");
            }
            return;
        }

        // If teleporting is enabled, let em through
        if (settings.canPlayersTeleport()) {
            if (ConfigHandler.isDebugMode()) {
                Logger.Console("Teleporting is enabled!");
            }
            return;
        }

        // If no checks are passed, cancel the teleport
        if (ConfigHandler.isDebugMode()) {
            Logger.Console("Teleport was blocked!");
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onCreatePortal(PortalCreateEvent event) {
        World world = event.getWorld();
        WorldSettings settings = ConfigHandler.getWorldSettings(world.getName());

        if (ConfigHandler.isDebugMode()) {
            Logger.Console("Attempted to create portal in " + world.getName());
        }

        // If enabled, that's it
        if (settings.canCreatePortals()) {
            if (ConfigHandler.isDebugMode()) {
                Logger.Console("Creating portals is enabled!");
            }
            return;
        }

        // If it's not enabled, check if the player has a permission to do it anyways
        Entity init = event.getEntity();
        if (init instanceof Player) {
            Player p =  (Player) init;
            if (p.hasPermission("portalsettings." + world.getName() + ".portal-create")) {
                if (ConfigHandler.isDebugMode()) {
                    Logger.Console("Created portal through permission bypass!");
                }
                return;
            }
        }

        // Blocked, so cancel it
        if (ConfigHandler.isDebugMode()) {
            Logger.Console("Creation is disabled!");
        }
        event.setCancelled(true);
    }
}
