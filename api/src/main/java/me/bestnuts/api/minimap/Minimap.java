package me.bestnuts.api.minimap;

import java.util.ArrayList;
import java.util.List;

public class Minimap {

    private final List<MinimapTarget> targets;
    private final MinimapRenderer renderer;

    public Minimap() {
        this.targets = new ArrayList<>();
        this.renderer = new MinimapRenderer(this);
    }

    public List<MinimapTarget> getTargets() {
        return targets;
    }

    public MinimapRenderer getRenderer() {
        return renderer;
    }
}
