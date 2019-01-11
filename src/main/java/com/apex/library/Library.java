package com.apex.library;

import com.apex.library.command.CommandLibAdmin;
import com.apex.library.exec.CheckBookRunnable;
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
        instance = this;

        bookManager = new BookManager();

        bookManager.loadBooks();

        registerEvents();

        getCommand("libadmin").setExecutor(new CommandLibAdmin());

        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new CheckBookRunnable(), 0L, 20L); //check every 1 sec
    }


    public void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new BlockListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        Bukkit.getPluginManager().registerEvents(new BookshelfGUI(), this);
    }
}
