package me.bestnuts.api.minimap;

import me.bestnuts.api.minimap.text.MinimapTextBuilder;

public class MinimapTargetComponent {

    // TODO : separate nsf 2의 거듭제곱별 구성
    private int imageWidth = 256;
    // TODO : nsf 2진 구성
    private int xOffset = 0;
    private int scaleModifier = 232;

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

    public MinimapTargetComponent scale(int scale) {
        this.scaleModifier = scale;
        return this;
    }

    public MinimapTextBuilder builder(MinimapTarget target) {
        return MinimapTextBuilder.create().icon(target.getIcon()).scale(scaleModifier);
    }
}
