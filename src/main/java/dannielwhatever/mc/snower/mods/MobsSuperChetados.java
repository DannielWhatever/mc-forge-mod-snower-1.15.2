package dannielwhatever.mc.snower.mods;

import dannielwhatever.mc.snower.ModItems;
import dannielwhatever.mc.snower.utils.Chat;
import dannielwhatever.mc.snower.utils.ItemGenerator;
import dannielwhatever.mc.snower.utils.Rand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.passive.horse.DonkeyEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.passive.horse.LlamaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MobsSuperChetados {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String GENERIC_MSG = "Oh no, pobre animalito :(";
    private static final String PIG_MSG = "Mataste a Porccini, cómo te atreves?!!";
    private static final String SHEEP_MSG = "Beee Beee, esa oveja no podrá saltar la valla uwu";
    private static final String CAT_MSG = "Miau Miau =^-^=";
    private static final String MONSTER_MSG = "¡Eso es! ¡A matar mobs!";
    private static final String DOLPHIN_MSG = "Es la hora DEL-FIN.";

    private static final String SLEEP_MSG = "Una siesta reparadora n_n";

    @SubscribeEvent
    public void sleepEvent(PlayerSleepInBedEvent event) {
        PlayerEntity player = event.getPlayer();
        Chat.sendMessage(player, SLEEP_MSG);

        Rand.reInitSeed();
        player.getEntityWorld().getGameRules().write();
    }

    @SubscribeEvent
    public void livingDeathEvent(LivingDeathEvent event){
        Entity entity = event.getEntity();
        LOGGER.info("entity is dead {}", entity.getClass().getName());

        if (!entity.world.isRemote) {

            if (entity instanceof SheepEntity) {
                Chat.sendMessage(event, SHEEP_MSG);

                dropItem(entity, ItemGenerator.bed());
            }

            if (entity instanceof PigEntity) {
                Chat.sendMessage(event, PIG_MSG);

                dropItem(entity, ItemGenerator.mineral(), 1 + Rand.twentyPercent());
                dropWeapon(entity, Rand.ninetyPercent());
                dropArmor(entity, Rand.fiftyPercent());
            }

            if (entity instanceof CowEntity || entity instanceof LlamaEntity || entity instanceof WolfEntity) {
                Chat.sendMessage(event, GENERIC_MSG);

                dropTool(entity, 1);
                dropArmor(entity, Rand.fiftyPercent());
            }

            if (entity instanceof HorseEntity || entity instanceof DonkeyEntity || entity instanceof ChickenEntity || entity instanceof BeeEntity || entity instanceof  RabbitEntity) {
                Chat.sendMessage(event, GENERIC_MSG);

                dropRandomItem(entity, 1);
                dropRandomItem(entity, Rand.twentyPercent());
            }

            if (entity instanceof CatEntity) {
                Chat.sendMessage(event, CAT_MSG);

                dropItem(entity, ModItems.snowerBlockItem);
                dropItem(entity, Items.END_PORTAL_FRAME);
            }

            if (entity instanceof MonsterEntity) {
                Chat.sendMessage(event, MONSTER_MSG);

                dropTool(entity, Rand.fiftyPercent());
                dropArmor(entity, Rand.fiftyPercent());
                dropItem(entity, Items.END_PORTAL_FRAME, Rand.twentyPercent());
                dropRandomItem(entity, Rand.ninetyPercent());

            }

            if (entity instanceof DolphinEntity) {
                Chat.sendMessage(event, DOLPHIN_MSG);

                triggerTheEnd(event);
            }

        }
    }

    private void dropItem(Entity entity, Item item) {
        dropItem(entity, item, 1);
    }

    private void dropItem(Entity entity, Item item, int cant) {
        if (cant < 1)
            return;
        entity.entityDropItem(item, cant);
    }

    private void dropRandomItem(Entity entity, int cant) {
        if (cant < 1)
            return;
        Item item;
        switch(Rand.rand5() ) {
            case 0:
            case 1:
                item = Items.ENDER_PEARL; break;
            case 2: item = Items.ENCHANTING_TABLE; break;
            default: item = Items.BLAZE_ROD; break;
        }
        dropItem(entity, item, cant);
    }

    private void dropArmor(Entity entity, int cant) {
        if (cant < 1)
            return;
        ItemStack armor;
        switch(Rand.rand5() ) {
            case 0: armor = ItemGenerator.elytra(); break;
            case 1: armor = ItemGenerator.helmet(); break;
            case 2: armor = ItemGenerator.chest(); break;
            case 3: armor = ItemGenerator.leggings(); break;
            default: armor = ItemGenerator.boots(); break;
        }
        entity.entityDropItem(armor, cant);
    }

    private void dropWeapon(Entity entity, int cant) {
        if (cant < 1)
            return;
        ItemStack weapon = Rand.rand5() > 2 ? ItemGenerator.axe() : ItemGenerator.sword();
        entity.entityDropItem(weapon, cant);
    }

    private void dropTool(Entity entity, int cant) {
        if (cant < 1)
            return;
        ItemStack tool = Rand.getOne(ItemGenerator.pickaxe(), ItemGenerator.bow(), ItemGenerator.shovel());
        entity.entityDropItem(tool, cant);
    }

    private void triggerTheEnd(LivingDeathEvent event) {
        Entity source = event.getSource().getTrueSource();
        LOGGER.info("dolphin 1 - A dolphin die");
        if (source instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) source;
            LOGGER.info("dolphin 2 - was killed by the player {}", player.getName());

            World world = player.getEntityWorld();
            MinecraftServer server = world.getServer();
            if (server != null) {
                LOGGER.info("dolphin 3 - server {}", server.getName());
                ServerWorld worldTheEnd = server.getWorld(DimensionType.THE_END);
                BlockPos pos = worldTheEnd.getSpawnCoordinate();
                ((ServerPlayerEntity) player).teleport(worldTheEnd, pos.getX(), pos.getY(), pos.getZ(), 0, 90);
                // player.setGameType(GameType.SPECTATOR);
            }
        }
    }


}
