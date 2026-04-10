package me.bestnuts.test;

import me.bestnuts.api.TargetMinimap;
import me.bestnuts.api.TargetMinimapAPI;
import me.bestnuts.api.minimap.Coordinate;
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
import org.bukkit.util.Vector;

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

        int radius = TargetMinimap.getInstance().getConfiguration().getRenderer().radius() / 2;
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

        MinimapTarget playerTarget = TargetMinimapAPI.createTarget(
                Component.text("\uE001"),
                player
        );

        backgroundIndex(index_0, -180, -90);
        backgroundIndex(index_1, -180, 0);
        backgroundIndex(index_2, -180, -180);
        backgroundIndex(index_3, -180, 90);

        TargetMinimapAPI.addTarget(player, centerTarget, index_0, index_1, index_2, index_3, playerTarget);
    }

    private void backgroundIndex(MinimapTarget index, int offset, double theta) {
        index.getComponent().offset(offset).thetaOffset(theta).coordinate(Coordinate.Polar);
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