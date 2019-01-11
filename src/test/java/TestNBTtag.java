import com.apex.library.util.NBTUtil;
import com.comphenix.protocol.utility.MinecraftReflection;
import net.minecraft.server.v1_12_R1.Items;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class TestNBTtag {
    @BeforeClass
    public static void before() {
        main.test.java.BukkitInitialization.initializeItemMeta();
    }

    @Test
    public void test() {
        net.minecraft.server.v1_12_R1.ItemStack item = new net.minecraft.server.v1_12_R1.ItemStack(Items.BOOK);
        ItemStack itemStack = MinecraftReflection.getBukkitItemStack(item);
        NBTUtil.setData(itemStack, "data", "dummy");
        YamlConfiguration yaml = new YamlConfiguration();
        yaml.set("item", itemStack);
        ItemStack itemStack1 = yaml.getItemStack("item");

        assertTrue(NBTUtil.hasCompound(itemStack, "data"));
    }
}
