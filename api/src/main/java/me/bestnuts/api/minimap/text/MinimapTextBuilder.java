package me.bestnuts.api.minimap.text;

import me.bestnuts.api.minimap.target.icon.TargetIcon;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.ShadowColor;
import net.kyori.adventure.text.format.TextColor;

public class MinimapTextBuilder {

    private Component icon = Component.empty();
    private int x = 0;
    private int y = 0;
    private int scale = 255;

    private MinimapTextBuilder() {
    }

    public static MinimapTextBuilder create() {
        return new MinimapTextBuilder();
    }

    public MinimapTextBuilder icon(Component icon) {
        this.icon = icon;
        return this;
    }

    public MinimapTextBuilder icon(TargetIcon icon) {
        this.icon = icon.getIcon();
        return this;
    }

    public MinimapTextBuilder x(int x) {
        this.x = x;
        return this;
    }

    public MinimapTextBuilder y(int y) {
        this.y = y;
        return this;
    }

    public MinimapTextBuilder scale(int scale) {
        this.scale = scale;
        return this;
    }

    public Component build() {
        return icon.color(TextColor.color(x, y, scale)).shadowColor(ShadowColor.shadowColor(0));
    }
}
