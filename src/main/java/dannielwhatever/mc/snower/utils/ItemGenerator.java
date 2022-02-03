package dannielwhatever.mc.snower.utils;

import com.google.common.collect.ImmutableList;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Tuple;

import java.util.List;
import java.util.Random;

public final class ItemGenerator {

    static final int MAX_LEVEL = 50;

    public static Item bed() {
        Item item;
        switch(Rand.rand5() ) {
            case 0: item = Items.BLACK_BED; break;
            case 1: item = Items.BROWN_BED; break;
            case 2: item = Items.RED_BED; break;
            case 3: item = Items.GREEN_BED; break;
            default: item = Items.WHITE_BED; break;
        }
        return item;
    }

    public static Item mineral() {
        return Rand.getOne(Items.DIAMOND, Items.IRON_INGOT, Items.GOLD_INGOT);
    }

    public static ItemStack pickaxe(){
        Item item = Rand.getOne(Items.DIAMOND_PICKAXE, Items.IRON_PICKAXE, Items.GOLDEN_PICKAXE);
        List<Tuple<Enchantment, Integer>> enchantmentsList = ImmutableList.of(
                enchantmentProbability(Enchantments.UNBREAKING, 5),
                enchantmentProbability(Enchantments.EFFICIENCY, 4),
                enchantmentProbability(Enchantments.FIRE_ASPECT, 2),
                enchantmentProbability(Enchantments.FORTUNE, 2));
        return ItemGenerator.couldBeEnchanted(item, enchantmentsList);
    }

    public static ItemStack sword(){
        Item item = Rand.getOne(Items.DIAMOND_SWORD, Items.IRON_SWORD, Items.GOLDEN_SWORD);
        List<Tuple<Enchantment, Integer>> enchantmentsList = ImmutableList.of(
                enchantmentProbability(Enchantments.UNBREAKING, 5),
                enchantmentProbability(Enchantments.SHARPNESS, 4),
                enchantmentProbability(Enchantments.POWER, 2),
                enchantmentProbability(Enchantments.FIRE_ASPECT, 3),
                enchantmentProbability(Enchantments.LOOTING, 2),
                enchantmentProbability(Enchantments.FLAME, 1));
        return ItemGenerator.couldBeEnchanted(item, enchantmentsList);
    }

    public static ItemStack axe(){
        Item item = Rand.getOne(Items.DIAMOND_AXE, Items.IRON_AXE, Items.GOLDEN_AXE);
        List<Tuple<Enchantment, Integer>> enchantmentsList = ImmutableList.of(
                enchantmentProbability(Enchantments.UNBREAKING, 5),
                enchantmentProbability(Enchantments.POWER, 2),
                enchantmentProbability(Enchantments.FIRE_ASPECT, 3),
                enchantmentProbability(Enchantments.LOOTING, 2));
        return ItemGenerator.couldBeEnchanted(item, enchantmentsList);
    }

    public static ItemStack shovel(){
        Item item = Rand.getOne(Items.DIAMOND_SHOVEL, Items.IRON_SHOVEL, Items.GOLDEN_SHOVEL);
        List<Tuple<Enchantment, Integer>> enchantmentsList = ImmutableList.of(
                enchantmentProbability(Enchantments.UNBREAKING, 5)
        );
        return ItemGenerator.couldBeEnchanted(item, enchantmentsList);
    }

    public static ItemStack helmet(){
        Item item = Rand.getOne(Items.DIAMOND_HELMET, Items.IRON_HELMET, Items.GOLDEN_HELMET);
        List<Tuple<Enchantment, Integer>> enchantmentsList = ImmutableList.of(
                enchantmentProbability(Enchantments.UNBREAKING, 5),
                enchantmentProbability(Enchantments.FIRE_PROTECTION, 3),
                enchantmentProbability(Enchantments.RESPIRATION, 2)
        );
        return ItemGenerator.couldBeEnchanted(item, enchantmentsList);
    }

    public static ItemStack chest(){
        Item item = Rand.getOne(Items.DIAMOND_CHESTPLATE, Items.IRON_CHESTPLATE, Items.GOLDEN_CHESTPLATE);
        List<Tuple<Enchantment, Integer>> enchantmentsList = ImmutableList.of(
                enchantmentProbability(Enchantments.UNBREAKING, 5),
                enchantmentProbability(Enchantments.FIRE_PROTECTION, 3),
                enchantmentProbability(Enchantments.THORNS, 2)
        );
        return ItemGenerator.couldBeEnchanted(item, enchantmentsList);
    }

    public static ItemStack leggings(){
        Item item = Rand.getOne(Items.DIAMOND_LEGGINGS, Items.IRON_LEGGINGS, Items.GOLDEN_LEGGINGS);
        List<Tuple<Enchantment, Integer>> enchantmentsList = ImmutableList.of(
                enchantmentProbability(Enchantments.UNBREAKING, 5),
                enchantmentProbability(Enchantments.FIRE_PROTECTION, 3),
                enchantmentProbability(Enchantments.THORNS, 2)
        );
        return ItemGenerator.couldBeEnchanted(item, enchantmentsList);
    }

    public static ItemStack boots(){
        Item item = Rand.getOne(Items.DIAMOND_BOOTS, Items.IRON_BOOTS, Items.GOLDEN_BOOTS);
        List<Tuple<Enchantment, Integer>> enchantmentsList = ImmutableList.of(
                enchantmentProbability(Enchantments.UNBREAKING, 5),
                enchantmentProbability(Enchantments.FIRE_PROTECTION, 3),
                enchantmentProbability(Enchantments.FEATHER_FALLING, 2)
        );
        return ItemGenerator.couldBeEnchanted(item, enchantmentsList);
    }

    public static ItemStack elytra(){
        Item item = Items.ELYTRA;
        List<Tuple<Enchantment, Integer>> enchantmentsList = ImmutableList.of(
                enchantmentProbability(Enchantments.UNBREAKING, 5)
        );
        return ItemGenerator.couldBeEnchanted(item, enchantmentsList);
    }

    public static ItemStack bow(){
        Item item = Items.BOW;
        List<Tuple<Enchantment, Integer>> enchantmentsList = ImmutableList.of(
                enchantmentProbability(Enchantments.UNBREAKING, 5),
                enchantmentProbability(Enchantments.INFINITY, 4),
                enchantmentProbability(Enchantments.POWER, 3),
                enchantmentProbability(Enchantments.FLAME, 3)
        );
        return ItemGenerator.couldBeEnchanted(item, enchantmentsList);
    }

    /**
     *
     * @param item
     * @param enchantments (list of enchantment alternatives)
     * @return
     */
    private static ItemStack couldBeEnchanted(Item item, List<Tuple<Enchantment, Integer>> enchantments){
        Random random = new Random();
        ItemStack stack = new ItemStack(item);
        enchantments.forEach(tuple -> {
            if (Rand.rand5() < tuple.getB()) {
                stack.addEnchantment(tuple.getA(), random.nextInt(MAX_LEVEL));
            }
        });
        return stack;
    }

    /**
     *
     * @param enchantment
     * @param probability (between 1 and 5; probability 5 is sure to obtain)
     * @return
     */
    private static Tuple<Enchantment, Integer> enchantmentProbability(Enchantment enchantment, int probability) {
        return new Tuple<>(enchantment, probability);
    }

}
