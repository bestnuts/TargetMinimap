package me.bestnuts.api.minimap.shape;

public class CircleShape implements MinimapShape {

    @Override
    public boolean isInBounds(double rx, double ry, int radius) {
        return rx * rx + ry * ry <= (double) radius * radius;
    }

    @Override
    public double[] outOfBoundAlign(double rx, double ry, int radius) {
        double dist = Math.sqrt(rx * rx + ry * ry);
        return new double[]{rx * radius / dist, ry * radius / dist};
    }
}