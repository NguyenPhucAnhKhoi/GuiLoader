package me.nguyenkhoi.test;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Command implements CommandExecutor {
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
        if (command.getName().equalsIgnoreCase("counter")) {
            if (args.length == 1) {
                Player p = (Player) sender;
                amount = Integer.parseInt(args[0]);
                page = 0;
                openGui(p, amount);
            }
        }
        return false;
    }
    public static void openGui(Player p, int amount) {
        pages = amount / 45;
        List<Integer> count = new ArrayList<Integer>();
        for(int i = 0; i < amount; i++) {
            count.add(i);
        }
        inv = Bukkit.createInventory(null, 54, "Counter " + (page + 1) + "/" + (pages + 1));
        ItemStack next = new ItemStack(Material.ARROW, 1);
        ItemMeta mnext = next.getItemMeta();
        mnext.setDisplayName("Next Page");
        next.setItemMeta(mnext);
        nextpage = next;
        ItemStack prev = new ItemStack(Material.ARROW, 1);
        ItemMeta mprev = prev.getItemMeta();
        mprev.setDisplayName("Previous Page");
        prev.setItemMeta(mprev);
        prevpage = prev;
        ItemStack out = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 4);
        ItemMeta mout = out.getItemMeta();
        mout.setDisplayName(" ");
        out.setItemMeta(mout);
        outline = out;
        inv.setItem(46, outline);
        inv.setItem(47, outline);
        inv.setItem(48, outline);
        inv.setItem(49, outline);
        inv.setItem(50, outline);
        inv.setItem(51, outline);
        inv.setItem(52, outline);
        if (page < pages) {
            inv.setItem(53, nextpage);
        } else {
            inv.setItem(53, outline);
        }
        if (page > 0) {
            inv.setItem(45, prevpage);
        } else {
            inv.setItem(45, outline);
        }
        for(int i = 0; i < 45; i++) {
            int index = 45 * page + i;
            if(index >= count.size()) break;
            if (count.get(index) != null){
                ItemStack im = new ItemStack(Material.WOOL, 1, (short) 5);
                ItemMeta meta = im.getItemMeta();
                meta.setDisplayName("§a" + (count.get(index) + 1));
                im.setItemMeta(meta);
                item = im;
                inv.setItem(i, item);
            }
        }
        p.openInventory(inv);
    }
}
