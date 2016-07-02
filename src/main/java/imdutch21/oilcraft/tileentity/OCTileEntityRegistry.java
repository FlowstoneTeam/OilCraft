package imdutch21.oilcraft.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class OCTileEntityRegistry {
    public static void init() {
        registerTileEntity(TileEntityOilCompressor.class, "oilCompressor");
        registerTileEntity(TileEntityOilGenerator.class, "oilGenerator");
        registerTileEntity(TileEntityOilFurnace.class, "oilFurnace");
    }


    private static void registerTileEntity(Class<? extends TileEntity> cls, String baseName) {
        GameRegistry.registerTileEntity(cls, "tile.oilcraft." + baseName);
    }
}
