package me.bestnuts.api.minimap.shape;

import me.bestnuts.api.minimap.MinimapRenderer;
import me.bestnuts.api.minimap.text.MinimapTextBuilder;
import net.kyori.adventure.text.Component;

public class CircleShape implements MinimapShape {

    @Override
    public boolean isInBounds(double rx, double ry, int radius) {
        return rx * rx + ry * ry <= (double) radius * radius;
    }

    @Override
    public Component outOfBoundAlign(double rx, double ry, int radius, MinimapRenderer.CalculateBox box, MinimapTextBuilder builder) {
        int x = (int) -(radius * box.cosYaw() + radius * box.sinYaw()) + 128;
        int y = (int) (radius * box.cosYaw() - radius * box.sinYaw()) + 128;
        return builder.x(x).y(y).build();
    }
}
