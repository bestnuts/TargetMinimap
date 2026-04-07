package me.bestnuts.api;

import me.bestnuts.api.core.GlobalConfiguration;
import me.bestnuts.api.core.GlobalScheduler;
import me.bestnuts.api.listener.PlayerLifeCycle;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class TargetMinimap extends JavaPlugin {

    private static TargetMinimap instance;
    private GlobalConfiguration configuration;
    private GlobalScheduler scheduler;

    @Override
    public void onEnable() {
        instance = this;
        this.configuration = new GlobalConfiguration(this);
        this.scheduler = new GlobalScheduler(this);
        register();
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
        this.scheduler.disable();
        this.scheduler = null;
        this.configuration = null;
        instance = null;
    }

    private void register() {
        Bukkit.getPluginManager().registerEvents(new PlayerLifeCycle(this), this);
    }

    public static TargetMinimap getInstance() {
        return instance;
    }

    public GlobalConfiguration getConfiguration() {
        return configuration;
    }

    public GlobalScheduler getScheduler() {
        return scheduler;
    }
}
