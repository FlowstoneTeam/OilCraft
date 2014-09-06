package bart.oilcraft.tileentities;

import bart.oilcraft.blocks.ModBlocks;
import bart.oilcraft.containers.ContainerOilCompressor;
import bart.oilcraft.fluids.ModFluids;
import bart.oilcraft.items.ModItems;
import bart.oilcraft.util.Util;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.Slot;
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
 * Created by Bart on 4-6-2014.
 */
public class OilCompressorEntity extends TileEntity implements ISidedInventory, IFluidHandler{

    public static final int[] slotsInsert = new int[] { 0, 1 };
    public static final int[] slotsExtract = new int[] { 2 };

    public ItemStack[] items = new ItemStack[3];
    public FluidTank tank = new FluidTank(10000);
    public int progress;

    @Override
    public int[] getAccessibleSlotsFromSide(int side) {
        return side == 0 ? slotsExtract : slotsInsert;
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int side) {

        //System.out.println("Item 1: " + stack.getItem() + "  Item 2: " + Item.getItemFromBlock(Blocks.cobblestone));

        return (slot == 0 && (stack.getItem() == Item.getItemFromBlock(Blocks.cobblestone) || Item.getIdFromItem(stack.getItem()) == Block.getIdFromBlock(ModBlocks.CrudeOilOre))) ||
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

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return (slot == 0 && (stack.getItem() == Item.getItemFromBlock(Blocks.cobblestone) || Item.getIdFromItem(stack.getItem()) == Block.getIdFromBlock(ModBlocks.CrudeOilOre))) ||
               (slot == 1 && stack.getItem() == Items.bucket);
    }

    @Override
    public void updateEntity() {
        if (worldObj.isRemote) return;
        if (getOilLevel(items[0]) > 0) {
            if (progress >= 80) {
                int add = getOilLevel(items[0]);

                if(tank.getFluidAmount() + add <= tank.getCapacity()){
                    tank.fill(new FluidStack(ModFluids.Oil, add), true);
                    this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
                    setInventorySlotContents(0, items[0].stackSize == 1 ? null : new ItemStack(items[0].getItem(), items[0].stackSize - 1));
                    progress = 0;
                }
            }
            else {
                progress++;
            }

        }
        else
        {
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
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);

        tank.readFromNBT(nbt);

        Util.loadInventory(nbt, this);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);

        tank.writeToNBT(nbt);

        Util.saveInventory(nbt, this);
    }

    @Override
    public Packet getDescriptionPacket(){
        NBTTagCompound Tag = new NBTTagCompound();
        tank.writeToNBT(Tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, Tag);
    }

    @Override
    public void onDataPacket( NetworkManager Net, S35PacketUpdateTileEntity Packet){
        tank.readFromNBT(Packet.func_148857_g());
    }

    public FluidTank getTank(){
        return tank;
    }


    public static int getOilLevel(ItemStack stack)
    {
        if (stack == null)
        {
            return 0;
        }
        else {

            if (stack.getItem() == Item.getItemFromBlock(Blocks.cobblestone)) {
                return 1;
            }
            if (stack.getItem() == Item.getItemFromBlock(ModBlocks.CrudeOilOre)) {
                return 500;
            }

            if (stack.getItem() == Items.diamond){
                return 1000;
            }
        }
        return 0;
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
        return drain(from, resource.amount, doDrain);
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
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
}