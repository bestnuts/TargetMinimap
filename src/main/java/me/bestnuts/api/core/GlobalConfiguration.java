package me.bestnuts.api.core;

import me.bestnuts.api.TargetMinimap;
import me.bestnuts.api.minimap.shape.Shape;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class GlobalConfiguration extends YamlConfiguration {

    private final File file;
    private Renderer renderer;

    public GlobalConfiguration(TargetMinimap plugin) {
        this.file = new File(plugin.getDataFolder(), "config.yml");
        load();

        renderer();
    }

    private void load() {
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
                Shape.valueOf(shape)
        );
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public static record Renderer(int radius, Shape shape) {
    }
}
