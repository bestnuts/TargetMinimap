package me.bestnuts.test;

import me.bestnuts.api.TargetMinimap;
import me.bestnuts.api.TargetMinimapAPI;
import me.bestnuts.api.minimap.MinimapTarget;
import net.kyori.adventure.text.Component;
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
                Component.text("\uE000\uF800\uE001"),
                player
        );

        MinimapTarget spawnTarget = TargetMinimapAPI.createTarget(
                Component.text("\uE001"),
                player.getWorld().getSpawnLocation().getBlock()
        );

        TargetMinimapAPI.addTarget(player, centerTarget, spawnTarget);
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