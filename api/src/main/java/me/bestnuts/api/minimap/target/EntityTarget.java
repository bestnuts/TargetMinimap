package me.bestnuts.api.minimap.target;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class EntityTarget implements Target {

    private final Entity entity;

    public EntityTarget(Entity entity) {
        this.entity = entity;
    }

    @Override
    public Location getLocation() {
        return entity.getLocation();
    }

    @Override
    public boolean isValid() {
        return entity.isValid();
    }

    public Entity getEntity() {
        return entity;
    }
}
