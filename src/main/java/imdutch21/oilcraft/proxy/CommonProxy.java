package imdutch21.oilcraft.proxy;

import imdutch21.oilcraft.inventory.container.ContainerOilCompressor;
import imdutch21.oilcraft.inventory.container.ContainerOilFurnace;
import imdutch21.oilcraft.inventory.container.ContainerOilGenerator;
import imdutch21.oilcraft.inventory.gui.GuiOilCompressor;
import imdutch21.oilcraft.inventory.gui.GuiOilFurnace;
import imdutch21.oilcraft.inventory.gui.GuiOilGenerator;
import imdutch21.oilcraft.tileentity.TileEntityOilCompressor;
import imdutch21.oilcraft.tileentity.TileEntityOilFurnace;
import imdutch21.oilcraft.tileentity.TileEntityOilGenerator;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler {

    public static final int OIL_COMPRESSOR_GUI = 1;
    public static final int OIL_GENERATOR_GUI = 2;
    public static final int OIL_FURNACE_GUI = 3;


    public void initModels() {
    }

    public void initRenderers(){

    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity;
        switch (ID) {
            case OIL_COMPRESSOR_GUI:
                tileEntity = world.getTileEntity(new BlockPos(x, y, z));
                if (tileEntity instanceof TileEntityOilCompressor)
                    return new ContainerOilCompressor(player, (TileEntityOilCompressor) tileEntity);
                break;
            case OIL_GENERATOR_GUI:
                tileEntity = world.getTileEntity(new BlockPos(x, y, z));
                if (tileEntity instanceof TileEntityOilGenerator)
                    return new ContainerOilGenerator(player, (TileEntityOilGenerator) tileEntity);
                break;
            case OIL_FURNACE_GUI:
                tileEntity = world.getTileEntity(new BlockPos(x, y, z));
                if (tileEntity instanceof TileEntityOilFurnace)
                    return new ContainerOilFurnace(player, (TileEntityOilFurnace) tileEntity);
                break;
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case OIL_COMPRESSOR_GUI:
                TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
                if (tileEntity instanceof TileEntityOilCompressor)
                    return new GuiOilCompressor(player, (TileEntityOilCompressor) tileEntity);
                break;
            case OIL_GENERATOR_GUI:
                tileEntity = world.getTileEntity(new BlockPos(x, y, z));
                if (tileEntity instanceof TileEntityOilGenerator)
                    return new GuiOilGenerator(player, (TileEntityOilGenerator) tileEntity);
                break;
            case OIL_FURNACE_GUI:
                tileEntity = world.getTileEntity(new BlockPos(x, y, z));
                if (tileEntity instanceof TileEntityOilFurnace)
                    return new GuiOilFurnace(player, (TileEntityOilFurnace) tileEntity);
                break;
        }
        return null;
    }

    public void registerFluidBlockRendering(Block block, String name) {

    }
}
