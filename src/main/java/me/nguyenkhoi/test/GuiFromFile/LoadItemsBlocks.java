package me.nguyenkhoi.test.GuiFromFile;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Set;

import static me.nguyenkhoi.test.GuiFromFile.LoadMenu.items;
import static me.nguyenkhoi.test.GuiFromFile.LoadMenu.items_slot;
import static me.nguyenkhoi.test.Manager.colorize;
import static me.nguyenkhoi.test.Manager.lorecolor;
import static me.nguyenkhoi.test.Test.plugin;

public class LoadItemsBlocks {
    public static void LoadItemsBlock() {
        Set<String> iitems = plugin.getConfig().getConfigurationSection("ITEMS").getKeys(false);
        for (String key : iitems) {
            ItemStack item;
            Set<String> type = plugin.getConfig().getConfigurationSection("ITEMS." + key + ".").getKeys(false);
            Set<String> properties = plugin.getConfig().getConfigurationSection("ITEMS." + key + ".Blocks.").getKeys(false);
            if (properties.contains("material")) {
                int d = plugin.getConfig().getInt("ITEMS." + key + ".Blocks.data");
                short data = (short) d;
                if (properties.contains("amount")) {
                    if (properties.contains("data")) {
                        item = new ItemStack(Material.matchMaterial(plugin.getConfig().getString("ITEMS." + key + ".Blocks.material")), plugin.getConfig().getInt("ITEMS." + key + ".Blocks.amount"), data);
                    } else {
                        item = new ItemStack(Material.matchMaterial(plugin.getConfig().getString("ITEMS." + key + ".Blocks.material")), plugin.getConfig().getInt("ITEMS." + key + ".Blocks.amount"));
                    }
                } else {
                    if (properties.contains("data")) {
                        item = new ItemStack(Material.matchMaterial(plugin.getConfig().getString("ITEMS." + key + ".Blocks.material")), 1, data);
                    } else {
                        item = new ItemStack(Material.matchMaterial(plugin.getConfig().getString("ITEMS." + key + ".Blocks.material")), 1);
                    }
                }
                ItemMeta meta = item.getItemMeta();
                if (properties.contains("name")) {
                    meta.setDisplayName(colorize(plugin.getConfig().getString("ITEMS." + key + ".Blocks.name")));
                    if (properties.contains("lore")) {
                        List<String> lore = lorecolor(plugin.getConfig().getStringList("ITEMS." + key + ".Blocks.lore"));
                        meta.setLore(lore);
                        item.setItemMeta(meta);
                    }
                    else {
                        item.setItemMeta(meta);
                    }
                }
            } else {
                item = new ItemStack(Material.AIR);
                Bukkit.getLogger().warning("The item (" + key + ") do not have material propertie");
            }
            items.add(item);
            if (type.contains("Slot")) {
                items_slot.add(plugin.getConfig().getIntegerList("ITEMS." + key + ".Slot"));
            } else {
                Bukkit.getLogger().warning("The item (" + key + ") do not have slot propertie");
            }
        }
    }
}
