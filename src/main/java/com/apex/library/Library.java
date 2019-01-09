package com.apex.library;

import com.apex.library.command.CommandLibAdmin;
import com.apex.library.gui.BookshelfGUI;
import com.apex.library.listeners.BlockListener;
import com.apex.library.listeners.PlayerListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class Library extends JavaPlugin {
    @Getter
    private static Library instance;
    private BookManager bookManager;

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public void onEnable() {
        registerEvents();

        getCommand("libadmin").setExecutor(new CommandLibAdmin());
    }


    public void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new BlockListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        Bukkit.getPluginManager().registerEvents(new BookshelfGUI(), this);
    }
}
