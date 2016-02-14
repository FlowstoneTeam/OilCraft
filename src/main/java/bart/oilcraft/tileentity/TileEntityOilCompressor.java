package bart.oilcraft.tileentity;

import bart.oilcraft.fluids.OCFluidRegistry;
import bart.oilcraft.item.OCItemRegistry;
import bart.oilcraft.potion.OCPotionRegistry;
import bart.oilcraft.recipe.OilCompressorRecipe;
import bart.oilcraft.util.InventoryUtils;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.fluids.*;

import java.util.List;
import java.util.Random;

/**
 * Created by Bart on 12/02/2016.
 */
public class TileEntityOilCompressor extends OCTickingTileEntity implements ISidedInventory, IFluidHandler, IEnergyReceiver, IEnergyHandler {

    public FluidTank tank = new FluidTank(10000);
    public EnergyStorage energyStorage = new EnergyStorage(8000, 1000);
    public ItemStack[] items = new ItemStack[4];
    public static final int[] slotsInsert = new int[]{0, 1, 3};
    public static final int[] slotsExtract = new int[]{2};

    public int progress = 0;

    @Override
    public void update() {
        if (worldObj.isRemote) return;
        if (items[0] != null) {
            OilCompressorRecipe recipe = OilCompressorRecipe.getRecipeFromItem(items[0]);
            if (recipe != null && tank.getFluidAmount() + recipe.oilAmount <= tank.getCapacity() && energyStorage.getEnergyStored() - recipe.energyAmount >= 0) {
                if (progress >= recipe.time) {
                    progress = 0;
                    worldObj.markBlockForUpdate(getPos());
                    tank.fill(new FluidStack(OCFluidRegistry.oil, recipe.oilAmount), true);
                    energyStorage.extractEnergy(recipe.energyAmount, false);
                    setInventorySlotContents(0, items[0].stackSize > 1 ? new ItemStack(items[0].getItem(), items[0].stackSize - 1, items[0].getItemDamage()) : null);
                    Random random = new Random();
                    if (random.nextInt(40) == 0){
                        List<EntityLivingBase> list = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(getPos().getX() - 5, getPos().getY() - 5, getPos().getZ() - 5, getPos().getX() + 5, getPos().getY() + 5, getPos().getZ() + 5));
                        for (EntityLivingBase entity : list)
                            entity.addPotionEffect(new PotionEffect(OCPotionRegistry.slippery.getId(), 60, 2));
                    }
                } else {
                    progress++;
                }
            } else {
                progress = 0;
            }
        } else {
            progress = 0;
        }

        if (items[1] != null){
            if (items[1].getItem() == Items.bucket && tank.getFluidAmount() >= 1000 && items[2] == null){
                tank.drain(1000, true);
                items[2] = new ItemStack(OCItemRegistry.oilBucket);
                setInventorySlotContents(1, items[1].stackSize > 1 ? new ItemStack(items[1].getItem(), items[1].stackSize - 1, items[1].getItemDamage()) : null);
            }
        }
    }


    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        tank.readFromNBT(nbt);
        InventoryUtils.loadInventory(nbt, this);
        energyStorage.readFromNBT(nbt);
        progress = nbt.getInteger("progress");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        tank.writeToNBT(nbt);
        InventoryUtils.saveInventory(nbt, this);
        energyStorage.writeToNBT(nbt);
        nbt.setInteger("progress", progress);
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        writeToNBT(tag);
        return new S35PacketUpdateTileEntity(getPos(), 0, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        NBTTagCompound nbt = packet.getNbtCompound();
        readFromNBT(nbt);
    }
    @Override
    public int fill(EnumFacing from, FluidStack resource, boolean doFill) {
        return 0;
    }

    @Override
    public FluidStack drain(EnumFacing from, FluidStack resource, boolean doDrain) {
        this.worldObj.markBlockForUpdate(getPos());
        return tank.drain(resource.amount, doDrain);
    }

    @Override
    public FluidStack drain(EnumFacing from, int maxDrain, boolean doDrain) {
        return null;
    }

    @Override
    public boolean canFill(EnumFacing from, Fluid fluid) {
        return false;
    }

    @Override
    public boolean canDrain(EnumFacing from, Fluid fluid) {
        return true;
    }

    @Override
    public FluidTankInfo[] getTankInfo(EnumFacing from) {
        return new FluidTankInfo[]{new FluidTankInfo(tank)};
    }

    @Override
    public int receiveEnergy(EnumFacing from, int maxReceive, boolean simulate) {
        this.worldObj.markBlockForUpdate(getPos());
        return energyStorage.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int getEnergyStored(EnumFacing from) {
        return energyStorage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(EnumFacing from) {
        return energyStorage.getMaxEnergyStored();
    }

    @Override
    public boolean canConnectEnergy(EnumFacing from) {
        return true;
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        if (side == EnumFacing.DOWN)
            return slotsExtract;
        else
            return slotsInsert;
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return direction != EnumFacing.DOWN && ((index == 1 && itemStackIn.getItem() == Items.bucket) || index == 0);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return index == 2;
    }

    @Override
    public int getSizeInventory() {
        return 2;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        if (index > items.length) return null;
        return items[index];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (this.items[index] != null) {
            ItemStack itemstack;

            if (this.items[index].stackSize <= count) {
                itemstack = this.items[index];
                this.items[index] = null;
                return itemstack;
            } else {
                itemstack = this.items[index].splitStack(count);

                if (this.items[index].stackSize == 0) {
                    this.items[index] = null;
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        if (index > items.length) return null;
        if (this.items[index] != null) {
            ItemStack itemstack = this.items[index];
            this.items[index] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        this.items[index] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
            stack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return (index == 1 && stack.getItem() == Items.bucket) || (index == 0 && stack != null);
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < items.length; ++i) {
            this.items[i] = null;
        }
    }

    @Override
    public String getName() {
        return "container.oilCompressor";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public IChatComponent getDisplayName() {
        return null;
    }
}
