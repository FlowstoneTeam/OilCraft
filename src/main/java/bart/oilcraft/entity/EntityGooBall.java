package bart.oilcraft.entity;

import bart.oilcraft.potion.OCPotionRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

/**
 * Created by Bart on 14/02/2016.
 */
public class EntityGooBall extends EntitySlime {

    private boolean wasOnGround;

    public EntityGooBall(World worldIn) {
        super(worldIn);
        setSlimeSize(1);
    }

    public EntityGooBall(World worldIn, BlockPos pos) {
        super(worldIn);
        setSlimeSize(1);
        this.setPosition(pos.getX() + .5D, pos.getY() + .5D, pos.getZ() + .5D);
    }

    public void onUpdate() {
        this.squishFactor += (this.squishAmount - this.squishFactor) * 0.5F;
        this.prevSquishFactor = this.squishFactor;
        super.onUpdate();

        if (this.onGround && !this.wasOnGround) {
            int i = this.getSlimeSize();
            if (spawnCustomParticles()) {
                i = 0;
            } // don't spawn particles if it's handled by the implementation itself
            for (int j = 0; j < i * 8; ++j) {
                float f = this.rand.nextFloat() * (float) Math.PI * 2.0F;
                float f1 = this.rand.nextFloat() * 0.5F + 0.5F;
                float f2 = MathHelper.sin(f) * (float) i * 0.5F * f1;
                float f3 = MathHelper.cos(f) * (float) i * 0.5F * f1;
                World world = this.worldObj;
                EnumParticleTypes enumparticletypes = this.getParticleType();
                double d0 = this.posX + (double) f2;
                double d1 = this.posZ + (double) f3;
                world.spawnParticle(enumparticletypes, d0, this.getEntityBoundingBox().minY, d1, 0.0D, 0.0D, 0.0D, new int[0]);
            }

            this.playSound(this.getSplashSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);

            this.squishAmount = -0.5F;
        } else if (!this.onGround && this.wasOnGround) {
            this.squishAmount = 1.0F;
        }

        this.wasOnGround = this.onGround;
        this.alterSquishAmount();
    }

    public void writeEntityToNBT(NBTTagCompound tagCompound) {
        super.writeEntityToNBT(tagCompound);
        tagCompound.setInteger("Size", this.getSlimeSize() - 1);
        tagCompound.setBoolean("wasOnGround", this.wasOnGround);
    }

    public void readEntityFromNBT(NBTTagCompound tagCompund) {
        super.readEntityFromNBT(tagCompund);
        int i = tagCompund.getInteger("Size");

        if (i < 0) {
            i = 0;
        }

        this.setSlimeSize(i + 1);
        this.wasOnGround = tagCompund.getBoolean("wasOnGround");
    }

    @Override
    protected boolean spawnCustomParticles() {
        return true;
    }

    @Override
    public void setDead() {
    }

    @Override
    protected void collideWithEntity(Entity entityIn) {
        if (entityIn instanceof EntityLivingBase)
            ((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(OCPotionRegistry.SLIPPERY, 200, 1));
    }
}
