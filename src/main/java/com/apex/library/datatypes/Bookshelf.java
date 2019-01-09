package com.apex.library.datatypes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

@Getter
public class Bookshelf {
    private Location loc;
    private Inventory inv;
    private boolean adminMode;

    public Bookshelf(Location loc, boolean adminMode) {
        this.loc = loc;
        this.inv = Bukkit.createInventory(null, 3, "책꽂이");
        this.adminMode = adminMode;
    }

    public void openGUI(Player p){
        p.openInventory(inv);
    }
}
