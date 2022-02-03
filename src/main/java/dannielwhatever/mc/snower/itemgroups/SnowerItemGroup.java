package dannielwhatever.mc.snower.itemgroups;

import dannielwhatever.mc.snower.blocks.SnowerBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class SnowerItemGroup extends ItemGroup {

    public SnowerItemGroup(String label) {
        super(label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(new SnowerBlock());

    }
}
