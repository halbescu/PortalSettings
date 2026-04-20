package org.albescu.portalSettings.classes;

import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;

public class WorldSettings {
    public final  String worldName;
    private final boolean canCreatePortals;
    private final boolean canEntitiesTeleport;
    private final boolean canPlayersTeleport;
    private final List<EntityType> entityWhitelist;
    private final List<EntityType> entityBlacklist;

    public WorldSettings(
            boolean canCreatePortals,
            String worldName,
            boolean canEntitiesTeleport,
            boolean canPlayersTeleport,
            List<EntityType> entityWhitelist,
            List<EntityType> entityBlacklist
    ) {
        this.canCreatePortals = canCreatePortals;
        this.worldName = worldName; // Might not actually be needed at all lol
        this.canEntitiesTeleport = canEntitiesTeleport;
        this.canPlayersTeleport = canPlayersTeleport;
        this.entityWhitelist = entityWhitelist;
        this.entityBlacklist = entityBlacklist;
    }

    public WorldSettings() {
        canCreatePortals = true;
        this.worldName = "default";
        this.canEntitiesTeleport = true;
        this.canPlayersTeleport = true;
        this.entityWhitelist = new ArrayList<>();
        this.entityBlacklist = new ArrayList<>();
    }

    // General
    public String getWorldName() {
        return worldName;
    }

    public boolean canCreatePortals() {
        return canCreatePortals;
    }

    // Entities
    public boolean canEntitiesTeleport() {
        return canEntitiesTeleport;
    }

    public boolean isEntityWhitelisted(EntityType entityType) {
        return  entityWhitelist.contains(entityType);
    }

    public boolean isEntityBlacklisted(EntityType entityType) {
        return  entityBlacklist.contains(entityType);
    }

    // Players
    public boolean canPlayersTeleport() {
        return canPlayersTeleport;
    }


}
