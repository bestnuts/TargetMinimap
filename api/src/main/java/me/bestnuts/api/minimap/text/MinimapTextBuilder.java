package me.bestnuts.api.minimap.text;

import me.bestnuts.api.minimap.target.icon.TargetIcon;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.ShadowColor;
import net.kyori.adventure.text.format.TextColor;

public class MinimapTextBuilder {

    private String iconText = "";
    private int r = 0;
    private int g = 0;
    private int b = 232; // 셰이더 매직 넘버 232 * 2 == 464

    private MinimapTextBuilder() {
    }

    public static MinimapTextBuilder create() {
        return new MinimapTextBuilder();
    }

    public MinimapTextBuilder icon(Component icon) {
        if (icon instanceof TextComponent text) {
            this.iconText = text.content();
        }
        return this;
    }

    public MinimapTextBuilder icon(TargetIcon icon) {
        return icon(icon.getIcon());
    }

    public MinimapTextBuilder r(int r) {
        this.r = r;
        return this;
    }

    public MinimapTextBuilder g(int g) {
        this.g = g;
        return this;
    }

    public MinimapTextBuilder b(int b) {
        this.b = b;
        return this;
    }

    public Component build() {
        return Component.text(iconText)
                .color(TextColor.color(r, g, b))
                .shadowColor(ShadowColor.shadowColor(0));
    }

    public Component build(MinimapSeparatorBuilder separator) {
        return Component.text(separator.wrap(iconText))
                .color(TextColor.color(r, g, b))
                .shadowColor(ShadowColor.shadowColor(0));
    }
}