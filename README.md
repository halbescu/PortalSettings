# PortalSettings

PortalSettings is a configurable plugin that allows server administrators to control portal creation and teleportation behavior for players and entities on a per-world basis.

It provides:
- Portal creation control
- Player portal teleport control
- Entity portal teleport control

## Permissions

`portalsettings.admin` -> Allows user to reload the config</br>
`portalsettings.[world_name].portal-create` -> Allows user to bypass portal creation restrictions</br>
`portalsettings.[world_name].portal-teleport` -> Allows user to bypass portal teleport restrictions



---

## Default Configuration File

```yaml
# Enable to see plugin logs/info for debugging purposes
debug: false

worlds:
  # Template for general configuration
  some_world_name:
    # -=== General === -

    # Sets whether portals can be created
    # If set to false, it can be bypassed with a permission:
    # portalsettings.[world_name].portal-create
    can-create-portals: true

    # -=== Entities ===-

    # true -> The plugin will NOT block entities (items, mobs, etc.) from teleporting
    # false -> The plugin WILL block entities from teleporting
    can-entities-teleport: true

    # Allows set entities to bypass the teleport block
    # https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/entity/EntityType.html
    entity-whitelist:
      # - ENTITY_ID

    # Blocks set entities from teleporting even if can-entities-teleport is set to true
    entity-blacklist:
      # - ENTITY_ID

    # -=== Players ===-

    # true -> The plugin will NOT block players from teleporting
    # false -> The plugin WILL block players from teleporting
    # If set to false, can be bypassed with a permission:
    # portalsettings.[world_name].portal-teleport
    can-players-teleport: true
```