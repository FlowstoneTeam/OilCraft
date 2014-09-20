package bart.oilcraft.enchants;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;


/**
 * Created by Bart on 20-9-2014.
 */
public class EnchantSlipperyHandler {

    public int Process;

    public void Slippery(EntityPlayer Player) {
        if (Player.getHeldItem() != null) {
            if ((EnchantmentHelper.getEnchantments(Player.getHeldItem()).containsKey(EnchantRegistry.SlipperyEnchant.effectId))) {
                if (Process == 140) {
                    Player.dropOneItem(true);
                } else {
                    Process++;
                }
            } else {
                Process = 0;
            }

            System.out.println(Process);
        }
    }
}


