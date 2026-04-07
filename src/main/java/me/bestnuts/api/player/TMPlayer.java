package me.bestnuts.api.player;

import me.bestnuts.api.minimap.Minimap;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TMPlayer {

    private final Player player;
    private final UUID uuid;
    private final Minimap minimap;

    public TMPlayer(Player player) {
        this.player = player;
        this.uuid = player.getUniqueId();
        this.minimap = new Minimap();
    }

    public Location getLocation() {
        return player.getLocation().clone();
    }

    public Player getPlayer() {
        return player;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Minimap getMinimap() {
        return minimap;
    }
}
