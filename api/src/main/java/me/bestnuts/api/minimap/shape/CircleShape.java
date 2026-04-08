package me.bestnuts.api.minimap.shape;

import me.bestnuts.api.minimap.text.MinimapTextBuilder;
import net.kyori.adventure.text.Component;

public class CircleShape implements MinimapShape {

    @Override
    public boolean isInBounds(double rx, double ry, int radius) {
        return rx * rx + ry * ry <= (double) radius * radius;
    }

    @Override
    public Component outOfBoundAlign(double rx, double ry, int radius, MinimapTextBuilder builder) {
        double dist = Math.sqrt(rx * rx + ry * ry);
        int x = (int) (128 * rx / dist) + 128;
        int y = (int) (128 * ry / dist) + 128;
        return builder.x(x).y(y).build();
    }
}
