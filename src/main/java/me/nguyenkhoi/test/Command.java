package me.nguyenkhoi.test;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static me.nguyenkhoi.test.GuiFromFile.LoadItems.*;
import static me.nguyenkhoi.test.Manager.colorize;
import static me.nguyenkhoi.test.Test.plugin;


public class Command implements CommandExecutor {
    public static Inventory gui;
    public static Inventory inv;
    public static ItemStack nextpage;
    public static ItemStack prevpage;
    public static ItemStack outline;
    public static ItemStack item;
    public static int amount;
    public static int page;
    public static int pages;
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("items")) {
            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                plugin.reloadConfig();
                plugin.saveConfig();
                plugin.saveDefaultConfig();
            }
            load();
            Player p = (Player) sender;
            gui = Bukkit.createInventory(null, 54, colorize(plugin.getConfig().getString("TITLE")));
            int i = 0;
            for (List<Integer> a : slot) {
                for (int j = 0; j < a.size(); j++) {
                    if (a.equals(null)) return false;
                    else {
                        gui.setItem(a.get(j), decorate.get(i));
                    }
                }
                i++;
            }
            p.openInventory(gui);
        }
        return true;
    }
}
