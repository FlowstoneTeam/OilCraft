package bart.oilcraft.tileentity;

import bart.oilcraft.util.InventoryUtils;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fluids.*;

public class TileEntityOilFurnace extends OCTickingTileEntity implements ISidedInventory, IFluidHandler, IEnergyReceiver, IEnergyHandler {
    public FluidTank tank = new FluidTank(10000);
    public EnergyStorage energyStorage = new EnergyStorage(8000, 1000);
    public ItemStack[] items = new ItemStack[2];
    public int progress = 0;
    public int timeToProcess = 200;
    public int timesLeft = 0;

    @Override
    public void update() {
        super.update();
        if (worldObj.isRemote)
            return;
        if (items[0] != null) {
            ItemStack output = FurnaceRecipes.instance().getSmeltingResult(items[0]);
            if (output != null && (items[1] == null || (matches(items[1], output) && items[1].stackSize + output.stackSize <= items[1].getMaxStackSize())) && energyStorage.getEnergyStored() - 1600 >= 0) {
                if (progress >= timeToProcess * (timesLeft > 0 ? 0.6f : 1)) {
                    decrStackSize(0, 1);
                    if (items[1] == null)
                        items[1] = output.copy();
                    else
                        items[1].stackSize += output.stackSize;
                    energyStorage.extractEnergy(1600, false);
                    timesLeft--;
                    progress = 0;
                    worldObj.notifyBlockUpdate(getPos(), worldObj.getBlockState(getPos()), worldObj.getBlockState(getPos()), 3);
                } else {
                    progress++;
                }
            } else
                progress = 0;
        } else
            progress = 0;

        if (timesLeft == 0 && tank.getFluidAmount() >= 100) {
            tank.drain(100, true);
            timesLeft = 20;
            worldObj.notifyBlockUpdate(getPos(), worldObj.getBlockState(getPos()), worldObj.getBlockState(getPos()), 3);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        tank.readFromNBT(nbt);
        InventoryUtils.loadInventory(nbt, this);
        energyStorage.readFromNBT(nbt);
        progress = nbt.getInteger("progress");
        timesLeft = nbt.getInteger("timesLeft");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        tank.writeToNBT(nbt);
        InventoryUtils.saveInventory(nbt, this);
        energyStorage.writeToNBT(nbt);
        nbt.setInteger("progress", progress);
        nbt.setInteger("timesLeft", timesLeft);
    }

    @Override
    public Packet getDescriptionPacket() {
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
    public int fill(EnumFacing from, FluidStack resource, boolean doFill) {
        if (resource.getFluid() == FluidRegistry.getFluid("oil")) {
            worldObj.notifyBlockUpdate(getPos(), worldObj.getBlockState(getPos()), worldObj.getBlockState(getPos()), 3);
            return tank.fill(resource, doFill);
        } else
            return 0;
    }

    @Override
    public FluidStack drain(EnumFacing from, FluidStack resource, boolean doDrain) {
        return null;
    }

    @Override
    public FluidStack drain(EnumFacing from, int maxDrain, boolean doDrain) {
        return null;
    }

    @Override
    public boolean canFill(EnumFacing from, Fluid fluid) {
        return fluid == FluidRegistry.getFluid("oil");
    }

    @Override
    public boolean canDrain(EnumFacing from, Fluid fluid) {
        return false;
    }

    @Override
    public FluidTankInfo[] getTankInfo(EnumFacing from) {
        return new FluidTankInfo[]{new FluidTankInfo(tank)};
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        if (side == EnumFacing.DOWN)
            return new int[]{1};
        else
            return new int[]{0};
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return direction != EnumFacing.DOWN && index == 0;
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return direction == EnumFacing.DOWN && index == 1;
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
        return index == 0;
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
        return "container.oilFurnace";
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
