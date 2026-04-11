package me.bestnuts.api.minimap;

import me.bestnuts.api.core.GlobalConfiguration;
import me.bestnuts.api.minimap.shape.MinimapShape;
import me.bestnuts.api.minimap.text.MinimapSeparatorBuilder;
import me.bestnuts.api.minimap.text.MinimapTextBuilder;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;

import java.util.Iterator;

public class MinimapRenderer {

    public record CalculateBox(double yaw, double cosYaw, double sinYaw) {
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
        CalculateBox box = new CalculateBox(center.getYaw(), cosYaw, sinYaw);
        Iterator<MinimapTarget> iterator = minimap.getTargets().iterator();
        while (iterator.hasNext()) {
            MinimapTarget target = iterator.next();
            if (!target.getTarget().isValid()) {
                iterator.remove();
                continue;
            }
            MinimapTextBuilder builder = render(renderer, center, target, box);
            if (builder == null) continue;
            MinimapSeparatorBuilder separator = target.getComponent().separator();
            component = component.append(builder.build(separator));
        }
        return component;
    }

    public MinimapTextBuilder render(GlobalConfiguration.Renderer renderer, Location center, MinimapTarget target, CalculateBox box) {
        MinimapShape shape = renderer.shape().getShape();
        int radius = renderer.radius();
        Location targetLocation = target.getLocation();

        int dx = targetLocation.getBlockX() - center.getBlockX();
        int dy = targetLocation.getBlockZ() - center.getBlockZ();

        double rx = -(dx * box.cosYaw + dy * box.sinYaw);
        double ry = dy * box.cosYaw - dx * box.sinYaw;

        MinimapTargetComponent comp = target.getComponent();
        MinimapTextBuilder builder = comp.builder(target);
        Coordinate coordinate = comp.getCoordinate();
        double thetaOffset = comp.getThetaOffset();

        int alignRadius = comp.getAlignRadius(radius);

        if (!shape.isInBounds(rx, ry, alignRadius)) {
            if (renderer.outAlign()) {
                double[] aligned = shape.outOfBoundAlign(rx, ry, alignRadius);
                double[] resolved = coordinate.resolve(aligned[0], aligned[1], box);
                return coordinate.apply(builder, resolved[0], resolved[1], radius, box, thetaOffset);
            }
            return null;
        }

        double[] resolved = coordinate.resolve(rx, ry, box);
        return coordinate.apply(builder, resolved[0], resolved[1], radius, box, thetaOffset);
    }
}