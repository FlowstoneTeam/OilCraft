package bart.oilcraft.potions;


import net.minecraft.entity.EntityLivingBase;

/**
 * Created by Bart on 6-11-2014.
 */
public class PotionSlippery extends ModPotions {
    public PotionSlippery() {
        super("Slippery", false, 0xFFFFFF, 0);
    }


    @Override
    public void performEffect(EntityLivingBase entityLivingBase, int level) {
        if (entityLivingBase.getHeldItem() != null) {
            if (entityLivingBase.isPotionActive(ModPotions.slippery)) {
                if (entityLivingBase.worldObj.rand.nextInt(140) == 6) {
                    entityLivingBase.dropItem(entityLivingBase.getHeldItem().getItem(), 1);
                }
            }
        }
    }
}
