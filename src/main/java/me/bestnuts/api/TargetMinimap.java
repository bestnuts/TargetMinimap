package me.bestnuts.api;

import me.bestnuts.api.core.GlobalConfiguration;
import me.bestnuts.api.core.GlobalScheduler;
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
    }

    @Override
    public void onDisable() {
        this.scheduler.disable();
        this.scheduler = null;
        this.configuration = null;
        instance = null;
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
