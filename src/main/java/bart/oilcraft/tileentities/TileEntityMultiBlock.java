package bart.oilcraft.tileentities;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;

/**
 * Created by Bart on 13-7-2015.
 */
public class TileEntityMultiBlock extends TileEntity implements ISidedInventory, IFluidHandler, IEnergyHandler {
    int xParent, yParent, zParent;

    public FluidTank tank;
    public EnergyStorage energy;
    public boolean canOutputPower, canReceivePower, canOutputItem, canReceiveItem, canOutputFluid, canReceiveFluid, isFormed;

    public boolean checkBuddyDown, checkBuddyUp, checkBuddyNorth, checkBuddyEast, checkBuddySouth, checkBuddyWest;

    public ItemStack prevParentItemStack;
    public int timeTillUpdate;

    public String fluidCanOutput, fluidCanInput;

    @Override
    public void updateEntity() {
        if (worldObj.isRemote) return;
        if (isFormed) {
            checkForParent();
            checkForBuddy();
        }
    }

    public void checkForParent(){
        if (timeTillUpdate >= 5) {
            Block parent = worldObj.getBlock(xParent, yParent, zParent);
            if (parent != Block.getBlockFromItem(prevParentItemStack.getItem()))
                isFormed = false;
            prevParentItemStack = new ItemStack(Item.getItemFromBlock(parent));
        }
    }

    public void checkForBuddy(){
        if (timeTillUpdate >= 5) {
            timeTillUpdate = 0;
            for (int i = 0; i < ForgeDirection.VALID_DIRECTIONS.length; i++) {
                ForgeDirection direction = ForgeDirection.VALID_DIRECTIONS[i];
                TileEntity te = worldObj.getTileEntity(this.xCoord + direction.offsetX, this.yCoord + direction.offsetY, this.zCoord + direction.offsetZ);

                if(direction == ForgeDirection.DOWN && checkBuddyDown)
                    if(!(te instanceof TileEntityMultiBlock))
                        isFormed = false;
                if(direction == ForgeDirection.UP && checkBuddyUp)
                    if(!(te instanceof TileEntityMultiBlock))
                        isFormed = false;
                if(direction == ForgeDirection.NORTH && checkBuddyNorth)
                    if(!(te instanceof TileEntityMultiBlock))
                        isFormed = false;
                if(direction == ForgeDirection.EAST && checkBuddyEast)
                    if(!(te instanceof TileEntityMultiBlock))
                        isFormed = false;
                if(direction == ForgeDirection.SOUTH && checkBuddySouth)
                    if(!(te instanceof TileEntityMultiBlock))
                        isFormed = false;
                if(direction == ForgeDirection.WEST && checkBuddyWest)
                    if(!(te instanceof TileEntityMultiBlock))
                        isFormed = false;
            }
        } else
            timeTillUpdate++;
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        TileEntity tileEntityParent = worldObj.getTileEntity(xParent, yParent, zParent);
        if (isFormed && canReceivePower && tileEntityParent instanceof IEnergyHandler)
            return ((IEnergyHandler) tileEntityParent).receiveEnergy(from, maxReceive, simulate);
        return 0;
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        TileEntity tileEntityParent = worldObj.getTileEntity(xParent, yParent, zParent);
        if (isFormed && canOutputPower && tileEntityParent instanceof IEnergyHandler)
            return ((IEnergyHandler) tileEntityParent).extractEnergy(from, maxExtract, simulate);
        return 0;
    }

