package me.nguyenkhoi.test.GuiFromFile;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static me.nguyenkhoi.test.Manager.colorize;
import static me.nguyenkhoi.test.Manager.lorecolor;
import static me.nguyenkhoi.test.Test.plugin;

public class LoadItemsBlocks {
    public static List<ItemStack> items = new ArrayList<ItemStack>();
    public static List<List<Integer>> items_slot = new ArrayList<List<Integer>>();
    public static void LoadItems() {
        Set<String> iitems = plugin.getConfig().getConfigurationSection("ITEMS").getKeys(false);
        for (String key : iitems) {
            ItemStack item;
            Set<String> properties = plugin.getConfig().getConfigurationSection("ITEMS" + key + ".Blocks.").getKeys(false);
            if (properties.contains("material")) {
                int d = plugin.getConfig().getInt("ITEMS" + key + ".Blocks.data");
                short data = (short) d;
                if (properties.contains("amount")) {
                    if (properties.contains("data")) {
                        item = new ItemStack(Material.matchMaterial(plugin.getConfig().getString("ITEMS" + key + ".Blocks.material")), plugin.getConfig().getInt("ITEMS" + key + ".amount"), data);
                    } else {
                        item = new ItemStack(Material.matchMaterial(plugin.getConfig().getString("ITEMS" + key + ".Blocks.material")), plugin.getConfig().getInt("ITEMS" + key + ".amount"));
                    }
                } else {
                    if (properties.contains("data")) {
                        item = new ItemStack(Material.matchMaterial(plugin.getConfig().getString("ITEMS" + key + ".Blocks.material")), plugin.getConfig().getInt("ITEMS" + key + ".amount"), data);
                    } else {
                        item = new ItemStack(Material.matchMaterial(plugin.getConfig().getString("ITEMS" + key + ".Blocks.material")), plugin.getConfig().getInt("ITEMS" + key + ".amount"));
                    }
                }
                ItemMeta meta = item.getItemMeta();
                if (properties.contains("name")) {
                    meta.setDisplayName(colorize(plugin.getConfig().getString("ITEMS" + key + ".name")));
                    if (properties.contains("lore")) {
                        List<String> lores = lorecolor(plugin.getConfig().getStringList("ITEMS" + key + ".lore"));
                        meta.setLore(lores);
                        item.setItemMeta(meta);
                    }
                    else {
                        item.setItemMeta(meta);
                    }
                }
            } else {
                item = new ItemStack(Material.AIR);
                Bukkit.getLogger().warning("The decorate item (" + key + ") do not have material propertie");
            }
            items.add(item);
            if (properties.contains("slot")) {
                items_slot.add(plugin.getConfig().getIntegerList("ITEMS" + key + ".slot"));
            } else {
                Bukkit.getLogger().warning("The decorate item (" + key + ") do not have slot propertie");
            }
        }
    }
}
