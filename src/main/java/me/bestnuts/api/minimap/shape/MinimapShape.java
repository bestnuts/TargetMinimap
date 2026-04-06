package me.bestnuts.api.minimap.shape;

import org.bukkit.Location;

public interface MinimapShape {
    boolean isRadiusIn(Location center, Location target, int radius);
}
