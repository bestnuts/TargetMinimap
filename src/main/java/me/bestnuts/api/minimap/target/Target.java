package me.bestnuts.api.minimap.target;

import org.bukkit.Location;

public interface Target {
    Location getLocation();
    boolean isValid();
}
