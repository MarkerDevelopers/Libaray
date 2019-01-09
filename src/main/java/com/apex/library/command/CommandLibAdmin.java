package com.apex.library.command;

import com.apex.library.Library;
import com.apex.library.datatypes.Book;
import com.apex.library.datatypes.Bookshelf;
import com.apex.library.util.NBTUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Set;
import java.util.UUID;

public class CommandLibAdmin implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) {
            return true;
        }

        Player p = (Player) commandSender;

        if (args.length >= 1) {
            if (args[0].equalsIgnoreCase("removebook")) {
                return true;
            }
            if (args.length >= 2) {
                if (args[0].equalsIgnoreCase("addshelf")) {
                    Block b = p.getTargetBlock((Set<Material>) null, 100);
                    Location loc = b.getLocation();

                    if (b.getType() != Material.BOOKSHELF) {
                        p.sendMessage("책장을 보고 이 명령어를 사용하세요");
                        return true;
                    }
                    if (Library.getInstance().getBookManager().getBookshelfes().containsKey(loc)) {
                        p.sendMessage("이미 등록되어 있는 책장입니다");
                        return true;
                    }
                    boolean bool = Boolean.valueOf(args[1]);

                    Bookshelf bookshelf = new Bookshelf(loc, bool);

                    Library.getInstance().getBookManager().getBookshelfes().put(loc, bookshelf);
                    return true;
                } else if (args[0].equalsIgnoreCase("addbook")) {
                    ItemStack item = p.getInventory().getItemInMainHand();

                    if (NBTUtil.hasCompound(item, "uuid")) {
                        //TODO message
                        return true;
                    }

                    UUID uuid = UUID.randomUUID();
                    int price = Integer.parseInt(args[1]);

                    NBTUtil.setData(item, "uuid", uuid.toString());

                    Book book = new Book(uuid, price, item);

                    Library.getInstance().getBookManager().getBooks().put(uuid, book);

                }
            }
        }
        return false;
    }
}
