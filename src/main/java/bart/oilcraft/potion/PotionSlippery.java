package bart.oilcraft.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

/**
 * Created by Bart on 13/02/2016.
 */
public class PotionSlippery extends OCPotion {
    public PotionSlippery() {
        super(false, 0xFFFFFF, 0);
    }

    @Override
    public boolean isReady(int p_76397_1_, int p_76397_2_) {
        return true;
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBase, int level) {
        if (entityLivingBase != null && !entityLivingBase.worldObj.isRemote && entityLivingBase.isPotionActive(OCPotionRegistry.SLIPPERY)) {
            if (entityLivingBase.worldObj.rand.nextInt(140 - 10 * level > 0 ? 140 - 10 * level : 1) == 0) {
                entityDropHeld(entityLivingBase, entityLivingBase.worldObj.rand.nextBoolean() ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND);
            }

        }
    }


    private static void entityDropHeld(EntityLivingBase entityLivingBase, EnumHand hand) {
        if (entityLivingBase != null && entityLivingBase.getHeldItem(hand) != null) {
            Random random = entityLivingBase.worldObj.rand;

            double d0 = entityLivingBase.posY - 0.30000001192092896D + (double) entityLivingBase.getEyeHeight();
            EntityItem entityItem = new EntityItem(entityLivingBase.worldObj, entityLivingBase.posX, d0, entityLivingBase.posZ, entityLivingBase.getHeldItem(hand));
            entityItem.setPickupDelay(20);
            float f = random.nextFloat() * 0.5F;
            float f1 = random.nextFloat() * ((float) Math.PI * 2F);
            entityItem.motionX = (double) (-MathHelper.sin(f1) * f) - .5D;
            entityItem.motionZ = (double) (MathHelper.cos(f1) * f) - .5D;
            entityItem.motionY = 0.20000000298023224D;

            if (entityLivingBase.worldObj.spawnEntityInWorld(entityItem))
                entityLivingBase.setHeldItem(hand, null);
        }

    }
}