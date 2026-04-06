package me.bestnuts.api.listener;

import me.bestnuts.api.TargetMinimap;
import me.bestnuts.api.core.GlobalScheduler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerLifeCycle implements Listener {

    private final GlobalScheduler scheduler;

    public PlayerLifeCycle(TargetMinimap plugin) {
        this.scheduler = plugin.getScheduler();
    }

    @EventHandler
    public void join(PlayerJoinEvent event) {
        scheduler.storePlayer(event.getPlayer());
    }
}
