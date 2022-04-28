package me.nguyenkhoi.test.GuiFromFile;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Set;

import static me.nguyenkhoi.test.GuiFromFile.LoadMenu.decorate;
import static me.nguyenkhoi.test.GuiFromFile.LoadMenu.decorate_slot;
import static me.nguyenkhoi.test.Manager.colorize;
import static me.nguyenkhoi.test.Manager.lorecolor;
import static me.nguyenkhoi.test.Test.plugin;

public  class LoadDecorate {
    public static void LoadDecorate() {
       Set<String> ditems = plugin.getConfig().getConfigurationSection("DECORATES.").getKeys(false);
       for (String key : ditems) {
           ItemStack item;
           Set<String> properties = plugin.getConfig().getConfigurationSection("DECORATES." + key + ".").getKeys(false);
           if (properties.contains("material")) {
               int d = plugin.getConfig().getInt("DECORATES." + key + ".data");
               short data = (short) d;
               if (properties.contains("amount")) {
                   if (properties.contains("data")) {
                       item = new ItemStack(Material.matchMaterial(plugin.getConfig().getString("DECORATES." + key + ".material")), plugin.getConfig().getInt("DECORATES." + key + ".amount"), data);
                   } else {
                       item = new ItemStack(Material.matchMaterial(plugin.getConfig().getString("DECORATES." + key + ".material")), plugin.getConfig().getInt("DECORATES." + key + ".amount"));
                   }
               } else {
                   if (properties.contains("data")) {
                       item = new ItemStack(Material.matchMaterial(plugin.getConfig().getString("DECORATES." + key + ".material")), 1, data);
                   } else {
                       item = new ItemStack(Material.matchMaterial(plugin.getConfig().getString("DECORATES." + key + ".material")), 1);
                   }
               }
               ItemMeta meta = item.getItemMeta();
               if (properties.contains("name")) {
                   meta.setDisplayName(colorize(plugin.getConfig().getString("DECORATES." + key + ".name")));
                   item.setItemMeta(meta);
               }
               if (properties.contains("lore")) {
                   List<String> lores = lorecolor(plugin.getConfig().getStringList("DECORATES." + key + ".lore"));
                   meta.setLore(lores);
                   item.setItemMeta(meta);
               }
           } else {
               item = new ItemStack(Material.AIR);
               Bukkit.getLogger().warning("The decorate item (" + key + ") do not have material propertie");
           }
           decorate.add(item);
           if (properties.contains("slot")) {
               decorate_slot.add(plugin.getConfig().getIntegerList("DECORATES." + key + ".slot"));
           } else {
               decorate_slot.add(null);
               Bukkit.getLogger().warning("The decorate item (" + key + ") do not have slot propertie");
           }
       }
    }
}
