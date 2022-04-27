package me.nguyenkhoi.test.GuiFromFile;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static me.nguyenkhoi.test.Manager.colorize;
import static me.nguyenkhoi.test.Test.plugin;

public class LoadMenu {
    public static List<ItemStack> decorate = new ArrayList<ItemStack>();
    public static List<List<Integer>> decorate_slot = new ArrayList<List<Integer>>();
    public static String tittle;
    public static Integer rows;
    public static void  LoadMenu() {
        tittle = colorize(plugin.getConfig().getString("TITLE"));
        rows = plugin.getConfig().getInt("ROWS");
        new LoadDecorate();
    }
    public static void ReloadMenu () {
        decorate = new ArrayList<ItemStack>();
        decorate_slot = new ArrayList<List<Integer>>();
    }
}
