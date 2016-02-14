package bart.oilcraft.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Created by Bart on 13/02/2016.
 */
public class PotionSlippery extends OCPotion {
    public PotionSlippery() {
        super("slippery", false, 0xFFFFFF, 0);
    }

    @Override
    public boolean isReady(int p_76397_1_, int p_76397_2_) {
        return true;
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBase, int level) {
        if (entityLivingBase.getHeldItem() != null) {
            if (entityLivingBase.isPotionActive(OCPotionRegistry.slippery)) {
                if (entityLivingBase.worldObj.rand.nextInt(140 - 10 * level) == 0) {
                    entityLivingBase.entityDropItem(entityLivingBase.getHeldItem(), 1f);
                    if (entityLivingBase instanceof EntityPlayer)
                        ((EntityPlayer) entityLivingBase).destroyCurrentEquippedItem();
                    else
                        entityLivingBase.setCurrentItemOrArmor(0, null);
                }
            }
        }
    }
}