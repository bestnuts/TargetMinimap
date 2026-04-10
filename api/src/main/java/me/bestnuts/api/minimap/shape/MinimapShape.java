package me.bestnuts.api.minimap.shape;

public interface MinimapShape {
    boolean isInBounds(double rx, double ry, int radius);
    double[] outOfBoundAlign(double rx, double ry, int radius);
}