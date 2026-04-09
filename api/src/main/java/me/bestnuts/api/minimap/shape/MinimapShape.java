package me.bestnuts.api.minimap.shape;

import me.bestnuts.api.minimap.text.MinimapTextBuilder;

public interface MinimapShape {
    boolean isInBounds(double rx, double ry, int radius);
    MinimapTextBuilder outOfBoundAlign(double rx, double ry, int radius, MinimapTextBuilder builder);
}