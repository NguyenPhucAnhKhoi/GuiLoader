package me.nguyenkhoi.test;

import me.nguyenkhoi.test.GuiFromFile.GuiClick;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Test extends JavaPlugin {

    @Override
    public void onEnable() {
        plugin = this;
        this.saveDefaultConfig();
        this.getCommand("counter").setExecutor(new Command());
        this.getCommand("items").setExecutor(new Command());
        Bukkit.getPluginManager().registerEvents(new InventoryClick(), this);
        Bukkit.getPluginManager().registerEvents(new GuiClick(), this);
    }
    @Override
    public void onDisable() {
    }
    public static JavaPlugin plugin;
}
