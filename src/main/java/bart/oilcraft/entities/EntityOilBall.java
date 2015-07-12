package bart.oilcraft.entities;

import bart.oilcraft.potions.ModPotions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * Created by Bart on 6-11-2014.
 */
public class EntityOilBall extends EntityThrowable {
    private static final String __OBFID = "CL_00001722";

    public EntityOilBall(World par1) {
        super(par1);
    }

    public EntityOilBall(World par1, EntityLivingBase par2) {
        super(par1, par2);
    }

    public EntityOilBall(World par1, double par2, double par3, double par4) {
        super(par1, par2, par3, par4);
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(MovingObjectPosition par1) {
        if (par1.entityHit != null) {
            if (par1.entityHit instanceof EntityPlayer) {
                ((EntityPlayer) par1.entityHit).addPotionEffect(new PotionEffect(ModPotions.slippery.id, 500, 0, false));
            } else if (par1.entityHit instanceof EntityGooBall) {
                ((EntityGooBall) par1.entityHit).heal(1);
            }
        }

        for (int i = 0; i < 8; ++i) {
            this.worldObj.spawnParticle("snowballpoof", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }

        if (!this.worldObj.isRemote) {
            this.setDead();
        }
    }
}
