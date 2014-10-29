package bart.oilcraft.client.gui;

import bart.oilcraft.containers.ContainerOilCompressor;
import bart.oilcraft.containers.ContainerOilFurnace;
import bart.oilcraft.containers.ContainerSlipperyRemover;
import bart.oilcraft.tileentities.OilCompressorEntity;
import bart.oilcraft.tileentities.TileEntityOilFurnace;
import bart.oilcraft.tileentities.TileEntitySlipperyRemover;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Bart on 20-7-2014.
 */
public class GuiHandler implements IGuiHandler{
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity entity = world.getTileEntity(x, y, z);
        if (entity != null) {
            switch (ID)  {
                case 0: return new ContainerOilCompressor(player, (OilCompressorEntity) entity);
                case 1: return new ContainerSlipperyRemover(player, (TileEntitySlipperyRemover) entity);
                case 2: return new ContainerOilFurnace(player, (TileEntityOilFurnace) entity);
            }
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity entity = world.getTileEntity(x, y, z);
        if (entity != null) {
            switch (ID)  {
                case 0: return new OilCompressorGUI(player, (OilCompressorEntity) entity);
                case 1: return new SlipperyRemoverGUI(player, (TileEntitySlipperyRemover) entity);
                case 2: return new OilFurnaceGUI(player, (TileEntityOilFurnace) entity);
            }
        }
        return null;
    }
}
