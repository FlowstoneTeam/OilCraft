package bart.oilcraft.enchants;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.command.server.CommandSummon;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;


/**
 * Created by Bart on 20-9-2014.
 */
public class EnchantSlipperyHandler {

    public int Process;


    @SubscribeEvent
    public void Slippery(TickEvent.PlayerTickEvent event) {
        if (event.player.getHeldItem() != null) {
            if ((EnchantmentHelper.getEnchantments(event.player.getHeldItem()).containsKey(EnchantRegistry.SlipperyEnchant.effectId))) {
                if (Process == 140) {
                    event.player.dropOneItem(true);
                    Process = 0;

                } else {
                    Process++;
                }
            } else {
                Process = 0;
            }
        }
    }
}


