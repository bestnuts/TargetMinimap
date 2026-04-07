package me.bestnuts.api.minimap.shape;

public class CircleShape implements MinimapShape {

    @Override
    public boolean isInBounds(double rx, double ry, int radius) {
        return rx * rx + ry * ry <= (double) radius * radius;
    }
}
