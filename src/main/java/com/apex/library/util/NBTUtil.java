package com.apex.library.util;

import com.comphenix.protocol.wrappers.nbt.NbtCompound;
import com.comphenix.protocol.wrappers.nbt.NbtFactory;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

public class NBTUtil {

    private static final String COMPOUND_KEY = "com.apex.library";

    public static boolean hasCompound(ItemStack itemStack, String key) {
        NbtCompound compound = NbtFactory.asCompound(NbtFactory.fromItemTag(itemStack));
        if (checkCompoundSet(compound)) {
            NbtCompound data = compound.getCompoundOrDefault(COMPOUND_KEY);
            return data.containsKey(key);
        }
        return false;
    }

    private static boolean checkCompoundSet(NbtCompound compound) {
        return compound.containsKey(COMPOUND_KEY);
    }

    public static void setData(ItemStack itemStack, String key, String value) {
        NbtCompound compound = NbtFactory.asCompound(NbtFactory.fromItemTag(itemStack));
        NbtCompound data = compound.getCompoundOrDefault(COMPOUND_KEY);
        data.put(key, value);
    }

    public static String getData(ItemStack itemStack, String key) {
        if (!hasCompound(itemStack, key))
            return null;

        NbtCompound compound = NbtFactory.asCompound(NbtFactory.fromItemTag(itemStack));
        NbtCompound data = compound.getCompoundOrDefault(COMPOUND_KEY);

        return data.getString(key);
    }
}
