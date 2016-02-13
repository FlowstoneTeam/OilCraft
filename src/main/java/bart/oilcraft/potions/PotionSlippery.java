package bart.oilcraft.potions;

import net.minecraft.entity.EntityLivingBase;

/**
 * Created by Bart on 13/02/2016.
 */
public class PotionSlippery extends OCPotion {
    public PotionSlippery() {
        super("slippery", false, 0xFFFFFF, 0);
    }


    @Override
    public void performEffect(EntityLivingBase entityLivingBase, int level) {
        if (entityLivingBase.getHeldItem() != null) {
            if (entityLivingBase.isPotionActive(OCPotionRegistry.slippery)) {
                if (entityLivingBase.worldObj.rand.nextInt(140 - 10 * level) == 6) {
                    entityLivingBase.dropItem(entityLivingBase.getHeldItem().getItem(), 1);
                }
            }
        }
    }
}