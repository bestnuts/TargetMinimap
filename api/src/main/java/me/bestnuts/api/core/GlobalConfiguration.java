package me.bestnuts.api.core;

import me.bestnuts.api.TargetMinimap;
import me.bestnuts.api.minimap.shape.Shape;
import net.kyori.adventure.text.Component;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class GlobalConfiguration extends YamlConfiguration {

    private final File file;
    private Renderer renderer;
    private Output output;

    public GlobalConfiguration(TargetMinimap plugin) {
        this.file = new File(plugin.getDataFolder(), "config.yml");
        load();

        renderer();
        output();
    }

    private void load() {
        if (!file.exists()) {
            saveConfig();
        }
        try {
            super.load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveConfig() {
        try {
            super.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void renderer() {
        String shape = this.getString("render.shape", "Circle");
        this.renderer = new Renderer(
                this.getInt("render.radius", 128),
                Shape.valueOf(shape),
                this.getBoolean("render.out-align", false)
        );
    }

    private void output() {
        Component separator = Component.text(this.getString("output.separator", "\uF800"));
        this.output = new Output(
                this.getString("output.key", "minecraft"),
                this.getString("output.path", "radar"),
                separator
        );
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public Output getOutput() {
        return output;
    }

    public record Renderer(int radius, Shape shape, boolean outAlign) {
    }

    public record Output(String key, String path, Component separator) {
    }
}
