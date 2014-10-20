package bart.oilcraft.tileentities;

import bart.oilcraft.containers.SlotWhitelist;
import bart.oilcraft.fluids.ModFluids;
import bart.oilcraft.util.OilFurnaceRegistry;
import bart.oilcraft.util.OilInfuserRegistry;
import bart.oilcraft.util.Util;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;

/**
 * Created by bart on 18-10-2014.
 */
public class TileEntityOilFurnace extends TileEntity implements ISidedInventory, IFluidHandler, IEnergyHandler {
    public static final int[] slotsInsert = new int[]{0, 1};
    public static final int[] slotsExtract = new int[]{2};

    public ItemStack[] items = new ItemStack[3];
    public FluidTank tank = new FluidTank(10000);
    public int progress;

    public EnergyStorage energy = new EnergyStorage(8000, 1000);

    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
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

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return true;
    }

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        return tank.fill(resource, doFill);
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        return null;
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        return null;
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        if (fluid == ModFluids.Oil) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return false;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[]{new FluidTankInfo(tank)};
    }

    public FluidTank getTank() {
        return tank;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        return side == 0 ? slotsExtract : slotsInsert;
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int side) {
        return (slot == 0 && SlotWhitelist.arrayContains(OilInfuserRegistry.allowedItemsIn, stack));
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack stack, int side) {
        return (slot == 1);
    }

    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int var1) {
        if (var1 > items.length) return null;
        return items[var1];
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
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
        this.items[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit()) {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName() {
        return "containers.ContainerOilInfuser";
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
    public void openInventory() {}

    @Override
    public void closeInventory() {}


    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return (slot == 0 && SlotWhitelist.arrayContains(OilInfuserRegistry.allowedItemsIn, stack));
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
    public void updateEntity() {
        if (worldObj.isRemote) return;
        int whitelistID = OilFurnaceRegistry.getItemIndex(items[0]);
        if ( whitelistID < 0 || whitelistID >= OilFurnaceRegistry.allowedItemsOut.length) {
            if ((OilFurnaceRegistry.allowedItemsOut[whitelistID] != null)) {
                if (energy.getEnergyStored() >= getItemRF(items[0]) && tank.getFluidAmount() >= getOilUsage(items[0])) {
                    if (progress >= getItemProcess(items[0])) {
                        ItemStack output = OilFurnaceRegistry.allowedItemsOut[whitelistID];
                        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
                        setInventorySlotContents(0, items[0].stackSize == 1 ? null : new ItemStack(items[0].getItem(), items[0].stackSize - 1, items[0].getItemDamage()));
                        setInventorySlotContents(1, items[1].stackSize == 0 ? items[1] = output : new ItemStack(items[0].getItem(), items[1].stackSize + 1));
                        progress = 0;
                        energy.extractEnergy(getItemRF(items[0]), true);
                        tank.drain(getOilUsage(items[0]), true);
                    } else progress++;
                }
            }
        } else if (canSmelt()) {
            if (((items[1].getItem().equals(FurnaceRecipes.smelting().getSmeltingResult(items[0])) && items[1].stackSize + 1 <= items[1].getMaxStackSize()) || items[1].stackSize == 0) && energy.getEnergyStored() >= 100) {
                if (tank.getFluidAmount() > 100) {
                    if (progress >= getItemProcess(items[0])/100*75) {
                        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
                        setInventorySlotContents(0, items[0].stackSize == 1 ? null : new ItemStack(items[0].getItem(), items[0].stackSize - 1, items[0].getItemDamage()));
                        setInventorySlotContents(1, items[1].stackSize == 0 ? new ItemStack(FurnaceRecipes.smelting().getSmeltingResult(items[0]).getItem()) : new ItemStack(items[0].getItem(), items[1].stackSize + 1));
                        progress = 0;
                        energy.extractEnergy(getItemRF(items[0]), true);
                        tank.drain(getOilUsage(items[0]), true);
                    } else progress++;
                }else if (progress >= getItemProcess(items[0])) {
                    this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
                    setInventorySlotContents(0, items[0].stackSize == 1 ? null : new ItemStack(items[0].getItem(), items[0].stackSize - 1, items[0].getItemDamage()));
                    setInventorySlotContents(1, items[1].stackSize == 0 ? new ItemStack(FurnaceRecipes.smelting().getSmeltingResult(items[0]).getItem()) : new ItemStack(items[0].getItem(), items[1].stackSize + FurnaceRecipes.smelting().getSmeltingResult(items[0]).stackSize));
                    progress = 0;
                    energy.extractEnergy(getItemRF(items[0]), true);
                } else progress++;
            }

        }
    }
    public int getOilUsage(ItemStack stack){
        if (stack == null || OilFurnaceRegistry.getItemIndex(stack) < 0){
            return 0;
        }
        else if(canSmelt()) {
            return 100;
        }else {
            return OilFurnaceRegistry.oil[OilFurnaceRegistry.getItemIndex(stack)];
        }
    }
    public int getItemProcess(ItemStack stack){
        if (stack == null || OilFurnaceRegistry.getItemIndex(stack) < 0){
            return 0;
        }
        else if(canSmelt()) {
            return 150;
        } else{
            return OilFurnaceRegistry.time[OilFurnaceRegistry.getItemIndex(stack)];
        }
    }
    public int getItemRF(ItemStack stack){
        if (stack == null || OilFurnaceRegistry.getItemIndex(stack) < 0){
            return 0;
        } else if(canSmelt()){
            return 100;
        }
        else {
            return OilFurnaceRegistry.energy[OilFurnaceRegistry.getItemIndex(stack)];
        }
    }

    private boolean canSmelt()
    {
        if (items[0] == null)
        {
            return false;
        }
        else
        {
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(items[0]);
            if (itemstack == null) return false;
            if (items[1] == null) return true;
            if (!items[1].isItemEqual(itemstack)) return false;
            int result = items[1].stackSize + itemstack.stackSize;
            return result <= getInventoryStackLimit() && result <= items[1].getMaxStackSize(); //Forge BugFix: Make it respect stack sizes properly.
        }
    }


    public void signEdit(){
        TileEntity te = worldObj.getTileEntity(xCoord, yCoord+1, zCoord);

        if (te instanceof TileEntitySign){
            worldObj.markBlockForUpdate(xCoord, yCoord+1, zCoord);
            ((TileEntitySign) te).signText[0]="Energy " + energy.getEnergyStored() + "/" + energy.getMaxEnergyStored();
            ((TileEntitySign) te).signText[1]="Fluid " + tank.getFluidAmount() + "/" + tank.getCapacity();
            ((TileEntitySign) te).signText[2]="Process " + progress + "/" + getItemProcess(items[0]);
            ((TileEntitySign) te).signText[3]="Block: Oil Furnace";
        }
    }
}