package me.bestnuts.api.minimap;

import me.bestnuts.api.minimap.target.Target;
import me.bestnuts.api.minimap.target.icon.TargetIcon;
import org.bukkit.Location;

public class MinimapTarget {

    private final Target target;
    private final TargetIcon icon;

    public MinimapTarget(Target target, TargetIcon icon) {
        this.target = target;
        this.icon = icon;
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
}
