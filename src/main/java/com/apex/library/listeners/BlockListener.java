package com.apex.library.listeners;

import com.apex.library.Library;
import com.apex.library.datatypes.Bookshelf;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.HashMap;

public class BlockListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent e) {
        Location loc = e.getBlock().getLocation();
        HashMap<Location, Bookshelf> bookshelfes = Library.getInstance().getBookManager().getBookshelfes();
        if (bookshelfes.containsKey(loc)) {
            Bookshelf bookshelf = bookshelfes.get(loc);
            bookshelf.getBookList().forEach(b -> {
                Location pLoc = e.getPlayer().getLocation();
                pLoc.getWorld().dropItemNaturally(pLoc, b.getBookItem());
            });
            bookshelfes.remove(loc);
        }
    }
}
