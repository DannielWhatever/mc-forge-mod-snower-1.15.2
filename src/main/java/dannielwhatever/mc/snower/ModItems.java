package dannielwhatever.mc.snower;

import dannielwhatever.mc.snower.blocks.SnowerBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

public class ModItems {

    private static final Logger LOGGER = LogManager.getLogger();

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);

    public static SnowerBlock snowerBlock = new SnowerBlock();
    public static BlockItem snowerBlockItem = itemFrom(snowerBlock, Main.snowerItemGroup);

    static {
        BLOCKS.register("snower", supplier(snowerBlock));
        ITEMS.register("snower", supplier(snowerBlockItem));
    }

    private static BlockItem itemFrom(Block block, ItemGroup itemGroup) {
        LOGGER.info("safdasfassdasdasda>>>> saffsafs1 2 31 ");
        return new BlockItem(block, new Item.Properties().group(itemGroup));
    }

    private static <T extends IForgeRegistryEntry<T>> Supplier<? extends T> supplier(T entry) {
        return () -> entry;
    }


}
