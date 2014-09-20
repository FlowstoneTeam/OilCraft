package bart.oilcraft.enchants;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;


/**
 * Created by Bart on 20-9-2014.
 */
public class EnchantSlipperyHandler {

    public int Process;


    @SubscribeEvent
    public void Slippery(TickEvent.PlayerTickEvent event) {
        if (event.player.getHeldItem() != null) {
            System.out.println("Item droped");
            if ((EnchantmentHelper.getEnchantments(event.player.getHeldItem()).containsKey(EnchantRegistry.SlipperyEnchant.effectId))) {
                if (Process == 140) {
                    event.player.dropOneItem(true);
                    Process = 0;
                    System.out.println("Item droped");
                } else {
                    Process++;
                }
            } else {
                Process = 0;
            }
        }
    }
}


