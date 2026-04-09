package me.bestnuts.test;

import me.bestnuts.api.TargetMinimap;
import me.bestnuts.api.TargetMinimapAPI;
import me.bestnuts.api.minimap.MinimapTarget;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class TestPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        MinimapTarget centerTarget = TargetMinimapAPI.createTarget(
                Component.text("\uE000"),
                player
        );

        Location anchor = player.getLocation().clone();

        MinimapTarget index_0 = TargetMinimapAPI.createTarget(
                Component.text("\uE00A"),
                anchor
        );
        MinimapTarget index_1 = TargetMinimapAPI.createTarget(
                Component.text("\uE00B"),
                anchor
        );
        MinimapTarget index_2 = TargetMinimapAPI.createTarget(
                Component.text("\uE00C"),
                anchor
        );
        MinimapTarget index_3 = TargetMinimapAPI.createTarget(
                Component.text("\uE00D"),
                anchor
        );

        backgroundIndex(index_0, 64);
        backgroundIndex(index_1, -64);
        backgroundIndex(index_2, 64);
        backgroundIndex(index_3, -64);

        MinimapTarget playerTarget = TargetMinimapAPI.createTarget(
                Component.text("\uE001"),
                player
        );

        TargetMinimapAPI.addTarget(player, centerTarget, index_0, index_1, index_2, index_3, playerTarget);
    }

    private void backgroundIndex(MinimapTarget index, int offset) {
        index.getComponent().offset(offset);
    }

    @EventHandler
    public void mobSpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();
        MinimapTarget entityTarget = TargetMinimapAPI.createTarget(
                Component.text("\uE002"),
                entity
        );

        TargetMinimap.getInstance().getScheduler().getTMPlayers().forEach(
                player -> TargetMinimapAPI.addTarget(player.getPlayer(), entityTarget)
                );
    }
}