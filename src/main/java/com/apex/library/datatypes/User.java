package com.apex.library.datatypes;

import com.apex.library.BookManager;
import com.apex.library.Library;
import com.apex.library.util.NBTUtil;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

@Getter
public class User {
    UUID uuid;
    List<Book> bookList;

    public void removeReadBook(ItemStack itemStack){
        Player p = Bukkit.getPlayer(uuid);
        if (NBTUtil.hasCompound(itemStack, "bookUUID")) {
            String s = NBTUtil.getData(itemStack, "bookUUID");

            UUID uuid = UUID.fromString(s);

            BookManager bmg = Library.getInstance().getBookManager();
            Book book = bmg.getBooks().get(uuid);

            if (book.getLentInfo().getType() == LentType.READ){
                p.getInventory().remove(itemStack);
            }
        }
    }

    public void removeReadBooks() {
        Player p = Bukkit.getPlayer(uuid);
        p.getInventory().forEach(itemStack -> {
            removeReadBook(itemStack);
        });
    }

}
