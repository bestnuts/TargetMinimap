package me.bestnuts.api.minimap.target;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class BlockTarget implements Target {

    private final Block block;

    public BlockTarget(Block block) {
        this.block = block;
    }

    @Override
    public Location getLocation() {
        return block.getLocation();
    }

    @Override
    public boolean isValid() {
        return !block.isEmpty();
    }

    public Block getBlock() {
        return block;
    }
}
