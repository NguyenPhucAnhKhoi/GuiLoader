package me.nguyenkhoi.test;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static me.nguyenkhoi.test.Command.*;

public class InventoryClick implements Listener {
    @EventHandler
    public void onClickInv(InventoryClickEvent event) {
        if(!event.getInventory().equals(inv)) return;
        if(event.getCurrentItem() == null) return;
        event.setCancelled(true);
        Player p = (Player) event.getWhoClicked();
        if (event.getSlot() == 45 && event.getCurrentItem().equals(prevpage)) {
            page = page - 1;
            openGui(p, amount);
        }
        if (event.getSlot() == 53 && event.getCurrentItem().equals(nextpage)) {
            page = page + 1;
            openGui(p, amount);
        }
    }
}
