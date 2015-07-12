package bart.oilcraft.tileentities;

import bart.oilcraft.items.ItemOilBall;
import bart.oilcraft.util.EntityFinder;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import java.util.List;

/**
 * Created by Bart on 14-11-2014.
 */
public class TileEntitySummonTable extends TileEntity {

    public boolean item1;
    public boolean item2;

    @Override
    public void updateEntity() {
        if (worldObj.isRemote) return;
        itemCheck();
        if (item1 && item2) {
            worldObj.createExplosion(new EntityCreeper(this.getWorldObj()), xCoord, yCoord + 1, zCoord, 3, false);
            worldObj.spawnEntityInWorld(new EntityZombie(this.getWorldObj()));
            worldObj.setBlock(xCoord, yCoord, zCoord, Blocks.air);
        }
    }

    public void itemCheck() {
        List<EntityItem> items = EntityFinder.getItemsInRange(this.getWorldObj(), xCoord, yCoord + 1, zCoord, 0.5, 0.5);
        for (EntityItem itemEntity : items) {
            if ((itemEntity.getEntityItem().getItem() instanceof ItemOilBall && itemEntity.getEntityItem().stackSize > 0) && !item1) {
                item1 = true;
                itemEntity.setDead();
            }
            if ((itemEntity.getEntityItem().getItem().equals(Items.diamond) && itemEntity.getEntityItem().stackSize > 0) && !item2) {
                item2 = true;
                itemEntity.setDead();
            }
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        item1 = nbt.getBoolean("item1");
        item2 = nbt.getBoolean("item2");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setBoolean("item1", item1);
        nbt.setBoolean("item2", item2);
    }
}
