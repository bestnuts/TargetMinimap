package me.bestnuts.api.core;

import me.bestnuts.api.TargetMinimap;
import me.bestnuts.api.player.TMPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GlobalScheduler {

    private final Map<UUID, TMPlayer> players;
    private final BukkitTask tickTask;

    public GlobalScheduler(TargetMinimap plugin) {
        this.players = new HashMap<>();
        this.tickTask = Bukkit.getScheduler().runTaskTimer(plugin, this::runTick, 1L, 1L);
    }

    private void runTick() {
        for (TMPlayer player : players.values()) {
            if (!player.getPlayer().isOnline()) {
                removePlayer(player.getUuid());
                continue;
            }
        }
    }

    public void storePlayer(Player player) {
        players.put(player.getUniqueId(), new TMPlayer(player));
    }

    public TMPlayer getTMPlayer(UUID uuid) {
        return players.get(uuid);
    }

    private TMPlayer removePlayer(UUID uuid) {
        return players.remove(uuid);
    }
}
