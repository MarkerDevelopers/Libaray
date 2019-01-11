package com.apex.library.datatypes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Bookshelf {
    private Location loc;
    private Inventory inv;
    private List<Book> bookList;
    private boolean adminMode;

    public Bookshelf(Location loc, boolean adminMode) {
        this.loc = loc;
        this.inv = Bukkit.createInventory(null, 3, "책꽂이");
        this.adminMode = adminMode;
        this.bookList = new ArrayList<>();
    }

    public Bookshelf(Location loc, List<Book> bookList, boolean adminMode) {
        this.loc = loc;
        this.bookList = bookList;
        this.adminMode = adminMode;


    }

    public void openGUI(Player p) {
        p.openInventory(inv);
    }
}
