package me.bestnuts.api.minimap;

import me.bestnuts.api.core.GlobalConfiguration;
import me.bestnuts.api.minimap.shape.MinimapShape;
import me.bestnuts.api.minimap.text.MinimapTextBuilder;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;

public class MinimapRenderer {

    private final Minimap minimap;

    public MinimapRenderer(Minimap minimap) {
        this.minimap = minimap;
    }

    public Component render(GlobalConfiguration.Renderer renderer, Location center) {
        Component component = Component.empty();
        for (MinimapTarget target : minimap.getTargets()) {
            component = component.append(render(renderer, center, target));
        }
        return component;
    }

    public Component render(GlobalConfiguration.Renderer renderer, Location center, MinimapTarget target) {
        MinimapShape shape = renderer.shape().getShape();
        int radius = renderer.radius();
        Location targetLocation = target.getLocation();
        if (!shape.isRadiusIn(center, targetLocation, radius)) {
            return Component.empty();
        }

        int x = targetLocation.getBlockX() - center.getBlockX();
        int y = targetLocation.getBlockZ() - center.getBlockZ();

        return MinimapTextBuilder
                .create()
                .icon(target.getIcon())
                .x(x)
                .y(y)
                .build();
    }
}
