package me.bestnuts.api.minimap.shape;

import me.bestnuts.api.minimap.text.MinimapTextBuilder;
import net.kyori.adventure.text.Component;

public class SquareShape implements MinimapShape {

    @Override
    public boolean isInBounds(double rx, double ry, int radius) {
        return Math.abs(rx) <= radius && Math.abs(ry) <= radius;
    }

    @Override
    public Component outOfBoundAlign(double rx, double ry, int radius, MinimapTextBuilder builder) {
        double scale = radius / Math.max(Math.abs(rx), Math.abs(ry));
        int x = Math.clamp((int) (128 * rx * scale / radius) + 128, 0, 255);
        int y = Math.clamp((int) (128 * ry * scale / radius) + 128, 0, 255);
        return builder.x(x).y(y).build();
    }
}
