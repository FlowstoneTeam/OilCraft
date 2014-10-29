package bart.oilcraft.tileentities;

import bart.oilcraft.enchants.EnchantRegistry;
import bart.oilcraft.util.Util;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Bart on 29-10-2014.
 */
public class TileEntitySlipperyRemover extends TileEntity implements ISidedInventory, IEnergyHandler {
    public static final int[] slotsInsert = new int[]{0, 1};
    public static final int[] slotsExtract = new int[]{2};
    public ItemStack[] items = new ItemStack[3];

    public int Process;

    public EnergyStorage energy = new EnergyStorage(8000, 1000);


    @Override
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
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
    public int[] getAccessibleSlotsFromSide(int side) {
        return side == 0 ? slotsExtract : slotsInsert;
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int side) {
        return (slot == 0);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack stack, int side) {
        return (slot == 2);
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
        return "containers.ContainerSlipperyRemover";
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
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return true;
    }

    @Override
    public void openInventory() {    }

    @Override
    public void closeInventory() {    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return (slot == 0 || (slot == 1 && stack.getItem() == Items.paper));
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        Util.loadInventory(nbt, this);
        energy.readFromNBT(nbt);

    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        Util.saveInventory(nbt, this);
        energy.writeToNBT(nbt);
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound Tag = new NBTTagCompound();
        energy.writeToNBT(Tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, Tag);
    }
    @Override
    public void onDataPacket(NetworkManager Net, S35PacketUpdateTileEntity Packet) {
        energy.readFromNBT(Packet.func_148857_g());
    }

    @Override
    public void updateEntity() {
        if (worldObj.isRemote) return;
        distributePower();
        signEdit();
        if((EnchantmentHelper.getEnchantments(items[0]).containsKey(EnchantRegistry.SlipperyEnchant.effectId)) && energy.getEnergyStored() >= 500 && items[2].getItem() == null){
            if(Process == 400) {
                worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                ItemStack output = items[0];
                int amount = items[0].stackSize;
                setInventorySlotContents(2, output);
                setInventorySlotContents(1, items[1].stackSize == 1 ? null : new ItemStack(items[0].getItem(), items[0].stackSize - 1));
                setInventorySlotContents(0, null);
                Process = 0;
            } else Process++;
        }else Process = 0;
    }

    public void distributePower(){
        for (int i =0; i < ForgeDirection.VALID_DIRECTIONS.length; i++){
            ForgeDirection direction = ForgeDirection.VALID_DIRECTIONS[i];
            TileEntity te = worldObj.getTileEntity(this.xCoord + direction.offsetX, this.yCoord + direction.offsetY, this.zCoord + direction.offsetZ);
            if(te instanceof IEnergyHandler){
                int sending = 10;
                int received = ((IEnergyHandler)te).receiveEnergy(direction, sending, true);
                if (received <= sending && received > 0 && energy.getEnergyStored() >= sending &&  energy.getEnergyStored() - ((IEnergyHandler)te).getEnergyStored(direction) >= sending) {
                    energy.extractEnergy(received, false);
                    ((IEnergyHandler)te).receiveEnergy(direction, received, false);
                }
            }
        }
    }



    public void signEdit(){
        TileEntity te = worldObj.getTileEntity(xCoord, yCoord+1, zCoord);
        if (te instanceof TileEntitySign){
            worldObj.markBlockForUpdate(xCoord, yCoord+1, zCoord);
            ((TileEntitySign) te).signText[0]="Energy " + energy.getEnergyStored() + "/" + energy.getMaxEnergyStored();
            ((TileEntitySign) te).signText[1]="Fluid this block has no fluid";
            ((TileEntitySign) te).signText[2]="Process " + Process + "/" + "400";
            ((TileEntitySign) te).signText[3]="Block: Slippery Remover";
        }
    }
}
