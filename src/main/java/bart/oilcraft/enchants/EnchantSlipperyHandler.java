package bart.oilcraft.enchants;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;


/**
 * Created by Bart on 20-9-2014.
 */
public class EnchantSlipperyHandler {

    public int Process;

    @SubscribeEvent
    public void Slippery(EntityPlayer Player){
        if(Player.getHeldItem() == null) return;
        if(!(Player.getHeldItem().isItemEnchanted())) return;
           itemDrop(Player);
    }

    public void itemDrop(EntityPlayer player){
        if((EnchantmentHelper.getEnchantments(player.getHeldItem()).containsKey(EnchantRegistry.SlipperyEnchant.effectId))){
            if(Process == 140){
                player.dropOneItem(true);
            }
            else{
             Process++;
            }
        }
        else{
                Process = 0;
            }

    }

}
