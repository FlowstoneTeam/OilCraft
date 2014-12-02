package bart.oilcraft.tileentities;

import bart.oilcraft.blocks.BlockBodyReconstructor;
import bart.oilcraft.blocks.CrudeOilOre;
import bart.oilcraft.blocks.ModBlocks;
import bart.oilcraft.items.ModItems;
import bart.oilcraft.util.Util;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import ibxm.Player;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by Bart on 30-11-2014.
 */
public class TileEntityLiquidizer extends TileEntity implements IEnergyHandler {

    public static final int[] slotsInsert = new int[]{3};
    public EnergyStorage energy = new EnergyStorage(10000, 50000);
    public ItemStack[] items = new ItemStack[4];

    public int xCoordTel;
    public int yCoordTel;
    public int zCoordTel;

    public int facing;

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
        return energy.getEnergyStored();
    }

    @Override
    public boolean canConnectEnergy(ForgeDirection from) {
        return true;
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



    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        energy.readFromNBT(nbt);
        facing = nbt.getInteger("facing");
        xCoordTel = nbt.getInteger("xcoordtel");
        yCoordTel = nbt.getInteger("ycoordtel");
        zCoordTel = nbt.getInteger("zcoordtel");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        energy.writeToNBT(nbt);
        nbt.setInteger("facing", facing);
        nbt.setInteger("xcoordtel", xCoordTel);
        nbt.setInteger("ycoordtel", yCoordTel);
        nbt.setInteger("zcoordtel", zCoordTel);
    }

    /*@Override
    public void updateEntity() {
        if (worldObj.isRemote)return;
        //distributePower();
        //Teleport();
        signEdit();
    }*/


    public void Teleport(EntityPlayer player){
        if(worldObj.isRemote) return;
        if(worldObj.getBlock(xCoordTel, yCoordTel, zCoordTel) instanceof BlockBodyReconstructor && worldObj.getBlock(xCoordTel, yCoordTel-1, zCoordTel).equals(Blocks.air) && worldObj.getBlock(xCoordTel, yCoordTel-2, zCoordTel).equals(Blocks.air)){
            if (worldObj.getBlock(xCoordTel, yCoordTel-3, zCoordTel).isOpaqueCube()){
                if(player != null){
                    player.setPositionAndUpdate(xCoordTel, yCoordTel-2, zCoordTel);
                    player.fallDistance = 0.0F;
                }
            } else {
                player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "The block 3 blocks under needs to be an opaque cube"));
            }
        } else {
            player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "The teleport was unsuccessful make sure the output block" + EnumChatFormatting.RED + " is the Body Reconstruct and that it has 2 empty spaces below it"));
        }
    }

    /*public void signEdit(){
        TileEntity te = worldObj.getTileEntity(xCoord, yCoord+1, zCoord);

        if (te instanceof TileEntitySign){
            worldObj.markBlockForUpdate(xCoord, yCoord+1, zCoord);
            ((TileEntitySign) te).signText[0]="x " + xCoordTel;
            ((TileEntitySign) te).signText[1]="y " + yCoordTel;
            ((TileEntitySign) te).signText[2]="z " + zCoordTel;

        }
    }*/

}
