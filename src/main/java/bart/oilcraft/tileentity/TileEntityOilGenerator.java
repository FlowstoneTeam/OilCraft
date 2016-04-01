package bart.oilcraft.tileentity;

import bart.oilcraft.util.ConfigHandler;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fluids.*;

public class TileEntityOilGenerator extends OCTickingTileEntity implements IFluidHandler, IEnergyProvider, IEnergyHandler {
    public FluidTank tank = new FluidTank(10000);
    public EnergyStorage energyStorage = new EnergyStorage(80000, 1000);

    public boolean outputUp = false, outputDown = false, outputNorth = false, outputSouth = false, outputEast = false, outputWest = false;

    public int ticksLeft = 0;

    @Override
    public void update() {
        super.update();
        if (worldObj.isRemote)
            return;
        if (ticksLeft > 0) {
            if (energyStorage.getEnergyStored() + ConfigHandler.OIL_GENERATOR_RF_TICK <= energyStorage.getMaxEnergyStored()) {
                energyStorage.receiveEnergy(ConfigHandler.OIL_GENERATOR_RF_TICK, false);
                ticksLeft--;
                worldObj.notifyBlockUpdate(getPos(), worldObj.getBlockState(getPos()), worldObj.getBlockState(getPos()), 3);
            }
        } else if (tank.getFluidAmount() >= 100) {
            worldObj.notifyBlockUpdate(getPos(), worldObj.getBlockState(getPos()), worldObj.getBlockState(getPos()), 3);
            tank.drain(100, true);
            ticksLeft = ConfigHandler.OIL_GENERATOR_TICKS;
        }
        distributePower();
    }

    @Override
    public int extractEnergy(EnumFacing from, int maxExtract, boolean simulate) {
        if (outputEnabled(from.getIndex())) {
            worldObj.notifyBlockUpdate(getPos(), worldObj.getBlockState(getPos()), worldObj.getBlockState(getPos()), 3);
            return energyStorage.extractEnergy(maxExtract, simulate);
        }
        return 0;
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
        return outputEnabled(from.getIndex());
    }

    @Override
    public int fill(EnumFacing from, FluidStack resource, boolean doFill) {
        worldObj.notifyBlockUpdate(getPos(), worldObj.getBlockState(getPos()), worldObj.getBlockState(getPos()), 3);
        return tank.fill(resource, doFill);
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
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        tank.readFromNBT(nbt);
        energyStorage.readFromNBT(nbt);
        ticksLeft = nbt.getInteger("ticksLeft");
        outputDown = nbt.getBoolean("outputDown");
        outputUp = nbt.getBoolean("outputUp");
        outputNorth = nbt.getBoolean("outputNorth");
        outputEast = nbt.getBoolean("outputEast");
        outputSouth = nbt.getBoolean("outputSouth");
        outputWest = nbt.getBoolean("outputWest");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        tank.writeToNBT(nbt);
        energyStorage.writeToNBT(nbt);
        nbt.setInteger("ticksLeft", ticksLeft);
        nbt.setBoolean("outputDown", outputDown);
        nbt.setBoolean("outputUp", outputUp);
        nbt.setBoolean("outputNorth", outputNorth);
        nbt.setBoolean("outputEast", outputEast);
        nbt.setBoolean("outputSouth", outputSouth);
        nbt.setBoolean("outputWest", outputWest);
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


    public void distributePower() {
        for (EnumFacing direction : EnumFacing.VALUES) {
            if (outputEnabled(direction.getIndex())) {
                TileEntity te = worldObj.getTileEntity(new BlockPos(getPos().getX() + direction.getFrontOffsetX(), getPos().getY() + direction.getFrontOffsetY(), getPos().getZ() + direction.getFrontOffsetZ()));
                if (te instanceof IEnergyHandler) {
                    int sending = ConfigHandler.OIL_GENERATOR_RF_TICK;
                    int received = ((IEnergyReceiver) te).receiveEnergy(direction, sending, true);
                    if (received <= sending && received > 0 && energyStorage.getEnergyStored() >= sending) {
                        energyStorage.extractEnergy(received, false);
                        ((IEnergyReceiver) te).receiveEnergy(direction, received, false);
                        worldObj.notifyBlockUpdate(getPos(), worldObj.getBlockState(getPos()), worldObj.getBlockState(getPos()), 3);
                        worldObj.notifyBlockUpdate(te.getPos(), worldObj.getBlockState(te.getPos()), worldObj.getBlockState(te.getPos()), 3);
                    }
                }
            }
        }
    }


    public boolean outputEnabled(int side) {
        switch (side) {
            case 0:
                return outputDown;
            case 1:
                return outputUp;
            case 2:
                return outputNorth;
            case 3:
                return outputSouth;
            case 4:
                return outputWest;
            case 5:
                return outputEast;
            default:
                return false;
        }
    }

    public void switchOutput(int side) {
        switch (side) {
            case 0:
                outputDown = !outputDown;
                break;
            case 1:
                outputUp = !outputUp;
                break;
            case 2:
                outputNorth = !outputNorth;
                break;
            case 3:
                outputSouth = !outputSouth;
                break;
            case 4:
                outputWest = !outputWest;
                break;
            case 5:
                outputEast = !outputEast;
                break;
        }
    }
}
