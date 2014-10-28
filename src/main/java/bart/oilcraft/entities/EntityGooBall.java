package bart.oilcraft.entities;

import bart.oilcraft.enchants.EnchantRegistry;
import bart.oilcraft.fluids.BlockOil;
import bart.oilcraft.fluids.ModFluids;
import bart.oilcraft.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

/**
 * Created by Bart on 27-10-2014.
 */
public class EntityGooBall extends EntitySlime {

    public float squishAmount;
    public float squishFactor;
    public float prevSquishFactor;
    private int slimeJumpDelay;

    public EntityGooBall(World par1World)
    {
        super(par1World);
        int i = 1 << this.rand.nextInt(3);
        this.yOffset = 0.0F;
        this.slimeJumpDelay = this.rand.nextInt(20) + 10;
        this.setSlimeSize(i);
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
        //this.dataWatcher.addObject(16, new Byte((byte)1));
    }

    @Override
    protected void setSlimeSize(int par1)
    {
        this.dataWatcher.updateObject(16, new Byte((byte)par1));
        this.setSize(1, 1);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue((double)(par1 * par1));
        this.setHealth(this.getMaxHealth());
        this.experienceValue = par1;
    }

    /**
     * Returns the size of the slime.
     */
    @Override
    public int getSlimeSize()
    {
        return 1;
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("Size", this.getSlimeSize() - 1);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.setSlimeSize(par1NBTTagCompound.getInteger("Size") + 1);
    }

    @Override
    protected String getJumpSound()
    {
        return "mob.slime." + (this.getSlimeSize() > 1 ? "big" : "small");
    }

    public boolean isBesideClimbableBlock()
    {
        return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
    }

    /**
     * Updates the WatchableObject (Byte) created in entityInit(), setting it to 0x01 if par1 is true or 0x00 if it is
     * false.
     */
    public void setBesideClimbableBlock(boolean p_70839_1_)
    {
        byte b0 = this.dataWatcher.getWatchableObjectByte(16);

        if (p_70839_1_)
        {
            b0 = (byte)(b0 | 1);
        }
        else
        {
            b0 &= -2;
        }

        this.dataWatcher.updateObject(16, Byte.valueOf(b0));
    }
    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate()
    {
        if (!this.worldObj.isRemote && this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL && this.getSlimeSize() > 0)
        {
            this.isDead = true;
        }

        if (!this.worldObj.isRemote)
        {
            this.setBesideClimbableBlock(this.isCollidedHorizontally);
        }

        this.squishFactor += (this.squishAmount - this.squishFactor) * 0.5F;
        this.prevSquishFactor = this.squishFactor;
        boolean flag = this.onGround;
        super.onUpdate();
        int i;

        if (this.onGround && !flag)
        {
            i = this.getSlimeSize();

            for (int j = 0; j < i * 8; ++j)
            {
                float f = this.rand.nextFloat() * (float)Math.PI * 2.0F;
                float f1 = this.rand.nextFloat() * 0.5F + 0.5F;
                float f2 = MathHelper.sin(f) * (float)i * 0.5F * f1;
                float f3 = MathHelper.cos(f) * (float)i * 0.5F * f1;
                this.worldObj.spawnParticle(this.getSlimeParticle(), this.posX + (double)f2, this.boundingBox.minY + 0.25, this.posZ + (double)f3, 0.0D, 0.0D, 0.0D);
            }

            if (this.makesSoundOnLand())
            {
                this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
            }

            this.squishAmount = -0.5F;
        }
        else if (!this.onGround && flag)
        {
            this.squishAmount = 1.0F;
        }

        this.alterSquishAmount();

        if (this.worldObj.isRemote)
        {
            i = this.getSlimeSize();
            this.setSize(0.6F * (float)i, 0.6F * (float)i);
        }
        for (int h =0; h < ForgeDirection.VALID_DIRECTIONS.length; h++) {
            ForgeDirection direction = ForgeDirection.VALID_DIRECTIONS[h];
            Block block = worldObj.getBlock(this.chunkCoordX + direction.offsetX, this.chunkCoordY + direction.offsetY, this.chunkCoordZ + direction.offsetZ);

            if (this.getMaxHealth() < this.getMaxHealth() && block instanceof BlockOil) {
                this.heal(.5F);
            }
        }
    }

