package bart.oilcraft.tileentities;

import bart.oilcraft.blocks.CrudeOilOre;
import bart.oilcraft.containers.SlotWhitelist;
import bart.oilcraft.fluids.ModFluids;
import bart.oilcraft.items.ModItems;
import bart.oilcraft.lib.handler.ConfigurationHandler;
import bart.oilcraft.util.OilCompressorRegistry;
import bart.oilcraft.util.Util;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import net.minecraft.block.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import net.minecraftforge.fluids.*;



/**
 * Created by Bart on 4-6-2014.
 */
public class OilCompressorEntity extends TileEntity implements ISidedInventory, IFluidHandler, IEnergyHandler {

    public static final int[] slotsInsert = new int[]{0, 1};
    public static final int[] slotsExtract = new int[]{2};

    public ItemStack[] items = new ItemStack[3];
    public FluidTank tank = new FluidTank(10000);
    public int progress;

    public EnergyStorage energy = new EnergyStorage(8000, 1000);


    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        return side == 0 ? slotsExtract : slotsInsert;
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int side) {

        //System.out.println("Item 1: " + stack.getItem() + "  Item 2: " + Item.getItemFromBlock(Blocks.cobblestone));
        //add block to list to make it be accepted
        return (slot == 0 && SlotWhitelist.arrayContains(OilCompressorRegistry.allowedItems, stack)) ||
                (slot == 1 && stack.getItem() == Items.bucket);
    }


    @Override
    public boolean canExtractItem(int slot, ItemStack stack, int side) {
        return slot == 2;
    }

    @Override
    public int getSizeInventory() {
        return 2;
    }

    @Override
    public ItemStack getStackInSlot(int var1) {
        if (var1 > items.length) return null;
        return items[var1];
    }

    @Override
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
        this.items[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit()) {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int par1) {
        if (this.items[par1] != null) {
            ItemStack itemstack = this.items[par1];
            this.items[par1] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    @Override
    public ItemStack decrStackSize(int par1, int par2) {
        if (this.items[par1] != null) {
            ItemStack itemstack;

            if (this.items[par1].stackSize <= par2) {
                itemstack = this.items[par1];
                this.items[par1] = null;
                return itemstack;
            } else {
                itemstack = this.items[par1].splitStack(par2);

                if (this.items[par1].stackSize == 0) {
                    this.items[par1] = null;
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }

    @Override
    public String getInventoryName() {
        return "containers.ContainerOilCompressor";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
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
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    //add block to list to make it be accepted
    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return (slot == 0 && (slot == 0 && SlotWhitelist.arrayContains(OilCompressorRegistry.allowedItems, stack))) ||
                (slot == 1 && stack.getItem() == Items.bucket);
    }

    @Override
    public void updateEntity() {
        if (worldObj.isRemote) return;
        if (getOilLevel(items[0]) > 0) {
            if (energy.getEnergyStored() >= getRFAmount(items[0])) {
                if (progress >= getProcessTime(items[0])) {
                    int add = getOilLevel(items[0]);
                    if (tank.getFluidAmount() + add <= tank.getCapacity()) {
                        tank.fill(new FluidStack(ModFluids.Oil, add), true);
                        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);

                        setInventorySlotContents(0, items[0].stackSize == 1 ? null : new ItemStack(items[0].getItem(), items[0].stackSize - 1));
                        progress = 0;
                        energy.extractEnergy(getRFAmount(items[0]), true);
                    }

                } else {
                    progress++;
                }
            }
        }
        else {
            progress = 0;
        }



        if (tank.getFluidAmount() >= 1000 && items[1] != null && items[1].getItem() == Items.bucket && items[2] == null) {
            tank.drain(1000, true);
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
            setInventorySlotContents(1, items[1].stackSize == 1 ? null : new ItemStack(items[1].getItem(), items[1].stackSize - 1));
            setInventorySlotContents(2, new ItemStack(ModItems.OilBucket));

        }



    }





    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        tank.readFromNBT(nbt);

        Util.loadInventory(nbt, this);
        energy.readFromNBT(nbt);

    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        tank.writeToNBT(nbt);
        Util.saveInventory(nbt, this);
        energy.writeToNBT(nbt);
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound Tag = new NBTTagCompound();
        tank.writeToNBT(Tag);
        energy.writeToNBT(Tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, Tag);
    }

    @Override
    public void onDataPacket(NetworkManager Net, S35PacketUpdateTileEntity Packet) {
        tank.readFromNBT(Packet.func_148857_g());
        energy.readFromNBT(Packet.func_148857_g());
    }

    public FluidTank getTank() {
        return tank;
    }

    //add block and amount of oil it gives
    public static int getOilLevel(ItemStack stack) {
        if (stack == null || OilCompressorRegistry.getItemIndex(stack) < 0) {
            return 0;
        } else {
            return OilCompressorRegistry.output[OilCompressorRegistry.getItemIndex(stack)];
        }
    }
    //add block and amount of energy it uses
    public static int getRFAmount(ItemStack stack){
        if (stack == null || OilCompressorRegistry.getItemIndex(stack) < 0) {
            return 0;
        } else {
            return OilCompressorRegistry.energy[OilCompressorRegistry.getItemIndex(stack)];
        }
    }
    //Add block and amount of time it takes to proses
    public static int getProcessTime(ItemStack stack){
        if (stack == null || OilCompressorRegistry.getItemIndex(stack) < 0) {
            return 0;
        } else {
            return OilCompressorRegistry.time[OilCompressorRegistry.getItemIndex(stack)];
        }
    }



    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        return 0;
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        if (resource == null || !resource.isFluidEqual(tank.getFluid())) {
            return null;
        }
        this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        return drain(from, resource.amount, doDrain);
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        return tank.drain(maxDrain, doDrain);
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return false;
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return true;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[]{new FluidTankInfo(tank)};
    }




    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return true;
    }

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        return energy.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        return 0;
    }

    @Override
    public int getEnergyStored(ForgeDirection from) {
        return energy.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored(ForgeDirection from) {
            return energy.getMaxEnergyStored();
    }




}

