package me.bestnuts.api.minimap.shape;

import me.bestnuts.api.minimap.MinimapRenderer;
import me.bestnuts.api.minimap.text.MinimapTextBuilder;
import net.kyori.adventure.text.Component;

public interface MinimapShape {
    boolean isInBounds(double rx, double ry, int radius);
    Component outOfBoundAlign(double rx, double ry, int radius, MinimapRenderer.CalculateBox box, MinimapTextBuilder builder);
}