    @Override
    public int getEnergyStored(ForgeDirection from) {
        TileEntity tileEntityParent = worldObj.getTileEntity(xParent, yParent, zParent);
        if (isFormed && tileEntityParent instanceof IEnergyHandler)
            return ((IEnergyHandler) tileEntityParent).getEnergyStored(from);
        return 0;
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from) {
        TileEntity tileEntityParent = worldObj.getTileEntity(xParent, yParent, zParent);
        if (isFormed && tileEntityParent instanceof IEnergyHandler)
            return ((IEnergyHandler) tileEntityParent).getMaxEnergyStored(from);
        return 0;
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return isFormed && (canReceivePower || canOutputPower);
    }

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        TileEntity tileEntityParent = worldObj.getTileEntity(xParent, yParent, zParent);
        if (isFormed && canReceiveFluid && tileEntityParent instanceof IFluidHandler && resource.getFluid().equals(FluidRegistry.getFluid(fluidCanInput)))
            return ((IFluidHandler) tileEntityParent).fill(from, resource, doFill);
        return 0;
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        TileEntity tileEntityParent = worldObj.getTileEntity(xParent, yParent, zParent);
        if (isFormed && canOutputFluid && tileEntityParent instanceof IFluidHandler && resource.getFluid().equals(FluidRegistry.getFluid(fluidCanOutput)))
            return ((IFluidHandler) tileEntityParent).drain(from, resource, doDrain);
        return null;
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        TileEntity tileEntityParent = worldObj.getTileEntity(xParent, yParent, zParent);
        if (isFormed && canOutputFluid && tileEntityParent instanceof IFluidHandler )
            return ((IFluidHandler) tileEntityParent).drain(from, maxDrain, doDrain);
        return null;
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        TileEntity tileEntityParent = worldObj.getTileEntity(xParent, yParent, zParent);
        return isFormed && canReceiveFluid && tileEntityParent instanceof IFluidHandler && fluid.equals(FluidRegistry.getFluid(fluidCanInput)) && ((IFluidHandler) tileEntityParent).canFill(from, fluid);
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        TileEntity tileEntityParent = worldObj.getTileEntity(xParent, yParent, zParent);
        return isFormed && canOutputFluid && tileEntityParent instanceof IFluidHandler && fluid.equals(FluidRegistry.getFluid(fluidCanOutput)) && ((IFluidHandler) tileEntityParent).canDrain(from, fluid);
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        TileEntity tileEntityParent = worldObj.getTileEntity(xParent, yParent, zParent);
        if (isFormed && tileEntityParent instanceof IFluidHandler)
            return ((IFluidHandler) tileEntityParent).getTankInfo(from);
        return new FluidTankInfo[0];
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int slot) {
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack itemStack, int side) {
        return false;
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack itemStack, int side) {
        return false;
    }

    @Override
    public int getSizeInventory() {
        return 0;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return null;
    }

    @Override
    public ItemStack decrStackSize(int slot, int size) {
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {

    }

    @Override
    public String getInventoryName() {
        return null;
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 0;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return false;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack itemStack) {
        return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        canOutputPower = nbt.getBoolean("canOutputPower");
        canReceivePower = nbt.getBoolean("canReceivePower");
        canReceiveItem = nbt.getBoolean("canReceiveItem");
        canOutputItem = nbt.getBoolean("canOutputItem");
        canOutputFluid = nbt.getBoolean("canOutputFluid");
        canReceiveFluid = nbt.getBoolean("canReceiveFluid");
        isFormed = nbt.getBoolean("isFormed");
        xParent = nbt.getInteger("xParent");
        yParent = nbt.getInteger("yParent");
        zParent = nbt.getInteger("zParent");
        prevParentItemStack.readFromNBT(nbt);
        fluidCanOutput = nbt.getString("fluidCanOutput");
        fluidCanInput = nbt.getString("fluidCanInput");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setBoolean("canOutputPower", canOutputPower);
        nbt.setBoolean("canReceivePower", canReceivePower);
        nbt.setBoolean("canOutputItem", canOutputItem);
        nbt.setBoolean("canReceiveItem", canReceiveItem);
        nbt.setBoolean("canOutputFluid", canOutputFluid);
        nbt.setBoolean("canReceiveFluid", canReceiveFluid);
        nbt.setBoolean("isFormed", isFormed);
        nbt.setInteger("xParent", xParent);
        nbt.setInteger("yParent", yParent);
        nbt.setInteger("zParent", zParent);
        prevParentItemStack.writeToNBT(nbt);
        nbt.setString("fluidCanOutput", fluidCanOutput);
        nbt.setString("fluidCanInput", fluidCanInput);
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        writeToNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        NBTTagCompound nbt = packet.func_148857_g();
        readFromNBT(nbt);
    }
}
