package bart.oilcraft.proxy;

import bart.oilcraft.inventory.container.ContainerOilCompressor;
import bart.oilcraft.inventory.gui.GuiOilCompressor;
import bart.oilcraft.tileentity.TileEntityOilCompressor;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Bart on 12/02/2016.
 */
public class CommonProxy implements IGuiHandler {

    public static final int OIL_COMPRESSOR_GUI = 1;

    public void registerTileEntities() {
        registerTileEntity(TileEntityOilCompressor.class, "oilCompressor");
    }

    private void registerTileEntity(Class<? extends TileEntity> cls, String baseName) {
        GameRegistry.registerTileEntity(cls, "tile.oilcraft." + baseName);
    }

    public void initModels() {
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case OIL_COMPRESSOR_GUI:
                TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
                if (tileEntity instanceof TileEntityOilCompressor)
                    return new ContainerOilCompressor(player, (TileEntityOilCompressor) tileEntity);
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
        }
        return null;
    }

    public void registerFluidBlockRendering(Block block, String name) {

    }
}
