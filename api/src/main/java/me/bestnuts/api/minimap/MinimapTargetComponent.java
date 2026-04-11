package me.bestnuts.api.minimap;

import me.bestnuts.api.minimap.text.MinimapSeparatorBuilder;
import me.bestnuts.api.minimap.text.MinimapTextBuilder;

public class MinimapTargetComponent {

    private int imageWidth = 128;
    private int xOffset = 0;
    private double thetaOffset = 0;
    private int alignRadius = -1;
    private Coordinate coordinate = Coordinate.Orthogonal;

    private MinimapTargetComponent() {
    }

    public static MinimapTargetComponent create() {
        return new MinimapTargetComponent();
    }

    public MinimapTargetComponent width(int width) {
        this.imageWidth = width;
        return this;
    }

    public MinimapTargetComponent offset(int offset) {
        this.xOffset = offset;
        return this;
    }

    public MinimapTargetComponent thetaOffset(double thetaOffset) {
        this.thetaOffset = thetaOffset;
        return this;
    }

    public MinimapTargetComponent alignRadius(int alignRadius) {
        this.alignRadius = alignRadius;
        return this;
    }

    public MinimapTargetComponent coordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
        return this;
    }

    public int getAlignRadius(int fallback) {
        return alignRadius >= 0 ? alignRadius : fallback;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public double getThetaOffset() {
        return thetaOffset;
    }

    public MinimapSeparatorBuilder separator() {
        return MinimapSeparatorBuilder.of(imageWidth, xOffset);
    }

    public MinimapTextBuilder builder(MinimapTarget target) {
        return MinimapTextBuilder.create().icon(target.getIcon());
    }
}
