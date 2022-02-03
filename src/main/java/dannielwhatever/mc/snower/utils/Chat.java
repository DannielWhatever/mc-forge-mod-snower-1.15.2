package dannielwhatever.mc.snower.utils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class Chat {

    public static void sendMessage(LivingDeathEvent event, String message) {
        Entity source = event.getSource().getTrueSource();
        if (source instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) source;
            sendMessage(player, message);
        }
    }

    public static void sendMessage(PlayerEntity player, String message) {
        ITextComponent iTextComponent = new StringTextComponent(message);
        Style defaultStyle = new Style();
        defaultStyle.setColor(TextFormatting.DARK_AQUA);
        defaultStyle.setBold(true);
        iTextComponent.setStyle(defaultStyle);
        player.sendMessage(iTextComponent);
    }
}
