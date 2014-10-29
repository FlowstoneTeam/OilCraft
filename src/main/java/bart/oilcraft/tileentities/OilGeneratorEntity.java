package bart.oilcraft.tileentities;

import bart.oilcraft.fluids.ModFluids;
import bart.oilcraft.util.Util;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import net.minecraft.block.BlockSign;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;

/**
 * Created by Bart on 11-10-2014.
 */
public class OilGeneratorEntity extends TileEntity implements IFluidHandler, IEnergyHandler {

    public FluidTank tank = new FluidTank(10000);
    public EnergyStorage energy = new EnergyStorage(8000, 1000);
    public static int RfForOil;
    public int progress;



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
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {return energy.receiveEnergy(maxReceive, simulate);}

    @Override
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        return energy.receiveEnergy(maxExtract, simulate);
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
    public void updateEntity() {
    if(worldObj.isRemote) return;
        signEdit();
        distributePower();
        if(tank.getFluidAmount() >= 30 && energy.getEnergyStored() + RfForOil <= energy.getMaxEnergyStored()){
                this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                tank.drain(30, true);
                energy.receiveEnergy(RfForOil, false);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        tank.readFromNBT(nbt);
        energy.readFromNBT(nbt);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        tank.writeToNBT(nbt);
        energy.writeToNBT(nbt);
    }
    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound Tag = new NBTTagCompound();
        tank.writeToNBT(Tag);
        energy.writeToNBT(Tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, Tag);
    }

    public void distributePower(){
        for (int i =0; i < ForgeDirection.VALID_DIRECTIONS.length; i++){
            ForgeDirection direction = ForgeDirection.VALID_DIRECTIONS[i];
            TileEntity te = worldObj.getTileEntity(this.xCoord + direction.offsetX, this.yCoord + direction.offsetY, this.zCoord + direction.offsetZ);
            if(te instanceof IEnergyHandler){
                int sending = 11;
                int received = ((IEnergyHandler)te).receiveEnergy(direction, sending, true);
                if (received <= sending && received > 0 && energy.getEnergyStored() >= sending) {
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
            ((TileEntitySign) te).signText[1]="Fluid " + tank.getFluidAmount() + "/" + tank.getCapacity();
            ((TileEntitySign) te).signText[2]="Process " + "This block does not have a process";
            ((TileEntitySign) te).signText[3]="Block: Oil Generator";
        }
    }
}
