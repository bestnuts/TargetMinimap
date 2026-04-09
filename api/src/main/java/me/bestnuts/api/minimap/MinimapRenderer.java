package me.bestnuts.api.minimap;

import me.bestnuts.api.core.GlobalConfiguration;
import me.bestnuts.api.minimap.shape.MinimapShape;
import me.bestnuts.api.minimap.text.MinimapTextBuilder;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;

import java.util.Iterator;

public class MinimapRenderer {

    public record CalculateBox(double cosYaw, double sinYaw) {
    }

    private final Minimap minimap;

    public MinimapRenderer(Minimap minimap) {
        this.minimap = minimap;
    }

    public Component render(GlobalConfiguration.Renderer renderer, GlobalConfiguration.Output output, Location center) {
        Component component = Component.empty().font(Key.key(output.key(), output.path()));
        double yaw = Math.toRadians(center.getYaw());
        double cosYaw = Math.cos(yaw);
        double sinYaw = Math.sin(yaw);
        CalculateBox box = new CalculateBox(cosYaw, sinYaw);
        Iterator<MinimapTarget> iterator = minimap.getTargets().iterator();
        while (iterator.hasNext()) {
            MinimapTarget target = iterator.next();
            if (!target.getTarget().isValid()) {
                iterator.remove();
                continue;
            }
            Component check = render(renderer, center, target, box);
            if (check.equals(Component.empty())) continue;
            component = component.append(check).append(
                output.separator()
            );
        }
        return component;
    }

    public Component render(GlobalConfiguration.Renderer renderer, Location center, MinimapTarget target, CalculateBox box) {
        MinimapShape shape = renderer.shape().getShape();
        int radius = renderer.radius();
        Location targetLocation = target.getLocation();

        int dx = targetLocation.getBlockX() - center.getBlockX();
        int dy = targetLocation.getBlockZ() - center.getBlockZ();

        double rx = -(dx * box.cosYaw + dy * box.sinYaw);
        double ry = dy * box.cosYaw - dx * box.sinYaw;

        MinimapTextBuilder builder = target.getComponent().builder(target);

        if (!shape.isInBounds(rx, ry, radius)) {
            if (renderer.outAlign()) {
                return shape.outOfBoundAlign(rx, ry, radius, builder);
            }
            return Component.empty();
        }

        int x = (int) ((128 * rx) / radius) + 128;
        int y = (int) ((128 * ry) / radius) + 128;

        return builder
                .x(x)
                .y(y)
                .build();
    }
}
