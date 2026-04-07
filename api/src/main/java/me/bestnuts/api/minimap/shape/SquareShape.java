package me.bestnuts.api.minimap.shape;

public class SquareShape implements MinimapShape {

    @Override
    public boolean isInBounds(double rx, double ry, int radius) {
        return Math.abs(rx) <= radius && Math.abs(ry) <= radius;
    }
}
