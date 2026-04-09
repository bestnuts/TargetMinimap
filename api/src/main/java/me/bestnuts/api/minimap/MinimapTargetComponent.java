package me.bestnuts.api.minimap;

import me.bestnuts.api.minimap.text.MinimapSeparatorBuilder;
import me.bestnuts.api.minimap.text.MinimapTextBuilder;

public class MinimapTargetComponent {

    private int imageWidth = 128;
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

    public MinimapSeparatorBuilder separator() {
        return MinimapSeparatorBuilder.of(imageWidth, xOffset);
    }

    public MinimapTextBuilder builder(MinimapTarget target) {
        return MinimapTextBuilder.create().icon(target.getIcon()).scale(scaleModifier);
    }
}
