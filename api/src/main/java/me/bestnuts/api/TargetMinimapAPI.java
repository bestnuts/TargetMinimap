package me.bestnuts.api;

import me.bestnuts.api.core.GlobalScheduler;
import me.bestnuts.api.minimap.MinimapTarget;
import me.bestnuts.api.minimap.target.BlockTarget;
import me.bestnuts.api.minimap.target.EntityTarget;
import me.bestnuts.api.minimap.target.Target;
import me.bestnuts.api.player.TMPlayer;
import net.kyori.adventure.text.Component;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class TargetMinimapAPI {

    public static MinimapTarget createTarget(Component icon, Block block) {
        Target target = new BlockTarget(block);
        return createMinimapTarget(target, icon);
    }

    public static MinimapTarget createTarget(Component icon, Entity entity) {
        Target target = new EntityTarget(entity);
        return createMinimapTarget(target, icon);
    }

    private static MinimapTarget createMinimapTarget(Target target, Component icon) {
        return new MinimapTarget(target, () -> icon);
    }

    public static void addTarget(Player player, MinimapTarget... targets) {
        GlobalScheduler scheduler = TargetMinimap.getInstance().getScheduler();
        TMPlayer tmPlayer = scheduler.getTMPlayer(player.getUniqueId());
        for (MinimapTarget target : targets) {
            tmPlayer.getMinimap().getTargets().add(target);
        }
    }
}
