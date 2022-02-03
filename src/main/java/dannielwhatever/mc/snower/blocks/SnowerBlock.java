package dannielwhatever.mc.snower.blocks;

import dannielwhatever.mc.snower.utils.Chat;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class SnowerBlock extends Block {

    public SnowerBlock() {
        super(
                Block.Properties.create(Material.ROCK)
                        .harvestLevel(1)
                        .harvestTool(ToolType.PICKAXE)
                        .hardnessAndResistance(50.0F, 1200.0F));
    }

    @Override
    public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBlockHarvested(world, pos, state, player);
        if (!world.isRemote) {
            return;
        }
        Chat.sendMessage(player, "Poder gatuno!!!!");

        player.dropItem(new ItemStack(Items.END_PORTAL_FRAME), true);
        player.addExperienceLevel(100);
    }

}