    @Override
    protected void updateEntityActionState()
    {
        this.despawnEntity();
        EntityPlayer entityplayer = this.worldObj.getClosestVulnerablePlayerToEntity(this, 0.9D);

        if (entityplayer != null)
        {
            this.faceEntity(entityplayer, 10.0F, 20.0F);
        }

        if (this.onGround && this.slimeJumpDelay-- <= 0)
        {
            this.slimeJumpDelay = this.getJumpDelay();

            if (entityplayer != null)
            {
                this.slimeJumpDelay /= 3;
            }

            this.isJumping = true;

            if (this.makesSoundOnJump())
            {
                this.playSound(this.getJumpSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 0.8F);
            }

            this.moveStrafing = 1.0F - this.rand.nextFloat() * 2.0F;
            this.moveForward = (float)(1 * this.getSlimeSize());
        }
        else
        {
            this.isJumping = false;

            if (this.onGround)
            {
                this.moveStrafing = this.moveForward = 0.0F;
            }
        }
    }

    @Override
    protected void alterSquishAmount()
    {
        this.squishAmount *= 0.6F;
    }

    /**
     * Gets the amount of time the slime needs to wait between jumps.
     */
    @Override
    protected int getJumpDelay()
    {
        return this.rand.nextInt(20) + 10;
    }

    @Override
    protected EntityGooBall createInstance()
    {
        return new EntityGooBall(this.worldObj);
    }

    /**
     * Will get destroyed next tick.
     */
    @Override
    public void setDead()
    {
        int i = this.getSlimeSize();

        if (!this.worldObj.isRemote && i > 1 && this.getHealth() <= 0.0F)
        {
            // int j = 2 + this.rand.nextInt(3);
            int j = 2;

            for (int k = 0; k < j; ++k){
                float f = ((float)(k % 2) - 0.5F) * (float)i / 4.0F;
                float f1 = ((float)(k / 2) - 0.5F) * (float)i / 4.0F;
                EntityGooBall entityslime = this.createInstance();
                entityslime.setSlimeSize(i / 2);
                entityslime.setLocationAndAngles(this.posX + (double)f, this.posY + 0.5D, this.posZ + (double)f1, this.rand.nextFloat() * 360.0F, 0.0F);
                this.worldObj.spawnEntityInWorld(entityslime);
            }
        }

        super.setDead();
    }

    /**
     * Called by a player entity when they collide with an entity
     */
    @Override
    public void onCollideWithPlayer(EntityPlayer par1EntityPlayer)
    {
        if(par1EntityPlayer.getHeldItem() != null && !(EnchantmentHelper.getEnchantments(par1EntityPlayer.getHeldItem()).containsKey(EnchantRegistry.SlipperyEnchant.effectId))){
            par1EntityPlayer.getHeldItem().addEnchantment(EnchantRegistry.SlipperyEnchant, 1);
        }
        if (this.canDamagePlayer())
        {
            int i = this.getSlimeSize();

            if (this.canEntityBeSeen(par1EntityPlayer) && this.getDistanceSqToEntity(par1EntityPlayer) < 0.6D * (double)i * 0.6D * (double)i && par1EntityPlayer.attackEntityFrom(DamageSource.causeMobDamage(this), (float)this.getAttackStrength()))
            {
                this.playSound("mob.attack", 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
            }
        }
    }

    /**
     * Indicates weather the slime is able to damage the player (based upon the slime's size)
     */
    @Override
    protected boolean canDamagePlayer()
    {
        return false;
    }

    /**
     * Gets the amount of damage dealt to the player when "attacked" by the slime.
     */
    @Override
    protected int getAttackStrength()
    {
        return this.getSlimeSize();
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    @Override
    protected String getHurtSound()
    {
        return "mob.slime." + (this.getSlimeSize() > 1 ? "big" : "small");
    }

    /**
     * Returns the sound this mob makes on death.
     */
    @Override
    protected String getDeathSound()
    {
        return "mob.slime." + (this.getSlimeSize() > 1 ? "big" : "small");
    }

    protected Item getDropItem()
    {
        Random rand = new Random();
        int random = rand.nextInt(3);
        if(random == 1)return ModItems.OilBall;
        else return null;

    }

    @Override
    public boolean getCanSpawnHere()
    {
        if(worldObj.getBlock(this.chunkCoordX, this.chunkCoordY, this.chunkCoordZ) instanceof BlockOil)  return true;
        else return false;

    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    @Override
    protected float getSoundVolume()
    {
        return 0.1F * (float)this.getSlimeSize();
    }

    /**
     * The speed it takes to move the entityliving's rotationPitch through the faceEntity method. This is only currently
     * use in wolves.
     */
    @Override
    public int getVerticalFaceSpeed()
    {
        return 0;
    }

    /**
     * Returns true if the slime makes a sound when it jumps (based upon the slime's size)
     */
    @Override
    protected boolean makesSoundOnJump()
    {
        return this.getSlimeSize() > 0;
    }

    /**
     * Returns true if the slime makes a sound when it lands after a jump (based upon the slime's size)
     */
    @Override
    protected boolean makesSoundOnLand()
    {
        return this.getSlimeSize() > 2;
    }

}
