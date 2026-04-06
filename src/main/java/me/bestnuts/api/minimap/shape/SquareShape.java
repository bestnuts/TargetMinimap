package me.bestnuts.api.minimap.shape;

import org.bukkit.Location;

public class SquareShape implements MinimapShape {

    @Override
    public boolean isRadiusIn(Location center, Location target, int radius) {
        return false;
    }
}
