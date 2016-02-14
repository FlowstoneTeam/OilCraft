package bart.oilcraft.tileentity;

import bart.oilcraft.util.ConfigHandler;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.*;

import java.util.ArrayList;

/**
 * Created by Bart on 14/02/2016.
 */
public class TileEntityOilGenerator extends OCTickingTileEntity implements IFluidHandler, IEnergyProvider, IEnergyHandler {
    public FluidTank tank = new FluidTank(10000);
    public EnergyStorage energyStorage = new EnergyStorage(80000, 1000);

    public ArrayList<Integer> energyOutputs = new ArrayList<Integer>();

    public int ticksLeft = 0;

    @Override
    public void update() {
        if (worldObj.isRemote)
            return;
        if (ticksLeft > 0) {
            if (energyStorage.getEnergyStored() + ConfigHandler.OIL_GENERATOR_RF_TICK <= energyStorage.getMaxEnergyStored()) {
                worldObj.markBlockForUpdate(getPos());
                energyStorage.receiveEnergy(ConfigHandler.OIL_GENERATOR_RF_TICK, false);
                ticksLeft--;
            }
        } else if (tank.getFluidAmount() >= 100) {
            worldObj.markBlockForUpdate(getPos());
            tank.drain(100, true);
            ticksLeft = ConfigHandler.OIL_GENERATOR_TICKS;
        }
        distributePower();
    }

    @Override
    public int extractEnergy(EnumFacing from, int maxExtract, boolean simulate) {
        for (int i : energyOutputs) {
            if (i > -1 && i < 6) {
                if (EnumFacing.getFront(i) == from) {
                    this.worldObj.markBlockForUpdate(getPos());
                    return energyStorage.extractEnergy(maxExtract, simulate);
                }
            }
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
        for (int i : energyOutputs) {
            if (i > -1 && i < 6) {
                return EnumFacing.getFront(i) == from;
            }
        }
        return false;
    }

    @Override
    public int fill(EnumFacing from, FluidStack resource, boolean doFill) {
        this.worldObj.markBlockForUpdate(getPos());
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
        int[] array = nbt.getIntArray("energyOutputs");
        for (int i : array) {
            energyOutputs.add(i);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        tank.writeToNBT(nbt);
        energyStorage.writeToNBT(nbt);
        nbt.setInteger("ticksLeft", ticksLeft);
        int[] array = new int[energyOutputs.size()];
        for (int i = 0; i < energyOutputs.size(); i++) {
            array[i] = energyOutputs.get(i);
        }
        nbt.setIntArray("energyOutputs", array);
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        writeToNBT(tag);
        return new S35PacketUpdateTileEntity(getPos(), 0, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        NBTTagCompound nbt = packet.getNbtCompound();
        readFromNBT(nbt);
    }


    public void distributePower() {
        for (int i : energyOutputs) {
            if (i > -1 && i < 6) {
                EnumFacing direction = EnumFacing.getFront(i);
                TileEntity te = worldObj.getTileEntity(new BlockPos(getPos().getX() + direction.getFrontOffsetX(), getPos().getY() + direction.getFrontOffsetY(), getPos().getZ() + direction.getFrontOffsetZ()));
                if (te instanceof IEnergyHandler) {
                    int sending = ConfigHandler.OIL_GENERATOR_RF_TICK;
                    int received = ((IEnergyReceiver) te).receiveEnergy(direction, sending, true);
                    if (received <= sending && received > 0 && energyStorage.getEnergyStored() >= sending) {
                        energyStorage.extractEnergy(received, false);
                        ((IEnergyReceiver) te).receiveEnergy(direction, received, false);
                        worldObj.markBlockForUpdate(getPos());
                        worldObj.markBlockForUpdate(te.getPos());
                    }
                }
            }
        }
    }

}
