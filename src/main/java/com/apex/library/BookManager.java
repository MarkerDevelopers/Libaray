package com.apex.library;

import com.apex.library.datatypes.Book;
import com.apex.library.datatypes.Bookshelf;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.HashMap;
import java.util.UUID;

@Getter
public class BookManager {

    private HashMap<UUID, Book> books;
    private HashMap<Location, Bookshelf> bookshelfes;

    public void loadBookshelf(String name) {

    }

    public void loadBook(String uuid) {
        YamlConfiguration
    }

}
