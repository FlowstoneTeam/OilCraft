package bart.oilcraft.tileentities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

import java.util.ArrayList;

import static bart.oilcraft.util.OreDictionaryHelper.*;

/**
 * Created by Bart on 9-7-2015.
 */
public class TileEntityOilRefinery extends TileEntity implements IFluidHandler {
    public final int maxFluid = 10000;
    public ArrayList<FluidStack> fluidlist = new ArrayList<FluidStack>();

    @Override
    public void updateEntity() {
        if (worldObj.isRemote) return;
    }

    public FluidStack getFluidStack(int i) {
        FluidStack checkFor;
        switch (i) {
            case 0:
                checkFor = new FluidStack(RESIDUE, 0);
                break;
            case 1:
                checkFor = new FluidStack(FUEL, 0);
                break;
            case 2:
                checkFor = new FluidStack(DIESEL, 0);
                break;
            case 3:
                checkFor = new FluidStack(KEROSENE, 0);
                break;
            case 4:
                checkFor = new FluidStack(PETROL, 0);
                break;
            case 5:
                checkFor = new FluidStack(GAS, 0);
                break;
            default:
                checkFor = new FluidStack(OIL, 0);
        }
        for (FluidStack fluid : fluidlist)
            if (checkFor.getFluid().equals(fluid.getFluid()))
                return fluid;
        return checkFor;
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        NBTTagList liquidTag = tagCompound.getTagList("liquids", 10);
        fluidlist.clear();

        for (int i = 0; i < liquidTag.tagCount(); i++) {
            NBTTagCompound nbt = (NBTTagCompound) liquidTag.getCompoundTagAt(i);
            FluidStack fluid = FluidStack.loadFluidStackFromNBT(nbt);
            if (fluid != null && fluid.amount > 0)
                fluidlist.add(fluid);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        NBTTagList taglist = new NBTTagList();
        for (FluidStack fluid : fluidlist) {
            NBTTagCompound nbt = new NBTTagCompound();
            fluid.writeToNBT(nbt);
            taglist.appendTag(nbt);
        }
        tagCompound.setTag("liquids", taglist);
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


    public void outputToFluidContainer() {
        World world = this.worldObj;
        int x = this.xCoord;
        int y = this.yCoord;
        int z = this.zCoord;

    }

    public int addTo(FluidStack resource, boolean doFill) {
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        if (resource.amount <= maxFluid && isAllowedFluid(resource)) {
            if (!hasFluid(resource.getFluid())) {
                if (doFill)
                    fluidlist.add(resource);
                return resource.amount;
            } else {
                for (int i = 0; i < fluidlist.size(); i++) {
                    FluidStack stack = fluidlist.get(i);
                    if (stack.getFluid().equals(resource.getFluid()) && stack.amount + resource.amount <= maxFluid) {
                        if (doFill)
                            stack.amount += resource.amount;
                        return resource.amount;
                    }
                    if (stack.amount <= 0) {
                        fluidlist.remove(stack);
                        i--;
                    }
                }
            }
        }
        return 0;
    }

    public boolean hasFluid(Fluid fluid) {
        for (FluidStack fluidStack : fluidlist)
            if (fluid.equals(fluidStack.getFluid()))
                return true;
        return false;
    }

    public boolean isAllowedFluid(FluidStack stack) {
        Fluid[] fluids = new Fluid[]{OIL, RESIDUE, FUEL, DIESEL, KEROSENE, PETROL, GAS};
        for (Fluid fluid : fluids)
            if (stack.getFluid().equals(fluid))
                return true;
        return false;
    }

    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        if (resource.getFluid().equals(OIL) && resource.amount <= maxFluid) {
            return addTo(resource, doFill);
        }
        return 0;
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
        return fluid == OIL;
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return false;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        FluidTankInfo[] tankInfos = new FluidTankInfo[fluidlist.size()];
        int i = 0;
        boolean hasFluid = false;
        for (FluidStack stack : fluidlist) {
            tankInfos[i] = new FluidTankInfo(stack, maxFluid);
            i++;
            hasFluid = true;
        }
        if (!hasFluid) {
            tankInfos = new FluidTankInfo[1];
            tankInfos[0] = new FluidTankInfo(new FluidStack(OIL, 0), 10000);
        }
        return tankInfos;
    }
}
