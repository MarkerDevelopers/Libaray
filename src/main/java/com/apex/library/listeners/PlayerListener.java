package com.apex.library.listeners;

import com.apex.library.Library;
import com.apex.library.datatypes.Bookshelf;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;

public class PlayerListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onPlayerMove(PlayerMoveEvent e) {
        //TODO region api integration
    }

    @EventHandler
    public void onInventoryInteract(InventoryInteractEvent e) {

    }

    @EventHandler
    public void onPlayerBlockInteract(PlayerInteractEvent e) {
        Block b = e.getClickedBlock();
        if (b.getType() != Material.BOOKSHELF || e.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        HashMap<Location, Bookshelf> bookshelfes = Library.getInstance().getBookManager().getBookshelfes();

        if (bookshelfes.containsKey(b.getLocation())) {
            bookshelfes.get(b.getLocation()).openGUI(e.getPlayer());
        }
    }
}
