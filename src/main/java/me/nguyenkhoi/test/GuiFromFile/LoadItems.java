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

public  class LoadItems {
    public static String tittle;
    public static Integer rows;
    public static List<ItemStack> decorate = new ArrayList<ItemStack>();
    public static List<List<Integer>> slot = new ArrayList<List<Integer>>();
    public static void load() {
       tittle = colorize(plugin.getConfig().getString("TITLE"));
       rows = plugin.getConfig().getInt("ROWS");
       Set<String> ditems = plugin.getConfig().getConfigurationSection("DECORATES.").getKeys(false);
       for (String key : ditems) {
           int i = 0;
           ItemStack item;
           Set<String> properties = plugin.getConfig().getConfigurationSection("DECORATES." + key + ".").getKeys(false);
           if (properties.contains("material")) {
               if (properties.contains("amount")) {
                   if (properties.contains("data")) {
                       int d = plugin.getConfig().getInt("DECORATES." + key + ".data");
                       short data = (short) d;
                       item = new ItemStack(Material.matchMaterial(plugin.getConfig().getString("DECORATES." + key + ".material")), plugin.getConfig().getInt("DECORATES." + key + ".amount"), data);
                   } else {
                       item = new ItemStack(Material.matchMaterial(plugin.getConfig().getString("DECORATES." + key + ".material")), plugin.getConfig().getInt("DECORATES." + key + ".amount"));
                   }
               } else {
                   if (properties.contains("data")) {
                       item = new ItemStack(Material.matchMaterial(plugin.getConfig().getString("DECORATES." + key + ".material")), 1, (Short) plugin.getConfig().get("DECORATES." + key + ".data"));
                   } else {
                       item = new ItemStack(Material.matchMaterial(plugin.getConfig().getString("DECORATES." + key + ".material")), 1);
                   }
               }
               ItemMeta meta = item.getItemMeta();
               if (properties.contains("name")) {
                   meta.setDisplayName(colorize(plugin.getConfig().getString("DECORATES." + key + ".name")));
                   if (properties.contains("lore")) {
                       List<String> lores = lorecolor(plugin.getConfig().getStringList("DECORATES." + key + ".lore"));
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
           decorate.add(item);
           if (properties.contains("slot")) {
               slot.add(plugin.getConfig().getIntegerList("DECORATES." + key + ".slot"));
           } else {
               Bukkit.getLogger().warning("The decorate item (" + key + ") do not have slot propertie");
           }
       }
    }
}
