package me.nguyenkhoi.test;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

import static me.nguyenkhoi.test.GuiFromFile.LoadDecorate.*;
import static me.nguyenkhoi.test.GuiFromFile.LoadMenu.*;
import static me.nguyenkhoi.test.Manager.colorize;
import static me.nguyenkhoi.test.Test.plugin;


public class Command implements CommandExecutor {
    public static Inventory gui;
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("items")) {
            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                ReloadMenu();
                plugin.reloadConfig();
                plugin.saveConfig();
                plugin.saveDefaultConfig();
            }
            LoadDecorate();
            Player p = (Player) sender;
            gui = Bukkit.createInventory(null, 54, colorize(plugin.getConfig().getString("TITLE")));
            int d = 0;
            int t = 0;
            for (List<Integer> a : decorate_slot) {
                if (a == null) {
                    decorate.remove(t);
                } else {
                    t++;
                    for (int j = 0; j < a.size(); j++) {
                        gui.setItem(a.get(j), decorate.get(d));
                    }
                    d++;
                }
            }
            p.openInventory(gui);
        }
        return true;
    }
}
