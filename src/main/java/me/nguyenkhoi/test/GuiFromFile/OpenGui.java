package me.nguyenkhoi.test.GuiFromFile;

import me.nguyenkhoi.test.Manager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

import static me.nguyenkhoi.test.GuiFromFile.LoadItemsBlocks.LoadItemsBlock;
import static me.nguyenkhoi.test.GuiFromFile.LoadMenu.*;
import static me.nguyenkhoi.test.Test.plugin;

public class OpenGui {
    public static Inventory gui;
    public static void OpenGui(Player p) {
        LoadItemsBlock();
        gui = Bukkit.createInventory(null, 54, Manager.colorize(plugin.getConfig().getString("TITLE")));
        int d = 0;
        int i = 0;
        for (List<Integer> a : decorate_slot) {
            if (a == null) {
                decorate.remove(d);
            } else {
                for (int j = 0; j < a.size(); j++) {
                    gui.setItem(a.get(j), decorate.get(d));
                }
                d++;
            }
        }
        for (List<Integer> b : items_slot) {
            if (b == null) {
                items.remove(i);
            } else {
                for (int k = 0; k < b.size(); k++) {
                    gui.setItem(b.get(k), items.get(i));
                }
                i++;
            }
        }
        p.openInventory(gui);
    }
}
