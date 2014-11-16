package bart.oilcraft.tileentities;


import bart.oilcraft.fluids.ModFluids;
import bart.oilcraft.items.ModItems;
import bart.oilcraft.lib.handler.ConfigurationHandler;
import bart.oilcraft.util.OilFurnaceRegistry;
import bart.oilcraft.util.Util;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import net.minecraft.block.BlockFire;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;
import org.lwjgl.Sys;

/**
 * Created by bart on 18-10-2014.
 */
public class TileEntityOilFurnace extends TileEntity implements ISidedInventory, IFluidHandler, IEnergyHandler {
    public static final int[] slotsInsert = new int[]{0};
    public static final int[] slotsExtract = new int[]{1};

    public ItemStack[] items = new ItemStack[4];
    public FluidTank tank = new FluidTank(10000);
    public int Process;
    public int cycles;
    public int facing;

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
        return ((slot == 3 && stack.getItem() == ModItems.EnergyDistributeUpgrade) ||slot == 0);
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
        return "containers.ContainerOilFurnace";
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
        return ((slot == 3 && stack.getItem() == ModItems.EnergyDistributeUpgrade) || slot == 0);
    }
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        tank.readFromNBT(nbt);
        Util.loadInventory(nbt, this);
        energy.readFromNBT(nbt);
        facing = nbt.getInteger("facing");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        tank.writeToNBT(nbt);
        Util.saveInventory(nbt, this);
        energy.writeToNBT(nbt);
        nbt.setInteger("facing", facing);
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

    @Override
    public void updateEntity() {
        if (worldObj.isRemote) return;
        distributePower();
        //signEdit();
        customSmelting();
        furnaceSmelt();
    }
    public int getOilUsage(ItemStack stack){
        if (stack == null || OilFurnaceRegistry.getItemIndex(stack) < 0){
            return 0;
        }
        else {
            return OilFurnaceRegistry.oil[OilFurnaceRegistry.getItemIndex(stack)];
        }
    }
    public int getItemProcess(ItemStack stack){
        if (stack == null || OilFurnaceRegistry.getItemIndex(stack) < 0 || !canSmelt()){
            return 0;
        }
        else {
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
        if (this.items[0] == null)
        {
            return false;
        }
        else
        {
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.items[0]);
            if (itemstack == null) return false;
            if (this.items[1] == null) return true;
            if (!this.items[1].isItemEqual(itemstack)) return false;
            int result = items[1].stackSize + itemstack.stackSize;
            return result <= getInventoryStackLimit() && result <= this.items[1].getMaxStackSize();
        }
    }
    public void smelt() {
        if (this.canSmelt()) {
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.items[0]);
            if (this.items[1] == null) {
                this.items[1] = itemstack.copy();
            } else if (this.items[1].getItem() == itemstack.getItem()) {
                this.items[1].stackSize += itemstack.stackSize;
            }

            --this.items[0].stackSize;

            if (this.items[0].stackSize <= 0) {
                this.items[0] = null;
            }
        }
    }
    public void furnaceSmelt(){
        if (canSmelt()) {
            if (items[0] != null && this.canSmelt() && energy.getEnergyStored() >= 100) {
                if (tank.getFluidAmount() > ConfigurationHandler.OilUsageFur || cycles > 0) {
                    if (Process >= ConfigurationHandler.ProcessTimeFur/100*75) {
                        Process = 0;
                        energy.extractEnergy(ConfigurationHandler.RfForOilFur, false);
                        smelt();
                        cycles++;
                        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
                        if(cycles == ConfigurationHandler.cyclesAmountFur) {
                            tank.drain(ConfigurationHandler.OilUsageFur, true);
                            cycles = 0;
                            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
                        }
                    } else Process++;
                }else
                if (Process >= ConfigurationHandler.ProcessTimeFur) {
                    Process = 0;
                    energy.extractEnergy(ConfigurationHandler.RfForOilFur, false);
                    smelt();
                    this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
                } else Process++;
            }
        }
    }

    public void customSmelting(){
        int whitelistID = OilFurnaceRegistry.getItemIndex(items[0]);
        if(worldObj.getBlock(xCoord, yCoord -1, zCoord) instanceof BlockFire) {
            if (!(whitelistID < 0)) return;
            if ( whitelistID < 0 || whitelistID >= OilFurnaceRegistry.allowedItemsOut.length) return;
            if ((OilFurnaceRegistry.allowedItemsOut[whitelistID] != null)) {
                System.out.println("3");
                if (energy.getEnergyStored() >= getItemRF(items[0]) && tank.getFluidAmount() >= getOilUsage(items[0])) {
                    System.out.println("4");
                    if (Process >= getItemProcess(items[0])) {
                        System.out.println("5");
                        ItemStack output = OilFurnaceRegistry.allowedItemsOut[whitelistID];
                        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
                        setInventorySlotContents(0, items[0].stackSize == 1 ? null : new ItemStack(items[0].getItem(), items[0].stackSize - 1, items[0].getItemDamage()));
                        setInventorySlotContents(1, items[1].stackSize == 0 ? items[1] = output : new ItemStack(items[0].getItem(), items[1].stackSize + 1));
                        Process = 0;
                        energy.extractEnergy(getItemRF(items[0]), true);
                        tank.drain(getOilUsage(items[0]), true);
                    } else Process++;
                }
            }
        }
    }

    public void distributePower(){
        if (items[3] != null && items[3].getItem() == ModItems.EnergyDistributeUpgrade) {
            for (int i = 0; i < ForgeDirection.VALID_DIRECTIONS.length; i++) {
                ForgeDirection direction = ForgeDirection.VALID_DIRECTIONS[i];
                TileEntity te = worldObj.getTileEntity(this.xCoord + direction.offsetX, this.yCoord + direction.offsetY, this.zCoord + direction.offsetZ);
                if (te instanceof IEnergyHandler) {
                    int sending = 10;
                    int received = ((IEnergyHandler) te).receiveEnergy(direction, sending, true);
                    if (received <= sending && received > 0 && energy.getEnergyStored() >= sending && energy.getEnergyStored() - ((IEnergyHandler) te).getEnergyStored(direction) >= sending) {
                        energy.extractEnergy(received, false);
                        ((IEnergyHandler) te).receiveEnergy(direction, received, false);
                    }
                }
            }
        }
    }



    /*public void signEdit(){
        TileEntity te = worldObj.getTileEntity(xCoord, yCoord+1, zCoord);
        if (te instanceof TileEntitySign){
            worldObj.markBlockForUpdate(xCoord, yCoord+1, zCoord);
            ((TileEntitySign) te).signText[0]="Energy " + energy.getEnergyStored() + "/" + energy.getMaxEnergyStored();
            ((TileEntitySign) te).signText[1]="Fluid " + tank.getFluidAmount() + "/" + tank.getCapacity();
            ((TileEntitySign) te).signText[2]="Process " + Process + "/" + getItemProcess(items[0]);
            ((TileEntitySign) te).signText[3]="Block: Oil Furnace";
        }
    }*/
}
