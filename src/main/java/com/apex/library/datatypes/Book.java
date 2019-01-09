package com.apex.library.datatypes;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

@Getter
public class Book {
    UUID uuid;
    int price;

    @Setter
    Location lastBookshelf;

    ItemStack bookItem;
    @Setter
    LentInfo lentInfo;


    public Book(UUID uuid, int price, ItemStack bookItem) {
        this.uuid = uuid;
        this.price = price;
        this.bookItem = bookItem;
    }

    public boolean isLent() {
        return lentInfo != null;
    }

    public void readBook(Player p) {
        lentInfo = new LentInfo(p.getUniqueId(), System.currentTimeMillis(), 0, LentType.READ);
    }

    public void lendBook(Player p, int expiredIn) { // expiredIn : day
        long convertExpiredIn = System.currentTimeMillis() + (expiredIn * 1000 * 24 * 60 * 60);
        lentInfo = new LentInfo(p.getUniqueId(), System.currentTimeMillis(), convertExpiredIn, LentType.LENT);
    }

    public void doReturn() {
        lentInfo = null;
    }

}
