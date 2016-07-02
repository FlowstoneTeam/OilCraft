package imdutch21.oilcraft.tileentity;

import imdutch21.oilcraft.fluids.OCFluidRegistry;
import imdutch21.oilcraft.potion.OCPotionRegistry;
import imdutch21.oilcraft.recipe.OilCompressorRecipe;
import imdutch21.oilcraft.util.InventoryUtils;
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
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.*;

import java.util.List;
import java.util.Random;

public class TileEntityOilCompressor extends OCTickingTileEntity implements ISidedInventory, IFluidHandler, IEnergyReceiver, IEnergyHandler {

    public FluidTank tank = new FluidTank(10000);
    public EnergyStorage energyStorage = new EnergyStorage(8000, 1000);
    public ItemStack[] items = new ItemStack[3];
    public static final int[] slotsInsert = new int[]{0, 1};
    public static final int[] slotsExtract = new int[]{2};

    public int progress = 0;

    @Override
    public void update() {
        super.update();
        if (worldObj.isRemote) return;
        if (items[0] != null) {
            OilCompressorRecipe recipe = OilCompressorRecipe.getRecipeFromItem(items[0]);
            if (recipe != null && tank.getFluidAmount() + recipe.oilAmount <= tank.getCapacity() && energyStorage.getEnergyStored() - recipe.energyAmount >= 0) {
                if (progress >= recipe.time) {
                    progress = 0;
                    tank.fill(new FluidStack(OCFluidRegistry.OIL, recipe.oilAmount), true);
                    energyStorage.extractEnergy(recipe.energyAmount, false);
                    setInventorySlotContents(0, items[0].stackSize > 1 ? new ItemStack(items[0].getItem(), items[0].stackSize - 1, items[0].getItemDamage()) : null);
                    Random random = new Random();
                    worldObj.notifyBlockUpdate(getPos(), worldObj.getBlockState(getPos()), worldObj.getBlockState(getPos()), 3);
                    if (random.nextInt(40) == 0){
                        List<EntityLivingBase> list = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(getPos().getX() - 5, getPos().getY() - 5, getPos().getZ() - 5, getPos().getX() + 5, getPos().getY() + 5, getPos().getZ() + 5));
                        for (EntityLivingBase entity : list)
                            entity.addPotionEffect(new PotionEffect(OCPotionRegistry.SLIPPERY, 60, 2));
                    }
                } else {
                    progress++;
                    worldObj.notifyBlockUpdate(getPos(), worldObj.getBlockState(getPos()), worldObj.getBlockState(getPos()), 3);
                }
            } else {
                progress = 0;
            }
        } else {
            progress = 0;
        }

        if (items[1] != null){
            if (items[1].getItem() == Items.BUCKET && tank.getFluidAmount() >= 1000 && items[2] == null){
                tank.drain(1000, true);

                items[2] = UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, OCFluidRegistry.OIL);
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
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        tank.writeToNBT(nbt);
        InventoryUtils.saveInventory(nbt, this);
        energyStorage.writeToNBT(nbt);
        nbt.setInteger("progress", progress);
        return nbt;
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound tag = new NBTTagCompound();
        writeToNBT(tag);
        return new SPacketUpdateTileEntity(getPos(), 0, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        NBTTagCompound nbt = packet.getNbtCompound();
        readFromNBT(nbt);
    }
    @Override
    public int fill(EnumFacing from, FluidStack resource, boolean doFill) {
        return 0;
    }

    @Override
    public FluidStack drain(EnumFacing from, FluidStack resource, boolean doDrain) {
        worldObj.notifyBlockUpdate(getPos(), worldObj.getBlockState(getPos()), worldObj.getBlockState(getPos()), 3);
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
        worldObj.notifyBlockUpdate(getPos(), worldObj.getBlockState(getPos()), worldObj.getBlockState(getPos()), 3);
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
        return direction != EnumFacing.DOWN && ((index == 1 && itemStackIn.getItem() == Items.BUCKET) || index == 0);
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
        return (index == 1 && stack.getItem() == Items.BUCKET) || (index == 0 && stack != null);
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
    public ITextComponent getDisplayName() {
        return null;
    }
}
