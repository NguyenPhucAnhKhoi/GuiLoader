package me.nguyenkhoi.test.GuiFromFile;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static me.nguyenkhoi.test.Command.*;
import static me.nguyenkhoi.test.GuiFromFile.OpenGui.gui;

public class GuiClick implements Listener {
    @EventHandler
    public void onClickInv(InventoryClickEvent event) {
        if(!event.getInventory().equals(gui)) return;
        event.setCancelled(true);
    }
}
