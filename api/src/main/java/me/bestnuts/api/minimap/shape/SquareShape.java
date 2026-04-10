package me.bestnuts.api.minimap.shape;

public class SquareShape implements MinimapShape {

    @Override
    public boolean isInBounds(double rx, double ry, int radius) {
        return Math.abs(rx) <= radius && Math.abs(ry) <= radius;
    }

    @Override
    public double[] outOfBoundAlign(double rx, double ry, int radius) {
        double scale = radius / Math.max(Math.abs(rx), Math.abs(ry));
        return new double[]{rx * scale, ry * scale};
    }
}