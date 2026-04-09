package me.bestnuts.api.minimap;

import me.bestnuts.api.minimap.target.Target;
import me.bestnuts.api.minimap.target.icon.TargetIcon;
import org.bukkit.Location;

public class MinimapTarget {

    private final Target target;
    private final TargetIcon icon;
    private final MinimapTargetComponent component;

    public MinimapTarget(Target target, TargetIcon icon) {
        this.target = target;
        this.icon = icon;
        this.component = MinimapTargetComponent.create();
    }

    public Location getLocation() {
        return target.getLocation().clone();
    }

    public TargetIcon getIcon() {
        return icon;
    }

    public Target getTarget() {
        return target;
    }

    public MinimapTargetComponent getComponent() {
        return component;
    }
}
