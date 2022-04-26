package me.nguyenkhoi.test;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Test extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("counter").setExecutor(new Command());
        Bukkit.getPluginManager().registerEvents(new InventoryClick(), this);
    }
    @Override
    public void onDisable() {
    }
}
