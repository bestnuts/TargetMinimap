package me.bestnuts.api.minimap;

import me.bestnuts.api.minimap.text.MinimapTextBuilder;

public enum Coordinate {
    Orthogonal {
        @Override
        public double[] resolve(double rx, double ry, MinimapRenderer.CalculateBox box) {
            return new double[]{rx, ry};
        }

        @Override
        public MinimapTextBuilder apply(MinimapTextBuilder builder, int scale, double rx, double ry, int radius, MinimapRenderer.CalculateBox box, double thetaOffset) {
            int x = (int) ((128 * rx) / radius) + 128;
            int y = (int) ((128 * ry) / radius) + 128;
            return builder.r(x).g(y).b(scale);
        }
    },
    Polar {
        @Override
        public double[] resolve(double rx, double ry, MinimapRenderer.CalculateBox box) {
            return new double[]{rx, ry};
        }

        @Override
        public MinimapTextBuilder apply(MinimapTextBuilder builder, int scale, double rx, double ry, int radius, MinimapRenderer.CalculateBox box, double thetaOffset) {
            int x = (int) ((128 * rx) / radius) + 128;
            int y = (int) ((128 * ry) / radius) + 128;
            double theta = ((box.yaw() + thetaOffset + 180) % 360 + 360) % 360;
            int b = Math.clamp((int) (theta / 360.0 * 256), 0, 255);
            return builder.r(x).g(y).b(b);
        }
    };

    public abstract double[] resolve(double rx, double ry, MinimapRenderer.CalculateBox box);
    public abstract MinimapTextBuilder apply(MinimapTextBuilder builder, int scale, double rx, double ry, int radius, MinimapRenderer.CalculateBox box, double thetaOffset);
}