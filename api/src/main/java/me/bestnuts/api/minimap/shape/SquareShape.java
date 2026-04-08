package me.bestnuts.api.minimap.shape;

import me.bestnuts.api.minimap.MinimapRenderer;
import me.bestnuts.api.minimap.text.MinimapTextBuilder;
import net.kyori.adventure.text.Component;

public class SquareShape implements MinimapShape {

    @Override
    public boolean isInBounds(double rx, double ry, int radius) {
        return Math.abs(rx) <= radius && Math.abs(ry) <= radius;
    }

    @Override
    public Component outOfBoundAlign(double rx, double ry, int radius, MinimapRenderer.CalculateBox box, MinimapTextBuilder builder) {
        int x = Math.max(-radius, Math.min(radius, (int) rx));
        int y = Math.max(-radius, Math.min(radius, (int) ry));
        return builder.x(x).y(y).build();
    }
}
