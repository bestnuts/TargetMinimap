package me.bestnuts.api.minimap.shape;

import org.bukkit.Location;

public class SquareShape implements MinimapShape {

    @Override
    public boolean isRadiusIn(Location center, Location target, int radius) {
        int centerX = center.getBlockX();
        int centerY = center.getBlockZ();

        int minX = centerX - radius;
        int minY = centerY - radius;
        int maxX = centerX + radius;
        int maxY = centerY + radius;

        int targetX = target.getBlockX();
        int targetY = target.getBlockY();

        return aabb(minX, minY, maxX, maxY, targetX, targetY);
    }

    private boolean aabb(int minX, int minY, int maxX, int maxY, int targetX, int targetY) {
        return targetX >= minX && targetX <= maxX &&
                targetY >= minY && targetY <= maxY;
    }
}
