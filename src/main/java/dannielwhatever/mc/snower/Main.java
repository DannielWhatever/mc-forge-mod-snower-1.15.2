package dannielwhatever.mc.snower;

import dannielwhatever.mc.snower.itemgroups.SnowerItemGroup;
import dannielwhatever.mc.snower.mods.MobsSuperChetados;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Main.MODID)
public class Main
{
    public static final String MODID = "snower";

    private static final Logger LOGGER = LogManager.getLogger();

    public static ItemGroup snowerItemGroup = new SnowerItemGroup("tab_snower");

    public Main() {
        LOGGER.info("initializing bus 1 ");
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);

        LOGGER.info("initializing bus 2 ");
        MinecraftForge.EVENT_BUS.register(new MobsSuperChetados());
    }

}
