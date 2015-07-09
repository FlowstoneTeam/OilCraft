package bart.oilcraft.tileentities;

import bart.oilcraft.util.Util;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.*;

import java.util.ArrayList;

/**
 * Created by Bart on 9-7-2015.
 */
public class TileEntityOilRefinery extends TileEntity implements IFluidHandler{
    public FluidTank[] tanks = new FluidTank[]{new FluidTank(10000), new FluidTank(10000), new FluidTank(10000), new FluidTank(10000), new FluidTank(10000), new FluidTank(10000)};
    public FluidTank oilTank = new FluidTank(10000);
    public FluidTank residueTank = tanks[0];
    public FluidTank fuelTank = tanks[1];
    public FluidTank dieselTank = tanks[2];
    public FluidTank keroseneTank = tanks[3];
    public FluidTank petrolTank = tanks[4];
    public FluidTank gasTank = tanks[5];



    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        if(resource.getFluid() == FluidRegistry.getFluid("oil"))
            return oilTank.fill(resource, doFill);
        return 0;
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        if (resource != null) {
            for(FluidTank fluidTank:tanks){
                if(resource.isFluidEqual(fluidTank.getFluid())) {
                    this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                    return fluidTank.drain(resource.amount, doDrain);
                }
            }
        }

        return null;
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        return null;
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return fluid == FluidRegistry.getFluid("oil");
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return (fluid == FluidRegistry.getFluid("residue") || fluid == FluidRegistry.getFluid("diesel") || fluid == FluidRegistry.getFluid("fuel") || fluid == FluidRegistry.getFluid("kerosene") || fluid == FluidRegistry.getFluid("petrol") || fluid == FluidRegistry.getFluid("gas"));
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[]{new FluidTankInfo(oilTank)};
    }

    public FluidTank getTank(int tankNumber){
        if(tankNumber <= 5)
            return tanks[tankNumber];
        return oilTank;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        oilTank.readFromNBT(nbt);
        for(FluidTank tank:tanks)
            tank.readFromNBT(nbt);
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        for(FluidTank tank:tanks)
            tank.writeToNBT(nbt);
        oilTank.writeToNBT(nbt);
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
        if (oilTank.fill(new FluidStack(FluidRegistry.getFluid("oil"), 1000), false) == 1000)
            oilTank.fill(new FluidStack(FluidRegistry.getFluid("oil"), 1000), true);
        if (residueTank.fill(new FluidStack(FluidRegistry.getFluid("residue"), 1000), false) == 1000)
            residueTank.fill(new FluidStack(FluidRegistry.getFluid("residue"), 1000), true);
        if (fuelTank.fill(new FluidStack(FluidRegistry.getFluid("fuel"), 1000), false) == 1000)
            fuelTank.fill(new FluidStack(FluidRegistry.getFluid("fuel"), 1000), true);
        if (dieselTank.fill(new FluidStack(FluidRegistry.getFluid("diesel"), 1000), false) == 1000)
            dieselTank.fill(new FluidStack(FluidRegistry.getFluid("diesel"), 1000), true);
        if (keroseneTank.fill(new FluidStack(FluidRegistry.getFluid("kerosene"), 1000), false) == 1000)
            keroseneTank.fill(new FluidStack(FluidRegistry.getFluid("kerosene"), 1000), true);
        if (petrolTank.fill(new FluidStack(FluidRegistry.getFluid("petrol"), 1000), false) == 1000)
            petrolTank.fill(new FluidStack(FluidRegistry.getFluid("petrol"), 1000), true);
        if (gasTank.fill(new FluidStack(FluidRegistry.getFluid("gas"), 1000), false) == 1000)
            gasTank.fill(new FluidStack(FluidRegistry.getFluid("gas"), 1000), true);
    }
}
