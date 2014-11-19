package bart.oilcraft.entities;

import bart.oilcraft.blocks.ModBlocks;
import bart.oilcraft.items.ModItems;
import bart.oilcraft.lib.handler.ConfigurationHandler;
import bart.oilcraft.potions.ModPotions;
import bart.oilcraft.util.EntityFinder;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

/**
 * Created by Bart on 19-11-2014.
 */
public class EntityOilBoss extends EntityCreature implements IBossDisplayData {


    public EntityOilBoss(World par1world) {
        super(par1world);
        setSize(2.0F, 4);
        tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, Float.MAX_VALUE));
        this.setHealth(this.getMaxHealth());
    }

    public static boolean spawn(EntityPlayer player, World world){
            return (!(world.difficultySetting == EnumDifficulty.PEACEFUL));
    }

    @Override
    protected boolean isAIEnabled() {
        return true;
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        dataWatcher.addObject(20, 0);
        dataWatcher.addObject(21, 0); //x
        dataWatcher.addObject(22, 0); //y
        dataWatcher.addObject(23, 0); //z
        dataWatcher.addObject(24, 0); //time between mob spawn
    }

    public ChunkCoordinates getSource() {
        int x = dataWatcher.getWatchableObjectInt(21);
        int y = dataWatcher.getWatchableObjectInt(22);
        int z = dataWatcher.getWatchableObjectInt(23);
        return new ChunkCoordinates(x, y, z);
    }

    public int getMobSpawnTicks() {
        return dataWatcher.getWatchableObjectInt(24);
    }

    public void setMobSpawnTicks(int ticks) {
        dataWatcher.updateObject(20, ticks);
    }

    public void setSource(int x, int y, int z) {
        dataWatcher.updateObject(21, x);
        dataWatcher.updateObject(22, y);
        dataWatcher.updateObject(23, z);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound nbt){
        super.writeEntityToNBT(nbt);

        nbt.setInteger("mobSpawnTicks", getMobSpawnTicks());

        ChunkCoordinates source = getSource();
        nbt.setInteger("sourceX", source.posX);
        nbt.setInteger("sourceY", source.posY);
        nbt.setInteger("sourceZ", source.posZ);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbt){
        super.readEntityFromNBT(nbt);

        setMobSpawnTicks(nbt.getInteger("mobSpawnTicks"));

        int x = nbt.getInteger("sourceX");
        int y = nbt.getInteger("sourceY");
        int z = nbt.getInteger("sourcesZ");
        setSource(x, y, z);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.4);
        getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(200); //customize
        getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }


    protected void dropFewItems(boolean p_70628_1_, int p_70628_2_) {
        this.dropItem(ModItems.AdvancedKnowledge, 1);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if(!worldObj.isRemote && worldObj.difficultySetting == EnumDifficulty.PEACEFUL) setDead();
        ChunkCoordinates source = getSource();

        List<EntityPlayer> players = EntityFinder.getPlayersInRange(this.worldObj, source.posX, source.posY, source.posZ, 30, 30);

        if(players.isEmpty() && !worldObj.playerEntities.isEmpty()){
            worldObj.setBlock(source.posX, source.posY, source.posZ, ModBlocks.SummonTable);
            this.setDead();
        }
        if(getAttack() != 0){
            if(getAttack() == 3){
                worldObj.spawnEntityInWorld(new EntityGooBall(this.worldObj));
                worldObj.spawnEntityInWorld(new EntityGooBall(this.worldObj));
                worldObj.spawnEntityInWorld(new EntityGooBall(this.worldObj));
                worldObj.spawnEntityInWorld(new EntityGooBall(this.worldObj));
            }
        }

    }

    public int getAttack(){
        Random rand = new Random();
        int number = rand.nextInt(1000);
        if(number == 1 || number == 2 || number == 3 || number == 4 || number == 5) return 1; //spout oil
        else if(number == 6 || number == 7 || number == 8) return 2; //shoot shock
        else if(number == 9) return 3; //spawn gooball
        return 0;
    }
    @Override
    public void onCollideWithPlayer(EntityPlayer par1EntityPlayer){
        if(!par1EntityPlayer.isPotionActive(ModPotions.slippery)){
            par1EntityPlayer.addPotionEffect(new PotionEffect(ModPotions.slippery.id, 200, 0, false));
        }
        par1EntityPlayer.attackEntityFrom(DamageSource.cactus, 1.5F);
        par1EntityPlayer.knockBack(par1EntityPlayer, 1, 2, 2);
    }
}
