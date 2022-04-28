package me.nguyenkhoi.test;

import me.nguyenkhoi.test.GuiFromFile.LoadDecorate;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

import static me.nguyenkhoi.test.GuiFromFile.LoadItemsBlocks.*;
import static me.nguyenkhoi.test.GuiFromFile.LoadDecorate.*;
import static me.nguyenkhoi.test.GuiFromFile.LoadMenu.*;
import static me.nguyenkhoi.test.GuiFromFile.OpenGui.OpenGui;
import static me.nguyenkhoi.test.Test.plugin;


public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("items")) {
            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                ReloadMenu();
                plugin.reloadConfig();
                plugin.saveConfig();
                plugin.saveDefaultConfig();
            }
            LoadMenu();
            LoadDecorate();
            LoadItemsBlock();
            Player p = (Player) sender;
            OpenGui(p);
        }
        return true;
    }
}
