package me.bestnuts.api.core;

import me.bestnuts.api.TargetMinimap;
import me.bestnuts.api.player.TMPlayer;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

public class GlobalScheduler {

    private final TargetMinimap plugin;
    private final Map<UUID, TMPlayer> players;
    private final BukkitTask tickTask;

    public GlobalScheduler(TargetMinimap plugin) {
        this.plugin = plugin;
        this.players = new HashMap<>();
        this.tickTask = Bukkit.getScheduler().runTaskTimer(plugin, this::runTick, 1L, 1L);
    }

    public void disable() {
        tickTask.cancel();
    }

    private void runTick() {
        GlobalConfiguration configuration = plugin.getConfiguration();
        for (TMPlayer player : players.values()) {
            if (!player.getPlayer().isOnline()) {
                removePlayer(player.getUuid());
                continue;
            }
            Component component = player.getMinimap().getRenderer().render(
                    configuration.getRenderer(), configuration.getOutput(), player.getLocation()
            );
            player.getPlayer().sendActionBar(component);
        }
    }

    public Collection<TMPlayer> getTMPlayers() {
        return players.values();
    }

    public void storePlayer(Player player) {
        players.put(player.getUniqueId(), new TMPlayer(player));
    }

    public TMPlayer getTMPlayer(UUID uuid) {
        return players.get(uuid);
    }

    private void removePlayer(UUID uuid) {
        players.remove(uuid);
    }
}
