package me.bestnuts.api.minimap.shape;

import org.bukkit.Location;

public class CircleShape implements MinimapShape {

    @Override
    public boolean isRadiusIn(Location center, Location target, int radius) {
        return !(center.distance(target) > radius);
    }
}
