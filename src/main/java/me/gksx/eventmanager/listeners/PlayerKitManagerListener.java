package me.gksx.eventmanager.listeners;

import me.gksx.eventmanager.EventManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class PlayerKitManagerListener implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        if (player.hasMetadata("OpenedMenu")) {
            e.setCancelled(true);
            if (e.getSlot() == 11) {
                // Create new kit
            }
            if (e.getSlot() == 13) {
                // Change active kit
            }
            if (e.getSlot() == 15) {
                // Delete kit
            }
        }

    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();
        if (player.hasMetadata("OpenedMenu"))
            player.removeMetadata("OpenedMenu", EventManager.getInstance());
    }
}
