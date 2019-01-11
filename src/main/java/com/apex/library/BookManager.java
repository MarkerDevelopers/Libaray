package com.apex.library;

import com.apex.library.datatypes.Book;
import com.apex.library.datatypes.Bookshelf;
import com.apex.library.datatypes.LentInfo;
import com.apex.library.datatypes.LentType;
import com.apex.library.util.NBTUtil;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

@Getter
public class BookManager {

    private HashMap<UUID, Book> books;
    private HashMap<Location, Bookshelf> bookshelfes;
    private HashMap<UUID, Bookshelf> viewers;


    public void loadBookshelf(String name) {

    }

    public void loadBooks() {
        File dir = new File(Library.getInstance().getDataFolder().getPath() + File.separator + "books");

        if (dir.isDirectory() && dir.listFiles() != null) {
            for (File f : dir.listFiles()) {
                loadBook(f);
            }
        }
    }

    public void loadBook(File f) {
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(f);
        UUID uuid = UUID.fromString(yaml.getString("uuid"));
        int price = yaml.getInt("price");
        ItemStack item = yaml.getItemStack("item");
        Location loc = (Location) yaml.get("lastLocation");
        LentInfo lentInfo = (LentInfo) yaml.get("lentInfo");
        books.put(uuid, new Book(uuid, price, loc, item, lentInfo));
    }

    public void removeReadBook(Player p, ItemStack itemStack) {
        if (NBTUtil.hasCompound(itemStack, "bookUUID")) {
            String s = NBTUtil.getData(itemStack, "bookUUID");

            UUID uuid = UUID.fromString(s);

            BookManager bmg = Library.getInstance().getBookManager();
            Book book = bmg.getBooks().get(uuid);

            if (book.getLentInfo().getType() == LentType.READ) {
                p.getInventory().remove(itemStack);
            }
        }
    }

    public void removeReadBooks(Player p) {
        p.getInventory().forEach(itemStack -> {
            removeReadBook(p, itemStack);
        });
    }
}
