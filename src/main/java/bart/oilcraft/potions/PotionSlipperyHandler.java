package bart.oilcraft.potions;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingEvent;

/**
 * Created by Bart on 6-11-2014.
 */
public class PotionSlipperyHandler {

    @SubscribeEvent
    public void Slippery(TickEvent.PlayerTickEvent event) {
        if (event.player.getHeldItem() != null) {
            if (event.player.isPotionActive(ModPotions.slippery)) {
                if (event.player.worldObj.rand.nextInt(140) == 6) {
                    event.player.dropOneItem(true);
                }
            }
        }
    }
}
